package cv.vladislavchunikhin.discord.discord;

import cv.vladislavchunikhin.discord.discord.dto.SecPeriodDto;
import cv.vladislavchunikhin.discord.discord.dto.TimeCalculationDto;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TimeCalculationComponentImplJavaTest {

    private TimeCalculationComponent testable = Mockito.spy(TimeCalculationComponentImpl.class);

    @ParameterizedTest
    @MethodSource("calculateSecPeriodFromNowToFixedTimeProvider")
    void calculateSecPeriodFromNowToFixedTime(final TimeCalculationDto inputDto) {
        SecPeriodDto actualResult = testable.calculateSecPeriodFromNowToFixedTime(inputDto);
    }

    static Stream<TimeCalculationDto> calculateSecPeriodFromNowToFixedTimeProvider() {
        LocalDateTime now = LocalDateTime.now();
        return IntStream.range(0, 24).mapToObj(index -> new TimeCalculationDto(now.getDayOfWeek(), index, now));
    }
}