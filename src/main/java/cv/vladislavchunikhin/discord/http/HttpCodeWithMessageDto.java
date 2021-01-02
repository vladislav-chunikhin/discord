package cv.vladislavchunikhin.discord.http;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class HttpCodeWithMessageDto {
    private final HttpStatus code;
    private final String message;

    public HttpCodeWithMessageDto(final HttpStatus code, final String message) {
        this.code = code;
        this.message = message;
    }

    public HttpCodeWithMessageDto() {
        this.code = HttpStatus.OK;
        this.message = HttpStatus.OK.name();
    }
}
