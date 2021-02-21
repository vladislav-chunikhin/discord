package cv.vladislavchunikhin.discord.discord.dto;

import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Data
public class TimeCalculationDto {
    private final DayOfWeek dayOfWeek;
    private final Integer hours;
    private final LocalDateTime currentTime;
}
