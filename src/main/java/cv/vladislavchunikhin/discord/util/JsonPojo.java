package cv.vladislavchunikhin.discord.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import cv.vladislavchunikhin.discord.config.JacksonConfig;

/**
 * Used for marking json pojo.
 * Also contains some helper methods for common cases of json processing.
 */
public interface JsonPojo {
    /**
     * @param usePrettyPrinter if true - will convert with {@link ObjectMapper#writerWithDefaultPrettyPrinter()}.
     * @return json string representation of this json pojo.
     */
    @JsonIgnore
    default String toJsonString(final boolean usePrettyPrinter) {
        final ObjectMapper mapper = JacksonConfig.getObjectMapperInstance();
        mapper.registerModule(new JavaTimeModule());
        try {
            return usePrettyPrinter
                    ? mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this)
                    : mapper.writeValueAsString(this);
        } catch (final JsonProcessingException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
}
