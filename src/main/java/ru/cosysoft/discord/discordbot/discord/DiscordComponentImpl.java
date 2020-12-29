package ru.cosysoft.discord.discordbot.discord;

import java.io.OutputStream;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import ru.cosysoft.discord.discordbot.notification.dto.DiscordNotificationDto;

@Component
@Slf4j
public class DiscordComponentImpl implements DiscordComponent {
    @Override
    public boolean sendMessage(@NonNull final DiscordNotificationDto dto) {
        final JSONObject json = new JSONObject();
        json.put(DiscordConstants.CONTENT_KEY, dto.getContent());
        json.put(DiscordConstants.USERNAME_KEY, dto.getUsername());
        json.put(DiscordConstants.AVATAR_UTL_KEY, dto.getAvatarUrl());

        try {
            final URL url = new URL(dto.getWebhookUrl());
            final HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.addRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            connection.addRequestProperty(HttpHeaders.USER_AGENT, dto.getUserAgent());
            connection.setDoOutput(true);
            connection.setRequestMethod(HttpMethod.POST.name());

            final OutputStream stream = connection.getOutputStream();
            stream.write(json.toString().getBytes());
            stream.flush();
            stream.close();

            connection.getInputStream().close();
            connection.disconnect();
            return true;
        } catch (final Exception ex) {
            log.error("DiscordComponent::sendMessage, error while sending", ex);
            return false;
        }
    }
}
