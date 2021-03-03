package cv.vladislavchunikhin.discord.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * General property configuration.
 */
@PropertySources({
        @PropertySource("classpath:discord.properties")
})
@Configuration
public class PropertySourceConfig {
}
