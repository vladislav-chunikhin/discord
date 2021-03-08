package cv.vladislavchunikhin.discord.web;

import cv.vladislavchunikhin.discord.http.DiscordResponse;
import cv.vladislavchunikhin.discord.http.GeneralResponse;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;

/**
 * Base controller with common methods. */
public abstract class BaseController {
    /**
     * @param response {@link DiscordResponse}.
     * @return response as {@link DiscordResponse} wrapped in {@link ResponseEntity}.
     */
    protected ResponseEntity<DiscordResponse> getApiResponse(@NonNull final DiscordResponse response) {
        return ResponseEntity.status(response.getHttpCode()).body(response);
    }
}
