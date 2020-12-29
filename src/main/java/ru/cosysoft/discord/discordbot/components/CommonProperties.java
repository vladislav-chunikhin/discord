package ru.cosysoft.discord.discordbot.components;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CommonProperties {
    @Value("${discord.mention.my-id}")
    private String mention;

    @Value("${tecforce.daily-status.url}")
    private String dailyStatusUrl;

    @Value("${discord.webhook-url}")
    private String webhookUrl;

    @Value("${discord.username}")
    private String username;
}
