package cv.vladislavchunikhin.discord.http;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneralResponse {
    @JsonIgnore
    private int httpCode;
    private String message;
    private Object data;

    public GeneralResponse() {
        this.httpCode = HttpStatus.OK.value();
        this.message = HttpStatus.OK.name();
    }

    public GeneralResponse(final Object data) {
        this.data = data;
        this.httpCode = HttpStatus.OK.value();
        this.message = HttpStatus.OK.name();
    }

    public GeneralResponse(final int httpCode, final String message) {
        this.httpCode = httpCode;
        this.message = message;
    }
}
