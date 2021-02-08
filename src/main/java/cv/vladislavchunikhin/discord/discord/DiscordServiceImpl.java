package cv.vladislavchunikhin.discord.discord;

import cv.vladislavchunikhin.discord.components.CommonProperties;
import cv.vladislavchunikhin.discord.discord.dto.DiscordDto;
import cv.vladislavchunikhin.discord.discord.dto.ScheduleTaskDto;
import cv.vladislavchunikhin.discord.discord.dto.SecPeriodDto;
import cv.vladislavchunikhin.discord.discord.dto.TimeCalculationDto;
import cv.vladislavchunikhin.discord.http.GeneralResponse;
import cv.vladislavchunikhin.discord.http.HttpCodeWithMessageDto;
import cv.vladislavchunikhin.discord.web.payload.DiscordDataTaskPayload;
import cv.vladislavchunikhin.discord.web.payload.SimpleNotificationPayload;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class DiscordServiceImpl implements DiscordService {
    private static final String ERROR_MESSAGE_WHEN_NOTIFICATION_SENDING =
            "Sending message failed. To find out detailed reason you should look through server logs.";
    private static final String MESSAGE_TEMPLATE = "%s \nAnnouncement: %s";
    private static final String USER_AGENT = "Java-DiscordWebhook-By-Vladislav-Chunikhin";

    private final CommonProperties properties;
    private final DiscordComponent discordComponent;
    private final ScheduledTaskComponent scheduledTaskComponent;
    private final TimeCalculationComponent timeCalculationComponent;

    @Override
    public GeneralResponse sendNotification(@NonNull final SimpleNotificationPayload payload) {
        boolean sendingIsSuccess = discordComponent.sendNotification(this.getDiscordDto(payload));
        return sendingIsSuccess
                ? new GeneralResponse()
                : new GeneralResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ERROR_MESSAGE_WHEN_NOTIFICATION_SENDING);
    }

    @Override
    public GeneralResponse createNotificationTask(@NonNull final DiscordDataTaskPayload payload) {
        SecPeriodDto secPeriodDto = timeCalculationComponent.calculateSecPeriodFromNowToFixedTime(
                new TimeCalculationDto(payload.getDayOfWeek(), payload.getHours())
        );
        ScheduleTaskDto scheduleTaskDto = new ScheduleTaskDto(
                () -> discordComponent.sendNotification(this.getDiscordDto(payload)),
                secPeriodDto.getDelay(),
                secPeriodDto.getPeriod(),
                TimeUnit.SECONDS
        );
        UUID scheduledTask = scheduledTaskComponent.createScheduledTask(scheduleTaskDto);
        return new GeneralResponse(scheduledTask);
    }

    @Override
    public GeneralResponse shutdownNotificationTask(UUID id) {
        HttpCodeWithMessageDto dto = Optional.ofNullable(id)
                .map(scheduledTaskComponent::turnOffTaskById)
                .orElse(scheduledTaskComponent.turnOffAllTasks());
        return new GeneralResponse(dto.getCode().value(), dto.getMessage());
    }

    private DiscordDto getDiscordDto(@NonNull final SimpleNotificationPayload payload) {
        final String content = String.format(MESSAGE_TEMPLATE, properties.getMention(), payload.getContent());
        return new DiscordDto(properties.getWebhookUrl(), content, properties.getUsername(), USER_AGENT);
    }
}
