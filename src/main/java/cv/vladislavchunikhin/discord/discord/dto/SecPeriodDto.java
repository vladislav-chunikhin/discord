package cv.vladislavchunikhin.discord.discord.dto;

import lombok.Data;

/**
 * Result of {@link cv.vladislavchunikhin.discord.discord.TimeCalculationComponent#calculateSecPeriodFromNowToFixedTime(TimeCalculationDto)}
 */
@Data
public class SecPeriodDto {
    /**
     * Task delay (ms).
     */
    private final long delay;
    /**
     * Task period (ms).
     */
    private final long period;
}
