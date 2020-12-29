package ru.cosysoft.discord.discordbot.notification;

import ru.cosysoft.discord.discordbot.http.NotificationResponse;

public interface NotificationService {
    NotificationResponse sendTechforceDailyStatusNotification();
}
