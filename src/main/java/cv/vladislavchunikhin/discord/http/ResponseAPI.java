package cv.vladislavchunikhin.discord.http;

import cv.vladislavchunikhin.discord.http.code.ErrorType;
import cv.vladislavchunikhin.discord.http.code.ResponseType;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;

/**
 * Utility class to create a response.
 */
@UtilityClass
public class ResponseAPI {
    /**
     * @param data some data.
     * @param <T>  data type.
     * @return data as {@link PositiveResponse}.
     */
    public <T> PositiveResponse<T> positiveResponse(@NonNull T data) {
        return new PositiveResponse<>(data);
    }

    /**
     * @return empty positive response.
     */
    public GeneralResponse emptyPositiveResponse() {
        return new GeneralResponse(HttpStatus.OK, ResponseType.OK);
    }

    /**
     * @param httpStatus {@link HttpStatus}.
     * @param message    error message.
     * @param errorType  {@link ErrorType}.
     * @return {@link NegativeResponse}.
     */
    public NegativeResponse negativeResponse(@NonNull final HttpStatus httpStatus, @NonNull final String message, @NonNull final ErrorType errorType) {
        return new NegativeResponse(httpStatus, message, errorType);
    }

    /**
     * @param httpStatus {@link HttpStatus}.
     * @param errorType  {@link ErrorType}.
     * @return {@link NegativeResponse}.
     */
    public NegativeResponse negativeResponse(@NonNull final HttpStatus httpStatus, @NonNull final ErrorType errorType) {
        return new NegativeResponse(httpStatus, errorType);
    }
}
