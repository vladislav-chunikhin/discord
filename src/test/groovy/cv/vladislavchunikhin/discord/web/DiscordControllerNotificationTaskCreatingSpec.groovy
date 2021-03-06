package cv.vladislavchunikhin.discord.web

import cv.vladislavchunikhin.discord.discord.DiscordComponent
import cv.vladislavchunikhin.discord.http.PositiveResponse
import cv.vladislavchunikhin.discord.http.code.ResponseType
import cv.vladislavchunikhin.discord.spock.ApiBaseSpec
import cv.vladislavchunikhin.discord.web.payload.DiscordDataTaskPayload
import groovy.sql.Sql
import org.spockframework.spring.SpringBean
import spock.lang.Shared

import java.time.DayOfWeek

/**
 * Integration tests for {@link DiscordController#createNotificationTask}.
 */
class DiscordControllerNotificationTaskCreatingSpec extends ApiBaseSpec {
    @SpringBean DiscordComponent discordComponent = Mock()
    @Shared sql = Sql.newInstance("jdbc:h2:mem:", "org.h2.Driver")

    def "notification creation task"() {
        given:
        def payload = getPayload(dayOfWeek as DayOfWeek, hours)
        when:
        def resultActions = this.performPost(NOTIFICATION_TASK_CREATING_URL, payload)
        then:
        checkResultOnSuccessful(resultActions)
        def response = this.parseToGeneralResponse(resultActions)
        response.responseType == ResponseType.OK
        ((PositiveResponse) response).data != null
        where:
        [dayOfWeek, hours] << [
                [DayOfWeek.MONDAY, 0],
                [DayOfWeek.TUESDAY, 0],
                [DayOfWeek.WEDNESDAY, 0],
                [DayOfWeek.THURSDAY, 0],
                [DayOfWeek.FRIDAY, 0],
                [DayOfWeek.SATURDAY, 0],
                [DayOfWeek.SUNDAY, 0],

                [DayOfWeek.MONDAY, 23],
                [DayOfWeek.TUESDAY, 23],
                [DayOfWeek.WEDNESDAY, 23],
                [DayOfWeek.THURSDAY, 23],
                [DayOfWeek.FRIDAY, 23],
                [DayOfWeek.SATURDAY, 23],
                [DayOfWeek.SUNDAY, 23],

                [DayOfWeek.MONDAY, 14],
                [DayOfWeek.TUESDAY, 14],
                [DayOfWeek.WEDNESDAY, 14],
                [DayOfWeek.THURSDAY, 14],
                [DayOfWeek.FRIDAY, 14],
                [DayOfWeek.SATURDAY, 14],
                [DayOfWeek.SUNDAY, 14]
        ]
    }

    def "notification creation task test with sql query"() {
        given:
        def payload = getPayload(DayOfWeek.of(dayOfWeek as int), hours as int)
        when:
        def resultActions = this.performPost(NOTIFICATION_TASK_CREATING_URL, payload)
        then:
        checkResultOnSuccessful(resultActions)
        def response = this.parseToGeneralResponse(resultActions)
        response.responseType == ResponseType.OK
        ((PositiveResponse) response).data != null
        where:
        [dayOfWeek, hours] << sql.rows("SELECT 3 AS dayOfWeek, 12 AS hours")
    }

    private static DiscordDataTaskPayload getPayload(final DayOfWeek dayOfWeek, final Integer hours) {
        def payload = new DiscordDataTaskPayload()
        payload.content = UUID.randomUUID().toString()
        payload.dayOfWeek = dayOfWeek
        payload.description = UUID.randomUUID().toString()
        payload.hours = hours
        return payload
    }
}
