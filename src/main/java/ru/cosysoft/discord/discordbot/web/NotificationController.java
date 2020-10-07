package ru.cosysoft.discord.discordbot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cosysoft.discord.discordbot.notification.NotificationComponent;

@RestController
@RequestMapping("/notify")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationComponent notificationComponent;

    //https://discordbotcosy.herokuapp.com/notify/daily-status
    @GetMapping("/daily-status")
    public void sendDailyStatusNotification() {
        this.notificationComponent.sendDailyStatusNotification();
    }
}
