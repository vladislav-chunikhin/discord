package ru.cosysoft.discord.discordbot.discord;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import lombok.Data;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

@Data
public class DiscordWebhook {
    private final String url;
    private final String content;
    private final String username;

    private String avatarUrl;

    public void execute() throws IOException {
        final JSONObject json = new JSONObject();

        json.put(DiscordConstants.CONTENT_KEY, this.content);
        json.put(DiscordConstants.USERNAME_KEY, this.username);
        json.put(DiscordConstants.AVATAR_UTL_KEY, this.avatarUrl);

        final URL url = new URL(this.url);
        final HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.addRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        connection.addRequestProperty(HttpHeaders.USER_AGENT, "Java-DiscordWebhook-By-Vladislav-Chunikhin");
        connection.setDoOutput(true);
        connection.setRequestMethod(HttpMethod.POST.name());

        final OutputStream stream = connection.getOutputStream();
        stream.write(json.toString().getBytes());
        stream.flush();
        stream.close();

        connection.getInputStream().close();
        connection.disconnect();
    }
}
