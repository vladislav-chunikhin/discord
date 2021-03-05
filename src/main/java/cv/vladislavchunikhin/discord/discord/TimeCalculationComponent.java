package cv.vladislavchunikhin.discord.discord;

import cv.vladislavchunikhin.discord.discord.dto.SecPeriodDto;
import cv.vladislavchunikhin.discord.discord.dto.TimeCalculationDto;

/**
 * Component that calculates time for tasks.
 */
public interface TimeCalculationComponent {
    /**
     * @param dto initial data to calculate necessary period for a task.
     * @return required period with delay.
     */
    SecPeriodDto calculateSecPeriodFromNowToFixedTime(TimeCalculationDto dto);
}
