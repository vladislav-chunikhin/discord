package cv.vladislavchunikhin.discord.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "discord")
public class DiscordProperties {
    private String mention;
    private String webhookUrl;
    private String username;
}
