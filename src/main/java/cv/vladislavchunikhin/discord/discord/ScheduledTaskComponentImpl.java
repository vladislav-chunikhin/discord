package cv.vladislavchunikhin.discord.discord;

import cv.vladislavchunikhin.discord.discord.dto.ScheduledExecutorServiceDto;
import cv.vladislavchunikhin.discord.http.ErrorType;
import cv.vladislavchunikhin.discord.http.GeneralResponse;
import cv.vladislavchunikhin.discord.http.ResponseAPI;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import cv.vladislavchunikhin.discord.discord.dto.ScheduleTaskDto;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Implementation of {@link ScheduledTaskComponent}.
 */
@Component
@Slf4j
public class ScheduledTaskComponentImpl implements ScheduledTaskComponent {

    /**
     * Map containing task identifier as UUID and {@link ScheduledExecutorServiceDto}.
     */
    private static final Map<UUID, ScheduledExecutorServiceDto> SCHEDULE_EXECUTORS_MAP = new HashMap<>();

    /**
     * @param dto schedule task data transfer object.
     * @return task identifier as UUID.
     */
    @Override
    public UUID createScheduledTask(@NonNull final ScheduleTaskDto dto) {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(dto.getTask(), dto.getDelay(), dto.getPeriod(), dto.getUnit());
        UUID scheduleTaskId = UUID.randomUUID();
        SCHEDULE_EXECUTORS_MAP.put(scheduleTaskId, new ScheduledExecutorServiceDto(scheduledExecutorService, dto.getDescription()));
        return scheduleTaskId;
    }

    /**
     * @param id task identifier as UUID.
     * @return information about result of method execution as {@link GeneralResponse}.
     */
    @Override
    public GeneralResponse turnOffTaskById(@NonNull final UUID id) {
        if (SCHEDULE_EXECUTORS_MAP.containsKey(id)) {
            ScheduledExecutorServiceDto dto = SCHEDULE_EXECUTORS_MAP.get(id);
            if (!dto.getScheduledExecutorService().isShutdown()) {
                dto.getScheduledExecutorService().shutdown();
                log.info("Task by id = " + id + " turned off.");
                return ResponseAPI.emptyPositiveResponse();
            }
            String msg = "Schedule task is shutdown already.";
            log.info(msg);
            return ResponseAPI.emptyPositiveResponse();
        }
        String msg = "Schedule task not found by id = " + id + ".";
        log.info(msg);
        return ResponseAPI.negativeResponse(HttpStatus.NOT_FOUND, msg, ErrorType.INTERNAL_SERVER_ERROR);
    }

    /**
     * @return information about result of method execution as {@link GeneralResponse}.
     */
    @Override
    public GeneralResponse turnOffAllTasks() {
        if (!SCHEDULE_EXECUTORS_MAP.isEmpty()) {
            Collection<ScheduledExecutorServiceDto> scheduledExecutorServices = SCHEDULE_EXECUTORS_MAP.values();
            scheduledExecutorServices
                    .stream()
                    .filter(Objects::nonNull)
                    .map(ScheduledExecutorServiceDto::getScheduledExecutorService)
                    .filter(Objects::nonNull)
                    .forEach(ScheduledExecutorService::shutdown);
            log.info("Schedule tasks are turned off.");
        }
        return ResponseAPI.emptyPositiveResponse();
    }

    /**
     * @return map where key = task identifier as UUID but value = {@link ScheduledExecutorServiceDto}.
     */
    @Override
    public Map<UUID, ScheduledExecutorServiceDto> getAllTasks() {
        return SCHEDULE_EXECUTORS_MAP;
    }
}
