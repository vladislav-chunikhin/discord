package cv.vladislavchunikhin.discord.discord;

import cv.vladislavchunikhin.discord.discord.dto.ScheduledExecutorServiceDto;
import cv.vladislavchunikhin.discord.http.HttpCodeWithMessageDto;
import cv.vladislavchunikhin.discord.discord.dto.ScheduleTaskDto;

import java.util.Map;
import java.util.UUID;

public interface ScheduledTaskComponent {
    UUID createScheduledTask(ScheduleTaskDto dto);
    HttpCodeWithMessageDto turnOffTaskById(UUID id);
    HttpCodeWithMessageDto turnOffAllTasks();
    Map<UUID, ScheduledExecutorServiceDto> getAllTasks();
}
