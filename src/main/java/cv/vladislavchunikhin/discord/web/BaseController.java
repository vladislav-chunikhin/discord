package cv.vladislavchunikhin.discord.web;

import cv.vladislavchunikhin.discord.http.GeneralResponse;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;

/**
 * Base controller with common methods. */
public abstract class BaseController {
    /**
     * @param response {@link GeneralResponse}.
     * @return response as {@link GeneralResponse} wrapped in {@link ResponseEntity}.
     */
    protected ResponseEntity<GeneralResponse> getApiResponse(@NonNull final GeneralResponse response) {
        return ResponseEntity.status(response.getHttpCode()).body(response);
    }
}
