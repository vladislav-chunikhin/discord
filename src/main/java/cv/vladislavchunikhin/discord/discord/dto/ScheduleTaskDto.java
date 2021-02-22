package cv.vladislavchunikhin.discord.discord.dto;

import lombok.Data;

import java.util.concurrent.TimeUnit;

@Data
public class ScheduleTaskDto {
    private final Runnable task;
    private final long delay;
    private final long period;
    private final TimeUnit unit;
    private final String description;
}
