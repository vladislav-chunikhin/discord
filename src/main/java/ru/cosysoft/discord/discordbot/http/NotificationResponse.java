package ru.cosysoft.discord.discordbot.http;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
public class NotificationResponse {
    @JsonIgnore
    private int httpCode;
    private String message;
    private Object data;

    public NotificationResponse() {
        this.httpCode = HttpStatus.OK.value();
        this.message = HttpStatus.OK.name();
    }

    public NotificationResponse(final Object data) {
        this.data = data;
        this.httpCode = HttpStatus.OK.value();
        this.message = HttpStatus.OK.name();
    }

    public NotificationResponse(final int httpCode, final String message) {
        this.httpCode = httpCode;
        this.message = message;
    }

    public NotificationResponse(final String message) {
        this.httpCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.message = message;
    }
}
