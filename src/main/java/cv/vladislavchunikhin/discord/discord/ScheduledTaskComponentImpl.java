package cv.vladislavchunikhin.discord.discord;

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
    private static final Map<UUID, ScheduledExecutorService> SCHEDULE_EXECUTORS_MAP = new HashMap<>();

    @Override
    public UUID createScheduledTask(@NonNull final ScheduleTaskDto dto) {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(dto.getTask(), dto.getDelay(), dto.getPeriod(), dto.getUnit());
        UUID scheduleTaskId = UUID.randomUUID();
        SCHEDULE_EXECUTORS_MAP.put(scheduleTaskId, scheduledExecutorService);
        return scheduleTaskId;
    }

    @Override
    public HttpCodeWithMessageDto turnOffTaskById(final UUID id) {
        if (SCHEDULE_EXECUTORS_MAP.containsKey(id)) {
            ScheduledExecutorService scheduledExecutorService = SCHEDULE_EXECUTORS_MAP.get(id);
            if (!scheduledExecutorService.isShutdown()) {
                scheduledExecutorService.shutdown();
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
            Collection<ScheduledExecutorService> scheduledExecutorServices = SCHEDULE_EXECUTORS_MAP.values();
            scheduledExecutorServices.stream().filter(Objects::nonNull).forEach(ScheduledExecutorService::shutdown);
            log.info("Schedule tasks are turned off.");
            return new HttpCodeWithMessageDto();
        }
        return new HttpCodeWithMessageDto(HttpStatus.OK, "No schedule tasks found to turn off them.");
    }

    public Map<UUID, ScheduledExecutorService> getScheduleExecutorsMap() {
        return SCHEDULE_EXECUTORS_MAP;
    }

}
