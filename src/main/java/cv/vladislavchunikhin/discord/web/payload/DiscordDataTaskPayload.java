package cv.vladislavchunikhin.discord.web.payload;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.DayOfWeek;

/**
 * Payload for creation notification task.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DiscordDataTaskPayload extends SimpleNotificationPayload {
    /**
     * {@link DayOfWeek} as MONDAY, TUESDAY and etc.
     */
    private DayOfWeek dayOfWeek;
    /**
     * Hour. from 0 to 23.
     */
    @Min(value = 0) @Max(value = 23)
    private Integer hours;
    /**
     * Short description of task.
     */
    private String description;
}
