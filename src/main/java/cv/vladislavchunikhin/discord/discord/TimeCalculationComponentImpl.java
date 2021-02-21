package cv.vladislavchunikhin.discord.discord;

import cv.vladislavchunikhin.discord.discord.dto.SecPeriodDto;
import cv.vladislavchunikhin.discord.discord.dto.TimeCalculationDto;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class TimeCalculationComponentImpl implements TimeCalculationComponent {

    @Override
    public SecPeriodDto calculateSecPeriodFromNowToFixedTime(@NonNull final TimeCalculationDto dto) {
        LocalDateTime nextTime = dto.getCurrentTime().withHour(dto.getHours()).withMinute(0).withSecond(0).withNano(0);

        long dayDelayInSeconds = getDelayInSeconds(dto.getCurrentTime().getDayOfWeek(), dto.getDayOfWeek());

        LocalDateTime nextWeek = nextTime.plusDays(DayOfWeek.values().length);
        long period = Duration.between(nextTime, nextWeek).toSeconds();

        if (dto.getHours() > dto.getCurrentTime().getHour()) {
            dayDelayInSeconds = dayDelayInSeconds + Duration.between(dto.getCurrentTime(), nextTime).toSeconds();
        }

        return new SecPeriodDto(dayDelayInSeconds, period);
    }

    private long getDelayInSeconds(@Nullable final DayOfWeek now, @Nullable final DayOfWeek target) {
        if (now == null || target == null) return 0;
        if (now == target) return 0;
        int nowValue = now.getValue();
        int targetValue = target.getValue();
        if (nowValue > targetValue) return calculateDelayInSeconds(targetValue, nowValue);
        return calculateDelayInSeconds(nowValue, targetValue);
    }

    private long calculateDelayInSeconds(int min, int max) {
        int dayDiff = (DayOfWeek.values().length + (min - max)) % DayOfWeek.values().length;
        return Duration.ofDays(1).toSeconds() * dayDiff;
    }
}
