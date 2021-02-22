package cv.vladislavchunikhin.discord.web

import cv.vladislavchunikhin.discord.discord.DiscordComponent
import cv.vladislavchunikhin.discord.discord.dto.DiscordDto
import cv.vladislavchunikhin.discord.spock.ApiBaseSpec
import cv.vladislavchunikhin.discord.web.payload.SimpleNotificationPayload
import org.spockframework.spring.SpringBean

class DiscordControllerSpec extends ApiBaseSpec {
    @SpringBean DiscordComponent discordComponent = Mock()

    def "notification sending"() {
        given:
        def content = UUID.randomUUID().toString()
        def payload = new SimpleNotificationPayload()
        payload.content = content
        discordComponent.sendNotification(_ as DiscordDto) >> true
        when:
        def resultActions = this.performPost(NOTIFICATION_SENDING_URL, payload)
        then:
        checkResultOnSuccessful(resultActions)
        def response = this.parseToGenericResponse(resultActions)
        response.getData() == null
        response.getHttpCode() == 200
        response.getMessage() == "OK"
    }
}
