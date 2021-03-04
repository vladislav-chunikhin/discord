package cv.vladislavchunikhin.discord.discord;

import cv.vladislavchunikhin.discord.http.GeneralResponse;
import cv.vladislavchunikhin.discord.web.payload.DiscordDataTaskPayload;
import cv.vladislavchunikhin.discord.web.payload.SimpleNotificationPayload;

import java.util.UUID;

/**
 * General discord service containing all business methods.
 */
public interface DiscordService {
    /**
     * @param payload payload for notification sending.
     * @return {@link GeneralResponse}.
     */
    GeneralResponse sendNotification(SimpleNotificationPayload payload);

    /**
     * @param payload payload for notification task creating.
     * @return {@link GeneralResponse}.
     */
    GeneralResponse createNotificationTask(DiscordDataTaskPayload payload);

    /**
     * @param id identifier of recurring task.
     * @return {@link GeneralResponse}.
     */
    GeneralResponse shutdownNotificationTask(UUID id);

    /**
     * @return {@link GeneralResponse}.
     */
    GeneralResponse getAllNotificationTasks();
}
