package cv.vladislavchunikhin.discord.config;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Jackson configuration.
 */
@Configuration
public class JacksonConfig {

    /**
     * @return Customized {@link ObjectMapper}.
     */
    @Bean
    public ObjectMapper objectMapper() {
        return JacksonConfig.getObjectMapperInstance();
    }

    /**
     * @return Customized {@link ObjectMapper}
     */
    public static ObjectMapper getObjectMapperInstance() {
        final ObjectMapper mapper = new ObjectMapper();
        JacksonConfig.configure(mapper);
        return mapper;
    }

    /**
     * Registration modules and configuration for {@link ObjectMapper}.
     *
     * @param mapper instance of {@link ObjectMapper}.
     */
    private static void configure(final ObjectMapper mapper) {
        mapper.registerModule(new Jdk8Module());
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
