package cv.vladislavchunikhin.discord.discord.dto;

import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

/**
 * Input data transfer object for {@link cv.vladislavchunikhin.discord.discord.TimeCalculationComponent#calculateSecPeriodFromNowToFixedTime(TimeCalculationDto)}.
 */
@Data
public class TimeCalculationDto {
    /**
     * Day of week. For example, Monday or Friday.
     */
    private final DayOfWeek dayOfWeek;
    /**
     * Hour. Available values are from 0 to 23.
     */
    private final Integer hours;
    /**
     * Current time as {@link LocalDateTime}.
     */
    private final LocalDateTime currentTime;
}
