package cv.vladislavchunikhin.discord.web.payload;

import cv.vladislavchunikhin.discord.util.JsonPojo;
import lombok.Data;

@Data
public class SimpleNotificationPayload implements JsonPojo {
    private String content;
}
