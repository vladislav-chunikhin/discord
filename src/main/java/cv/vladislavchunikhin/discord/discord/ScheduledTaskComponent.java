package cv.vladislavchunikhin.discord.discord;

import cv.vladislavchunikhin.discord.discord.dto.ScheduledExecutorServiceDto;
import cv.vladislavchunikhin.discord.discord.dto.ScheduleTaskDto;
import cv.vladislavchunikhin.discord.http.GeneralResponse;

import java.util.Map;
import java.util.UUID;

/**
 * Component containing some methods for working with scheduled tasks.
 */
public interface ScheduledTaskComponent {
    /**
     * @param dto schedule task data transfer object.
     * @return task identifier as UUID.
     */
    UUID createScheduledTask(ScheduleTaskDto dto);

    /**
     * @param id task identifier as UUID.
     * @return information about result of method execution as {@link GeneralResponse}.
     */
    GeneralResponse turnOffTaskById(UUID id);

    /**
     * @return information about result of method execution as {@link GeneralResponse}.
     */
    GeneralResponse turnOffAllTasks();

    /**
     * @return map where key = task identifier as UUID but value = {@link ScheduledExecutorServiceDto}.
     */
    Map<UUID, ScheduledExecutorServiceDto> getAllTasks();
}
