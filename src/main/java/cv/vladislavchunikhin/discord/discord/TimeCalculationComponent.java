package cv.vladislavchunikhin.discord.discord;

import cv.vladislavchunikhin.discord.discord.dto.SecPeriodDto;
import cv.vladislavchunikhin.discord.discord.dto.TimeCalculationDto;

public interface TimeCalculationComponent {
    SecPeriodDto calculateSecPeriodFromNowToFixedTime(TimeCalculationDto dto);
}
