package cv.vladislavchunikhin.discord.web

import cv.vladislavchunikhin.discord.discord.DiscordComponent
import cv.vladislavchunikhin.discord.discord.DiscordServiceImpl
import cv.vladislavchunikhin.discord.discord.dto.DiscordDto
import cv.vladislavchunikhin.discord.properties.DiscordProperties
import cv.vladislavchunikhin.discord.spock.ApiBaseSpec
import cv.vladislavchunikhin.discord.web.payload.SimpleNotificationPayload
import org.spockframework.spring.SpringBean

class DiscordControllerSpec extends ApiBaseSpec {
    @SpringBean DiscordComponent discordComponent = Mock()
    @SpringBean DiscordProperties discordProperties = Spy()

    def "successful notification sending"() {
        given:
        def payload = getPayload()
        this.mockNotificationSending(true, payload)
        when:
        def resultActions = this.performPost(NOTIFICATION_SENDING_URL, payload)
        then:
        1 * discordProperties.getMention()
        checkResultOnSuccessful(resultActions)
        def response = this.parseToGenericResponse(resultActions)
        response.getData() == null
        response.getMessage() == "OK"
    }

    def "notification sending failed"() {
        given:
        def payload = getPayload()
        this.mockNotificationSending(false, payload)
        when:
        def resultActions = this.performPost(NOTIFICATION_SENDING_URL, payload)
        then:
        1 * discordProperties.getMention()
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

    private void mockNotificationSending(boolean expectedResult, final SimpleNotificationPayload payload) {
        1 * discordComponent.sendNotification({
            DiscordDto dto ->
                (
                        dto.content != null
                                && dto.content.contains(payload.getContent())
                                && dto.userAgent == DiscordServiceImpl.USER_AGENT
                                && dto.avatarUrl == null
                                && dto.webhookUrl == discordProperties.webhookUrl
                                && dto.username == discordProperties.username
                )
        }) >> expectedResult
    }
}
