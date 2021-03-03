package cv.vladislavchunikhin.discord.discord.dto;

import lombok.Data;

/**
 * Discord data transfer object to execute something.
 */
@Data
public class DiscordDto {
    /**
     * Webhook. It is necessary to retrieve from Discord.
     */
    private final String webhookUrl;
    /**
     * Message content.
     */
    private final String content;
    /**
     * Bot username.
     */
    private final String username;
    /**
     * User agent name.
     */
    private final String userAgent;
    /**
     * Optional. Avatar image url.
     */
    private String avatarUrl;
}
