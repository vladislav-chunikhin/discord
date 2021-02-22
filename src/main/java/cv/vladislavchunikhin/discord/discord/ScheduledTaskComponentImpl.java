package cv.vladislavchunikhin.discord.discord;

import cv.vladislavchunikhin.discord.discord.dto.ScheduledExecutorServiceDto;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import cv.vladislavchunikhin.discord.http.HttpCodeWithMessageDto;
import cv.vladislavchunikhin.discord.discord.dto.ScheduleTaskDto;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Component
@Slf4j
public class ScheduledTaskComponentImpl implements ScheduledTaskComponent {
    private static final Map<UUID, ScheduledExecutorServiceDto> SCHEDULE_EXECUTORS_MAP = new HashMap<>();

    @Override
    public UUID createScheduledTask(@NonNull final ScheduleTaskDto dto) {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(dto.getTask(), dto.getDelay(), dto.getPeriod(), dto.getUnit());
        UUID scheduleTaskId = UUID.randomUUID();
        SCHEDULE_EXECUTORS_MAP.put(scheduleTaskId, new ScheduledExecutorServiceDto(scheduledExecutorService, dto.getDescription()));
        return scheduleTaskId;
    }

    @Override
    public HttpCodeWithMessageDto turnOffTaskById(final UUID id) {
        if (SCHEDULE_EXECUTORS_MAP.containsKey(id)) {
            ScheduledExecutorServiceDto dto = SCHEDULE_EXECUTORS_MAP.get(id);
            if (!dto.getScheduledExecutorService().isShutdown()) {
                dto.getScheduledExecutorService().shutdown();
                log.info("Task by id = " + id + " turned off.");
                return new HttpCodeWithMessageDto();
            }
            String msg = "Schedule task is shutdown already.";
            log.info(msg);
            return new HttpCodeWithMessageDto(HttpStatus.OK, msg);
        }
        String msg = "Schedule task not found by id = " + id + ".";
        log.info(msg);
        return new HttpCodeWithMessageDto(HttpStatus.NOT_FOUND, msg);
    }

    @Override
    public HttpCodeWithMessageDto turnOffAllTasks() {
        if (!SCHEDULE_EXECUTORS_MAP.isEmpty()) {
            Collection<ScheduledExecutorServiceDto> scheduledExecutorServices = SCHEDULE_EXECUTORS_MAP.values();
            scheduledExecutorServices
                    .stream()
                    .filter(Objects::nonNull)
                    .map(ScheduledExecutorServiceDto::getScheduledExecutorService)
                    .filter(Objects::nonNull)
                    .forEach(ScheduledExecutorService::shutdown);
            log.info("Schedule tasks are turned off.");
            return new HttpCodeWithMessageDto();
        }
        return new HttpCodeWithMessageDto(HttpStatus.OK, "No schedule tasks found to turn off them.");
    }

    @Override
    public Map<UUID, ScheduledExecutorServiceDto> getAllTasks() {
        return SCHEDULE_EXECUTORS_MAP;
    }
}
