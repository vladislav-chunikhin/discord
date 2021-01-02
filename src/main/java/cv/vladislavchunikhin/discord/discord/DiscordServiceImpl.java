package cv.vladislavchunikhin.discord.discord;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import cv.vladislavchunikhin.discord.components.CommonProperties;
import cv.vladislavchunikhin.discord.http.GeneralResponse;
import cv.vladislavchunikhin.discord.discord.dto.DiscordDto;

@Service
@RequiredArgsConstructor
public class DiscordServiceImpl implements DiscordService {
    private static final String MESSAGE_TEMPLATE = "%s Заполните пожалуйста документ по ежедневному статусу: %s";
    private static final String USER_AGENT = "Java-DiscordWebhook-By-Vladislav-Chunikhin";

    private final CommonProperties properties;
    private final DiscordComponent discordComponent;

    @Override
    public GeneralResponse sendMessage() {
        final String content = String.format(MESSAGE_TEMPLATE, properties.getMention(), "test");
        DiscordDto dto = new DiscordDto(
                properties.getWebhookUrl(),
                content,
                properties.getUsername(),
                USER_AGENT
        );
        boolean sendingIsSuccess = discordComponent.sendMessage(dto);
        return sendingIsSuccess
                ? new GeneralResponse()
                : new GeneralResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Sending message failed");
    }
}
