package cv.vladislavchunikhin.discord.discord.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import cv.vladislavchunikhin.discord.util.JsonPojo;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DiscordPojo implements JsonPojo {
    @JsonProperty("content")
    private String content;

    @JsonProperty("username")
    private String username;

    @JsonProperty("avatar_url")
    private String avatarUrl;
}
