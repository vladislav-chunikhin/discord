package cv.vladislavchunikhin.discord.discord;

import cv.vladislavchunikhin.discord.discord.dto.SecPeriodDto;
import cv.vladislavchunikhin.discord.discord.dto.TimeCalculationDto;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Component that calculates time for tasks.
 */
@Component
public class TimeCalculationComponentImpl implements TimeCalculationComponent {

    /**
     * @param dto initial data to calculate necessary period for a task.
     * @return required period with delay.
     */
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

    /**
     * @param now current day of week as {@link DayOfWeek}.
     * @param target target day of week as {@link DayOfWeek}.
     * @return difference between days of week as seconds.
     */
    private long getDelayInSeconds(@Nullable final DayOfWeek now, @Nullable final DayOfWeek target) {
        if (now == null || target == null) return 0;
        if (now == target) return 0;
        int nowValue = now.getValue();
        int targetValue = target.getValue();
        return calculateDelayInSeconds(nowValue, targetValue);
    }

    /**
     * @param now    current day of week as int value.
     * @param target target day of week as int value.
     * @return difference between days of week as seconds.
     */
    private long calculateDelayInSeconds(int now, int target) {
        int dayDiff;
        if (target > now) {
            dayDiff = target - now;
        } else {
            if (now == DayOfWeek.values().length) {
                dayDiff = target;
            } else {
                int restDaysToEndOfWeek = DayOfWeek.values().length - now;
                dayDiff = restDaysToEndOfWeek + target;
            }
        }
        return Duration.ofDays(1).toSeconds() * dayDiff;
    }
}
