package cv.vladislavchunikhin.discord.discord.dto;

import lombok.Data;

import java.time.DayOfWeek;

@Data
public class TimeCalculationDto {
    private final DayOfWeek dayOfWeek;
    private final Integer hours;
}
