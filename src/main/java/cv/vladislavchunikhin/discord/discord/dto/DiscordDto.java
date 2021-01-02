package cv.vladislavchunikhin.discord.discord.dto;

import lombok.Data;

@Data
public class DiscordDto {
    private final String webhookUrl;
    private final String content;
    private final String username;
    private final String userAgent;
    private String avatarUrl;
}
