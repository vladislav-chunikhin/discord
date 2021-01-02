package cv.vladislavchunikhin.discord.config;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper() {
        return JacksonConfig.getObjectMapperInstance();
    }

    public static ObjectMapper getObjectMapperInstance() {
        final ObjectMapper mapper = new ObjectMapper();
        JacksonConfig.configure(mapper);
        return mapper;
    }

    private static void configure(final ObjectMapper mapper) {
        mapper.registerModule(new Jdk8Module());
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
