package cv.vladislavchunikhin.discord.discord;

import java.io.OutputStream;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

import cv.vladislavchunikhin.discord.discord.pojo.DiscordPojo;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import cv.vladislavchunikhin.discord.discord.dto.DiscordDto;

/**
 * Implementation of {@link DiscordComponent}.
 */
@Component
@Slf4j
public class DiscordComponentImpl implements DiscordComponent {

    /**
     * @param dto discord data transfer object to execute something.
     * @return flag that indicate successful or failed method result.
     */
    @Override
    public boolean sendNotification(@NonNull final DiscordDto dto) {
        final DiscordPojo json = new DiscordPojo(dto.getContent(), dto.getUsername(), dto.getAvatarUrl());
        try {
            final URL url = new URL(dto.getWebhookUrl());
            final HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.addRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            connection.addRequestProperty(HttpHeaders.USER_AGENT, dto.getUserAgent());
            connection.setDoOutput(true);
            connection.setRequestMethod(HttpMethod.POST.name());

            final OutputStream stream = connection.getOutputStream();
            stream.write(json.toJsonString(false).getBytes());
            stream.flush();
            stream.close();

            connection.getInputStream().close();
            connection.disconnect();
            return true;
        } catch (final Exception ex) {
            log.error("DiscordComponent::sendNotification, error while sending", ex);
            return false;
        }
    }
}
