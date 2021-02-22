package cv.vladislavchunikhin.discord.web.payload;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.DayOfWeek;

@EqualsAndHashCode(callSuper = true)
@Data
public class DiscordDataTaskPayload extends SimpleNotificationPayload {
    private DayOfWeek dayOfWeek;
    @Min(value = 0) @Max(value = 23)
    private Integer hours;
    private String description;
}
