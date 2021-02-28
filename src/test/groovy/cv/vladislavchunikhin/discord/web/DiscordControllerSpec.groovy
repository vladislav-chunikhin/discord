package cv.vladislavchunikhin.discord.web

import cv.vladislavchunikhin.discord.discord.DiscordComponent
import cv.vladislavchunikhin.discord.discord.DiscordServiceImpl
import cv.vladislavchunikhin.discord.discord.dto.DiscordDto
import cv.vladislavchunikhin.discord.spock.ApiBaseSpec
import cv.vladislavchunikhin.discord.web.payload.SimpleNotificationPayload
import org.spockframework.spring.SpringBean

class DiscordControllerSpec extends ApiBaseSpec {
    @SpringBean DiscordComponent discordComponent = Mock()

    def "successful notification sending"() {
        given:
        this.mockNotificationSending(true)
        def payload = getPayload()
        when:
        def resultActions = this.performPost(NOTIFICATION_SENDING_URL, payload)
        then:
        checkResultOnSuccessful(resultActions)
        def response = this.parseToGenericResponse(resultActions)
        response.getData() == null
        response.getMessage() == "OK"
    }

    def "notification sending failed"() {
        given:
        this.mockNotificationSending(false)
        def payload = getPayload()
        when:
        def resultActions = this.performPost(NOTIFICATION_SENDING_URL, payload)
        then:
        checkResultOnServerError(resultActions)
        def response = this.parseToGenericResponse(resultActions)
        response.getData() == null
        response.getMessage() == DiscordServiceImpl.ERROR_MESSAGE_WHEN_NOTIFICATION_SENDING
    }

    private static SimpleNotificationPayload getPayload(final String content) {
        def payload = new SimpleNotificationPayload()
        payload.content = Optional.ofNullable(content).orElse(UUID.randomUUID().toString())
        return payload
    }

    private static SimpleNotificationPayload getPayload() {
        return getPayload(null)
    }

    private void mockNotificationSending(final boolean expectedResult) {
        1 * discordComponent.sendNotification(_ as DiscordDto) >> expectedResult
    }
}
