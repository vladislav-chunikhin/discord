package cv.vladislavchunikhin.discord.http;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * Response structure for errors.
 */
@Getter
@Setter
@ToString
public class NegativeResponse extends GeneralResponse {
    /**
     * Message (Optional). In most cases it is error message.
     */
    private String message;
    /**
     * Custom error type.
     */
    private final ErrorType errorType;
    /**
     * Custom error code.
     */
    private final int errorCode;

    /**
     * @param httpCode  {@link HttpStatus}.
     * @param message   error message.
     * @param errorType {@link ErrorType}.
     */
    public NegativeResponse(final HttpStatus httpCode, final String message, final ErrorType errorType) {
        super(httpCode);
        this.message = message;
        this.errorType = errorType;
        this.errorCode = errorType.getCode();
    }

    /**
     * @param httpCode  {@link HttpStatus}.
     * @param errorType {@link ErrorType}.
     */
    public NegativeResponse(final HttpStatus httpCode, final ErrorType errorType) {
        super(httpCode);
        this.errorType = errorType;
        this.errorCode = errorType.getCode();
    }
}
