package cv.vladislavchunikhin.discord.http;

import cv.vladislavchunikhin.discord.http.code.ResponseType;
import lombok.*;
import org.springframework.http.HttpStatus;

/**
 * Response structure for positive response with data.
 *
 * @param <T> type data.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PositiveResponse<T> extends GeneralResponse {
    /**
     * Main data.
     */
    private T data;

    /**
     * @param data main data.
     */
    public PositiveResponse(@NonNull T data) {
        super(HttpStatus.OK, ResponseType.OK);
        this.data = data;
    }
}
