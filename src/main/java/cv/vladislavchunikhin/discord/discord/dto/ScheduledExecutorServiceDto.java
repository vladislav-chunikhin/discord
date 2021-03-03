package cv.vladislavchunikhin.discord.discord.dto;

import lombok.Data;

import java.util.concurrent.ScheduledExecutorService;

/**
 * Scheduled executor service data transfer object to save service and relevant description.
 */
@Data
public class ScheduledExecutorServiceDto {
    /**
     * {@link ScheduledExecutorService}.
     */
    private final ScheduledExecutorService scheduledExecutorService;
    /**
     * Short description for {@link ScheduledExecutorService}.
     */
    private final String description;
}
