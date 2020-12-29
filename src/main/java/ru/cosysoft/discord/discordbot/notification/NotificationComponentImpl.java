package ru.cosysoft.discord.discordbot.notification;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import ru.cosysoft.discord.discordbot.components.CommonProperties;
import ru.cosysoft.discord.discordbot.discord.DiscordWebhook;

@Component
@RequiredArgsConstructor
public class NotificationComponentImpl implements NotificationComponent {

    private static final String MESSAGE_TEMPLATE = "%s Заполните пожалуйста документ по ежедневному статусу: %s";

    private final CommonProperties properties;

    @Override
    @SneakyThrows
    public void sendDailyStatusNotification() {
        final DiscordWebhook discordWebhook = new DiscordWebhook(
            properties.getWebhookUrl(),
            String.format(MESSAGE_TEMPLATE, properties.getMention(), properties.getDailyStatusUrl()),
            properties.getUsername()
        );
        discordWebhook.execute();
    }
}