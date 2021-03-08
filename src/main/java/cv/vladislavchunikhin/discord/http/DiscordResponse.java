package cv.vladislavchunikhin.discord.http;

import org.springframework.http.HttpStatus;

/**
 * Main response interface.
 */
public interface DiscordResponse {
    /**
     * HTTP code. (for example, 200, 400, 500 and etc.)
     */
    HttpStatus getHttpCode();
}
