package cv.vladislavchunikhin.discord.http;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * General structure http response.
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public abstract class GeneralResponse {
    /**
     * HTTP code. (for example, 200, 400, 500 and etc.)
     */
    @JsonIgnore private final HttpStatus httpCode;
}
