package cv.vladislavchunikhin.discord.discord;

import cv.vladislavchunikhin.discord.http.GeneralResponse;
import cv.vladislavchunikhin.discord.web.payload.DiscordDataTaskPayload;
import cv.vladislavchunikhin.discord.web.payload.SimpleNotificationPayload;

import java.util.UUID;

public interface DiscordService {
    GeneralResponse sendNotification(SimpleNotificationPayload payload);
    GeneralResponse createNotificationTask(DiscordDataTaskPayload payload);
    GeneralResponse shutdownNotificationTask(UUID id);
    GeneralResponse getAllNotificationTasks();
}
