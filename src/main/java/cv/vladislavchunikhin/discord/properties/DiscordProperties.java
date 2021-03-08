package cv.vladislavchunikhin.discord.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Discord properties.
 */
@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "discord")
public class DiscordProperties {
    /**
     * Mention id (for example, <@398522842049937435>).
     */
    private String mention;
    /**
     * Bot webhook.
     */
    private String webhookUrl;
    /**
     * Bot username.
     */
    private String username;
}
