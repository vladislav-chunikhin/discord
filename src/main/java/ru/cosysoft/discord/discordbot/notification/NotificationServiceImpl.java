package ru.cosysoft.discord.discordbot.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cosysoft.discord.discordbot.components.CommonProperties;
import ru.cosysoft.discord.discordbot.http.NotificationResponse;
import ru.cosysoft.discord.discordbot.notification.dto.DiscordNotificationDto;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private static final String MESSAGE_TEMPLATE = "%s Заполните пожалуйста документ по ежедневному статусу: %s";
    private static final String USER_AGENT = "Java-DiscordWebhook-By-Vladislav-Chunikhin";

    private final CommonProperties properties;
    private final NotificationComponent notificationComponent;

    @Override
    public NotificationResponse sendTechforceDailyStatusNotification() {
        final String content = String.format(MESSAGE_TEMPLATE, properties.getMention(), properties.getDailyStatusUrl());
        DiscordNotificationDto dto = new DiscordNotificationDto(properties.getWebhookUrl(), content, properties.getUsername(), USER_AGENT);
        boolean sendingIsSuccess = notificationComponent.sendDiscordMessage(dto);
        return sendingIsSuccess ? new NotificationResponse() : new NotificationResponse("Sending message failed");
    }
}
