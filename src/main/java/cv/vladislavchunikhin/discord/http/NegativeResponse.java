package cv.vladislavchunikhin.discord.http;

import cv.vladislavchunikhin.discord.http.code.ErrorType;
import cv.vladislavchunikhin.discord.http.code.ResponseType;
import lombok.*;
import org.springframework.http.HttpStatus;

/**
 * Response structure for errors.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class NegativeResponse extends GeneralResponse {
    /**
     * Message (Optional). In most cases it is error message.
     */
    private String message;
    /**
     * Custom error type.
     */
    private ErrorType errorType;
    /**
     * Custom error code.
     */
    private int errorCode;

    /**
     * @param httpCode  {@link HttpStatus}.
     * @param message   error message.
     * @param errorType {@link ErrorType}.
     */
    public NegativeResponse(@NonNull final HttpStatus httpCode, @NonNull final String message, @NonNull final ErrorType errorType) {
        super(httpCode, ResponseType.FAIL);
        this.message = message;
        this.errorType = errorType;
        this.errorCode = errorType.getCode();
    }

    /**
     * @param httpCode  {@link HttpStatus}.
     * @param errorType {@link ErrorType}.
     */
    public NegativeResponse(@NonNull final HttpStatus httpCode, @NonNull final ErrorType errorType) {
        super(httpCode, ResponseType.FAIL);
        this.errorType = errorType;
        this.errorCode = errorType.getCode();
    }
}
