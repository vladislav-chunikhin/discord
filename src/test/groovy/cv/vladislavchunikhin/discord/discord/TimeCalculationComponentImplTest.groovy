package cv.vladislavchunikhin.discord.discord

import cv.vladislavchunikhin.discord.discord.dto.TimeCalculationDto
import cv.vladislavchunikhin.discord.spock.BaseSpec
import spock.lang.Shared

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.Month

/**
 * Unit tests for {@link TimeCalculationComponentImpl}.
 */
class TimeCalculationComponentImplTest extends BaseSpec {

    private TimeCalculationComponent testable = new TimeCalculationComponentImpl()

    @Shared LocalDateTime currentTime = LocalDateTime.of(2021, Month.JANUARY, 4, 2, 23)

    /**
     * Normal case.
     * {@link TimeCalculationComponentImpl#calculateSecPeriodFromNowToFixedTime(cv.vladislavchunikhin.discord.discord.dto.TimeCalculationDto)}
     */
    def "getting delay and period when schedule task day equals monday"() {
        when:
        def actualResult = testable.calculateSecPeriodFromNowToFixedTime(inputDto)
        then:
        actualResult.delay == expectedDelay
        actualResult.period == expectedPeriod
        where:
                            inputDto                              |  expectedDelay | expectedPeriod
        new TimeCalculationDto(DayOfWeek.MONDAY, 0, currentTime)  |       0        |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 1, currentTime)  |       0        |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 2, currentTime)  |       0        |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 3, currentTime)  |      2220      |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 4, currentTime)  |      5820      |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 5, currentTime)  |      9420      |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 6, currentTime)  |      13020     |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 7, currentTime)  |      16620     |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 8, currentTime)  |      20220     |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 9, currentTime)  |      23820     |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 10, currentTime) |      27420     |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 11, currentTime) |      31020     |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 12, currentTime) |      34620     |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 13, currentTime) |      38220     |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 14, currentTime) |      41820     |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 15, currentTime) |      45420     |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 16, currentTime) |      49020     |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 17, currentTime) |      52620     |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 18, currentTime) |      56220     |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 19, currentTime) |      59820     |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 20, currentTime) |      63420     |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 21, currentTime) |      67020     |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 22, currentTime) |      70620     |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 23, currentTime) |      74220     |     604800
    }
}
