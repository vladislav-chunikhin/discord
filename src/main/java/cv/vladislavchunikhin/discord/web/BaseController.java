package cv.vladislavchunikhin.discord.web;

import cv.vladislavchunikhin.discord.http.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * Base controller with common methods. */
public abstract class BaseController {
    protected ResponseEntity<GeneralResponse> getApiResponse(final GeneralResponse response) {
        return ResponseEntity.status(response.getHttpCode()).body(response);
    }
}
