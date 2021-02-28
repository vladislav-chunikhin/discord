package cv.vladislavchunikhin.discord.spock.mock

import cv.vladislavchunikhin.discord.discord.DiscordComponent
import cv.vladislavchunikhin.discord.discord.DiscordServiceImpl
import cv.vladislavchunikhin.discord.discord.dto.DiscordDto
import cv.vladislavchunikhin.discord.properties.DiscordProperties
import cv.vladislavchunikhin.discord.spock.ApiBaseSpec
import cv.vladislavchunikhin.discord.web.payload.SimpleNotificationPayload

abstract class DiscordComponentMockerSpec extends ApiBaseSpec {
    abstract DiscordComponent getDiscordComponent()
    abstract DiscordProperties getDiscordProperties()

    protected void mockNotificationSending(int expectedNumberOfInvocation, boolean expectedResult, final SimpleNotificationPayload payload) {
        expectedNumberOfInvocation * discordComponent.sendNotification({
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
