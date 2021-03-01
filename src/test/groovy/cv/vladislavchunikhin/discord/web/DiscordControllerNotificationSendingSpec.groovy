package cv.vladislavchunikhin.discord.web

import cv.vladislavchunikhin.discord.discord.DiscordComponent
import cv.vladislavchunikhin.discord.discord.DiscordServiceImpl
import cv.vladislavchunikhin.discord.properties.DiscordProperties
import cv.vladislavchunikhin.discord.spock.mock.DiscordComponentMockerSpec
import cv.vladislavchunikhin.discord.web.payload.SimpleNotificationPayload
import org.spockframework.spring.SpringBean

/**
 * Integration tests for {@link DiscordController#sendNotification}.
 */
class DiscordControllerNotificationSendingSpec extends DiscordComponentMockerSpec {
    @SpringBean DiscordComponent discordComponent = Mock()
    @SpringBean DiscordProperties discordProperties = Spy()

    def "successful notification sending"() {
        given:
        def payload = getPayload()
        this.mockNotificationSending(1,true, payload)
        when:
        def resultActions = this.performPost(NOTIFICATION_SENDING_URL, payload)
        then:
        1 * discordProperties.getMention()
        checkResultOnSuccessful(resultActions)
        def response = this.parseToGeneralResponse(resultActions)
        response.getData() == null
        response.getMessage() == "OK"
    }

    def "notification sending failed"() {
        given:
        def payload = getPayload()
        this.mockNotificationSending(1,false, payload)
        when:
        def resultActions = this.performPost(NOTIFICATION_SENDING_URL, payload)
        then:
        1 * discordProperties.getMention()
        checkResultOnServerError(resultActions)
        def response = this.parseToGeneralResponse(resultActions)
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
}
