package cv.vladislavchunikhin.discord.discord;

import cv.vladislavchunikhin.discord.properties.DiscordProperties;
import cv.vladislavchunikhin.discord.discord.dto.*;
import cv.vladislavchunikhin.discord.http.GeneralResponse;
import cv.vladislavchunikhin.discord.http.HttpCodeWithMessageDto;
import cv.vladislavchunikhin.discord.web.payload.DiscordDataTaskPayload;
import cv.vladislavchunikhin.discord.web.payload.SimpleNotificationPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of {@link DiscordService}.
 */
@Service
@RequiredArgsConstructor
public class DiscordServiceImpl implements DiscordService {
    /**
     * Error message that throws if notification sending failed.
     */
    public static final String ERROR_MESSAGE_WHEN_NOTIFICATION_SENDING = "Sending message failed. To find out detailed reason you should look through server logs.";
    /**
     * Message template for discord notification content.
     */
    private static final String MESSAGE_TEMPLATE = "%s \nAnnouncement: %s";
    /**
     * User agent value.
     */
    public static final String USER_AGENT = "Java-DiscordWebhook-By-Vladislav-Chunikhin";
    /**
     * Discord properties.
     */
    private final DiscordProperties properties;
    /**
     * Component containing some methods for Discord API.
     */
    private final DiscordComponent discordComponent;
    /**
     * Component for working with scheduled tasks.
     */
    private final ScheduledTaskComponent scheduledTaskComponent;
    /**
     * Component that calculates time for tasks.
     */
    private final TimeCalculationComponent timeCalculationComponent;

    /**
     * @param payload payload for notification sending.
     * @return if notification sending is successful then default {@link GeneralResponse} else http code = 500 plus error message.
     */
    @Override
    public GeneralResponse sendNotification(@NonNull final SimpleNotificationPayload payload) {
        boolean sendingIsSuccess = discordComponent.sendNotification(this.getDiscordDto(payload));
        return sendingIsSuccess ? new GeneralResponse() : new GeneralResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ERROR_MESSAGE_WHEN_NOTIFICATION_SENDING);
    }

    /**
     * @param payload payload for notification task creating.
     * @return {@link GeneralResponse} with task id as UUID.
     */
    @Override
    public GeneralResponse createNotificationTask(@NonNull final DiscordDataTaskPayload payload) {
        TimeCalculationDto timeCalculationDto = new TimeCalculationDto(payload.getDayOfWeek(), payload.getHours(), LocalDateTime.now().withNano(0));
        SecPeriodDto secPeriodDto = timeCalculationComponent.calculateSecPeriodFromNowToFixedTime(timeCalculationDto);
        Runnable task = () -> discordComponent.sendNotification(this.getDiscordDto(payload));
        ScheduleTaskDto scheduleTaskDto = new ScheduleTaskDto(task, secPeriodDto.getDelay(), secPeriodDto.getPeriod(), TimeUnit.SECONDS, payload.getDescription());
        UUID scheduledTask = scheduledTaskComponent.createScheduledTask(scheduleTaskDto);
        return new GeneralResponse(scheduledTask);
    }

    /**
     * If id is null all tasks will be disabled, otherwise by id.
     * @param id identifier of recurring task.
     * @return {@link GeneralResponse} with http code and relevant message.
     */
    @Override
    public GeneralResponse shutdownNotificationTask(@Nullable UUID id) {
        HttpCodeWithMessageDto dto;
        if (id == null) {
            dto = scheduledTaskComponent.turnOffAllTasks();
        } else {
            dto = scheduledTaskComponent.turnOffTaskById(id);
        }
        return new GeneralResponse(dto.getCode().value(), dto.getMessage());
    }

    /**
     * @return {@link GeneralResponse} with all tasks as map.
     */
    @Override
    public GeneralResponse getAllNotificationTasks() {
        final Map<UUID, ScheduledExecutorServiceDto> tasks = scheduledTaskComponent.getAllTasks();
        return new GeneralResponse(tasks);
    }

    /**
     * @param payload {@link SimpleNotificationPayload}.
     * @return {@link DiscordDto}.
     */
    private DiscordDto getDiscordDto(@NonNull final SimpleNotificationPayload payload) {
        final String content = String.format(MESSAGE_TEMPLATE, properties.getMention(), payload.getContent());
        return new DiscordDto(properties.getWebhookUrl(), content, properties.getUsername(), USER_AGENT);
    }
}
