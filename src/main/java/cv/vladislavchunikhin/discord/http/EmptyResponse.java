package cv.vladislavchunikhin.discord.http;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * Empty positive response.
 */
@Getter
@ToString(callSuper = true)
public class EmptyResponse extends GeneralResponse {
    /**
     * Default constructor for empty positive response.
     */
    public EmptyResponse() {
        super(HttpStatus.OK);
    }
}
