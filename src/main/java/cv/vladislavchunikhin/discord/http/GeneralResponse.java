package cv.vladislavchunikhin.discord.http;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cv.vladislavchunikhin.discord.http.code.ResponseType;
import lombok.*;
import org.springframework.http.HttpStatus;

/**
 * General structure http response.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GeneralResponse implements DiscordResponse {
    /**
     * HTTP code. (for example, 200, 400, 500 and etc.)
     */
    @JsonIgnore private HttpStatus httpCode;
    /**
     * Either OK or FAIL.
     */
    private ResponseType responseType;
}
