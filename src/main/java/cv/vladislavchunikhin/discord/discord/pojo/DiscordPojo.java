package cv.vladislavchunikhin.discord.discord.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import cv.vladislavchunikhin.discord.util.JsonPojo;
import lombok.*;

/**
 * Discord payload to execute something via webhook.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DiscordPojo implements JsonPojo {

    /**
     * Content that we are going to send in Discord.
     */
    @JsonProperty("content")
    private String content;

    /**
     * Username bot.
     */
    @JsonProperty("username")
    private String username;

    /**
     * Avatar image url bot.
     */
    @JsonProperty("avatar_url")
    private String avatarUrl;
}
