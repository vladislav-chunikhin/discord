package ru.cosysoft.discord.discordbot.discord;

import ru.cosysoft.discord.discordbot.notification.dto.DiscordNotificationDto;

public interface DiscordComponent {
    boolean sendMessage(DiscordNotificationDto dto);
}
