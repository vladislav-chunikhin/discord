package cv.vladislavchunikhin.discord.discord;

import cv.vladislavchunikhin.discord.discord.dto.SecPeriodDto;
import cv.vladislavchunikhin.discord.discord.dto.TimeCalculationDto;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class TimeCalculationComponentImpl implements TimeCalculationComponent {

    @Override
    public SecPeriodDto calculateSecPeriodFromNowToFixedTime(@NonNull final TimeCalculationDto dto) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextTime = now.withHour(dto.getHours()).withMinute(0).withSecond(0);

        if (now.getDayOfWeek() == dto.getDayOfWeek()) {
            return getSecPeriodDtoWhenDaysOfWeekAreEquals(now, nextTime);
        }

        return new SecPeriodDto(0, 0);
    }

    private SecPeriodDto getSecPeriodDtoWhenDaysOfWeekAreEquals(LocalDateTime now, LocalDateTime nextTime) {
        if (nextTime.equals(now) || nextTime.isAfter(now)) {
            LocalDateTime nextWeek = nextTime.plusDays(DayOfWeek.values().length);
            long period = Duration.between(nextTime, nextWeek).toSeconds();
            return new SecPeriodDto(0, period);
        }
        LocalDateTime nextWeek = nextTime.plusDays(DayOfWeek.values().length);
        long period = Duration.between(nextTime, nextWeek).toSeconds();
        return new SecPeriodDto(Duration.between(now, nextTime).toSeconds(), period);
    }
}
