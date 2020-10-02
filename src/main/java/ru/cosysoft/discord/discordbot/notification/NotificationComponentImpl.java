package ru.cosysoft.discord.discordbot.notification;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.cosysoft.discord.discordbot.discord.DiscordWebhook;


@Component
@RequiredArgsConstructor
public class NotificationComponentImpl implements NotificationComponent {

    @Value("${discrod.mention.here}")
    private String mentionHere;

    @Value("${tecforce.daily-status.url}")
    private String dailyStatusUrl;

    @Override
    @SneakyThrows
    public void sendDailyStatusNotification() {
        final String url = "https://discordapp.com/api/webhooks/761477551461629972/omnYbAgM1YG8KS794jXgZhwzM1GYK9-p7Ncv0mSzp4ZXfRzZJ70AdJk9hDUMF-drCpPn";
        final DiscordWebhook discordWebhook = new DiscordWebhook(
            url,
            mentionHere + " Заполните пожалуйста документ по ежедневному статусу: " + dailyStatusUrl,
            "Notification",
            ""
        );
        discordWebhook.execute();
    }
}