package cv.vladislavchunikhin.discord.web.payload;

import cv.vladislavchunikhin.discord.util.JsonPojo;
import lombok.Data;

/**
 * Payload for simple notification.
 */
@Data
public class SimpleNotificationPayload implements JsonPojo {
    /**
     * Message content.
     */
    private String content;
}
