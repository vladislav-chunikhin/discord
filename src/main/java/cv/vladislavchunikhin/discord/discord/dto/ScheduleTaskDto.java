package cv.vladislavchunikhin.discord.discord.dto;

import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * Schedule task data transfer object.
 */
@Data
public class ScheduleTaskDto {
    /**
     * Task for executing.
     */
    private final Runnable task;
    /**
     * Delay task (ms).
     */
    private final long delay;
    /**
     * Period task (ms).
     */
    private final long period;
    /**
     * {@link TimeUnit}.
     */
    private final TimeUnit unit;
    /**
     * Short description.
     */
    private final String description;
}
