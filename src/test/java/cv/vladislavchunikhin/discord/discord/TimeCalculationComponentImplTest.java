package cv.vladislavchunikhin.discord.discord;

import cv.vladislavchunikhin.discord.discord.dto.SecPeriodDto;
import cv.vladislavchunikhin.discord.discord.dto.TimeCalculationDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.stream.Stream;

public class TimeCalculationComponentImplTest {

    private final TimeCalculationComponent testable = Mockito.spy(TimeCalculationComponentImpl.class);

    @ParameterizedTest
    @MethodSource("calculateSecPeriodFromNowToFixedTimeProvider")
    void calculateSecPeriodFromNowToFixedTime(final TimeCalculationDto inputDto, long expectedDelay, long expectedPeriod) {
        SecPeriodDto actualResult = testable.calculateSecPeriodFromNowToFixedTime(inputDto);
        Assertions.assertEquals(actualResult.getDelay(), expectedDelay);
        Assertions.assertEquals(actualResult.getPeriod(), expectedPeriod);
    }

    static Stream<Object[]> calculateSecPeriodFromNowToFixedTimeProvider() {
        final LocalDateTime currentTime = LocalDateTime.of(2021, Month.JANUARY, 4, 2, 23);
        return Stream.of(
               new Object[]{new TimeCalculationDto(DayOfWeek.MONDAY, 0, currentTime), 0, 604800},
               new Object[]{new TimeCalculationDto(DayOfWeek.MONDAY, 1, currentTime), 0, 604800}
        );
    }
}