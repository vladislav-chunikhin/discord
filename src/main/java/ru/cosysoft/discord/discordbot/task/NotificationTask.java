package ru.cosysoft.discord.discordbot.task;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.cosysoft.discord.discordbot.notification.NotificationComponent;

@Component
@RequiredArgsConstructor
public class NotificationTask {

    private final NotificationComponent notificationComponent;

    @Scheduled(cron = "0 0 10 * * ?")
    public void sendDailyStatusNotification() {
        this.notificationComponent.sendDailyStatusNotification();
    }
}
