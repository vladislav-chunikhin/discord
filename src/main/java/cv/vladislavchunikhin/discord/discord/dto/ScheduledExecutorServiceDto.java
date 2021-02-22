package cv.vladislavchunikhin.discord.discord.dto;

import lombok.Data;

import java.util.concurrent.ScheduledExecutorService;

@Data
public class ScheduledExecutorServiceDto {
    private final ScheduledExecutorService scheduledExecutorService;
    private final String description;
}
