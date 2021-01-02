package cv.vladislavchunikhin.discord.components;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CommonProperties {
    @Value("${discord.mention.my-id}")
    private String mention;

    @Value("${discord.webhook-url}")
    private String webhookUrl;

    @Value("${discord.username}")
    private String username;
}
