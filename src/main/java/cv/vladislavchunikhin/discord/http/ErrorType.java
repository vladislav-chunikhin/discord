package cv.vladislavchunikhin.discord.http;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Custom error.
 */
@RequiredArgsConstructor
@Getter
public enum ErrorType {
    /**
     * Code that indicate problems connected with Discord.
     */
    DISCORD_ERROR(1000),
    /**
     * Code that indicate problems connected with server.
     */
    INTERNAL_SERVER_ERROR(1100);

    /**
     * Business code.
     */
    private final int code;
}
