package ru.cosysoft.discord.discordbot.notification.dto;

import lombok.Data;

@Data
public class DiscordNotificationDto {
    private final String webhookUrl;
    private final String content;
    private final String username;
    private final String userAgent;
    private String avatarUrl;
}
