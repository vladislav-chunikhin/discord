package cv.vladislavchunikhin.discord.http;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * Response structure for positive response with data.
 *
 * @param <T> type data.
 */
@Getter
@Setter
@ToString(callSuper = true)
public class PositiveResponse<T> extends GeneralResponse {
    /**
     * Main data.
     */
    private final T data;

    /**
     * @param data main data.
     */
    public PositiveResponse(T data) {
        super(HttpStatus.OK);
        this.data = data;
    }
}
