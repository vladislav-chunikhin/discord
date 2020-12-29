package ru.cosysoft.discord.discordbot.notification;

import ru.cosysoft.discord.discordbot.notification.dto.DiscordNotificationDto;

public interface NotificationComponent {
    boolean sendDiscordMessage(DiscordNotificationDto dto);
}
