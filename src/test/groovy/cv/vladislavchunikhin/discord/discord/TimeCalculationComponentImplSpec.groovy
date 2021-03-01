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
class TimeCalculationComponentImplSpec extends BaseSpec {

    private TimeCalculationComponent testable = new TimeCalculationComponentImpl()

    @Shared private LocalDateTime currentTimeFirstCase = LocalDateTime.of(2021, Month.JANUARY, 4, 2, 23)
    @Shared private LocalDateTime currentTimeSecondCase = LocalDateTime.of(2021, Month.JANUARY, 6, 5, 33)

    /**
     * Only Monday, the same day as now.
     * {@link TimeCalculationComponentImpl#calculateSecPeriodFromNowToFixedTime(cv.vladislavchunikhin.discord.discord.dto.TimeCalculationDto)}
     */
    def "getting delay and period when schedule task day equals monday"() {
        when:
        def actualResult = testable.calculateSecPeriodFromNowToFixedTime(inputDto)
        then:
        actualResult.delay == expectedDelay
        actualResult.period == expectedPeriod
        where:
                            inputDto                                       |  expectedDelay | expectedPeriod
        new TimeCalculationDto(DayOfWeek.MONDAY, 0, currentTimeFirstCase)  |       0        |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 1, currentTimeFirstCase)  |       0        |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 2, currentTimeFirstCase)  |       0        |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 3, currentTimeFirstCase)  |      2220      |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 4, currentTimeFirstCase)  |      5820      |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 5, currentTimeFirstCase)  |      9420      |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 6, currentTimeFirstCase)  |      13020     |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 7, currentTimeFirstCase)  |      16620     |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 8, currentTimeFirstCase)  |      20220     |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 9, currentTimeFirstCase)  |      23820     |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 10, currentTimeFirstCase) |      27420     |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 11, currentTimeFirstCase) |      31020     |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 12, currentTimeFirstCase) |      34620     |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 13, currentTimeFirstCase) |      38220     |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 14, currentTimeFirstCase) |      41820     |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 15, currentTimeFirstCase) |      45420     |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 16, currentTimeFirstCase) |      49020     |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 17, currentTimeFirstCase) |      52620     |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 18, currentTimeFirstCase) |      56220     |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 19, currentTimeFirstCase) |      59820     |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 20, currentTimeFirstCase) |      63420     |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 21, currentTimeFirstCase) |      67020     |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 22, currentTimeFirstCase) |      70620     |     604800
        new TimeCalculationDto(DayOfWeek.MONDAY, 23, currentTimeFirstCase) |      74220     |     604800
    }

    /**
     * All days of week.
     * {@link TimeCalculationComponentImpl#calculateSecPeriodFromNowToFixedTime(cv.vladislavchunikhin.discord.discord.dto.TimeCalculationDto)}
     */
    def "getting delay and period when schedule task day equals all of week days"() {
        when:
        def actualResult = testable.calculateSecPeriodFromNowToFixedTime(inputDto)
        then:
        actualResult.delay == expectedDelay
        actualResult.period == expectedPeriod
        where:
                            inputDto                                          |  expectedDelay | expectedPeriod
        new TimeCalculationDto(DayOfWeek.MONDAY, 3, currentTimeSecondCase)    |     432000     |     604800
        new TimeCalculationDto(DayOfWeek.TUESDAY, 3, currentTimeSecondCase)   |     518400     |     604800
        new TimeCalculationDto(DayOfWeek.WEDNESDAY, 3, currentTimeSecondCase) |       0        |     604800
        new TimeCalculationDto(DayOfWeek.THURSDAY, 3, currentTimeSecondCase)  |      86400     |     604800
        new TimeCalculationDto(DayOfWeek.FRIDAY, 3, currentTimeSecondCase)    |     172800     |     604800
        new TimeCalculationDto(DayOfWeek.SATURDAY, 3, currentTimeSecondCase)  |     259200     |     604800
        new TimeCalculationDto(DayOfWeek.SUNDAY, 3, currentTimeSecondCase)    |     345600     |     604800
    }
}
