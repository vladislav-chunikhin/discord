package ru.cosysoft.discord.discordbot.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.cosysoft.discord.discordbot.http.NotificationResponse;
import ru.cosysoft.discord.discordbot.notification.NotificationService;
import ru.cosysoft.discord.discordbot.web.payload.DiscordNotificationDataPayload;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
@Tag(name = "Notification API")
public class NotificationController {
    private final static Map<UUID, ScheduledExecutorService> SCHEDULE_EXECUTORS_MAP = new HashMap<>();

    private final NotificationService notificationService;

    @Operation(summary = "Sending daily status to discord")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Sending is successfully",
            content = {@Content(schema = @Schema(implementation = NotificationResponse.class))}
            ),
        @ApiResponse(
            responseCode = "500",
            description = "Sending failed",
            content = {@Content(schema = @Schema(implementation = NotificationResponse.class))}
        )
    })
    @GetMapping("/daily-status/techforce")
    public ResponseEntity<NotificationResponse> sendDailyStatusNotification() {
        NotificationResponse response = notificationService.sendTechforceDailyStatusNotification();
        return getApiResponse(response);
    }

    @Operation(summary = "Making discord notification")
    @PostMapping("/discord/create")
    public ResponseEntity<NotificationResponse> createDiscordNotification(@RequestBody final DiscordNotificationDataPayload payload) {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(notificationService::sendTechforceDailyStatusNotification, 2, 2, TimeUnit.SECONDS);
        UUID id = UUID.randomUUID();
        SCHEDULE_EXECUTORS_MAP.put(id, scheduledExecutorService);
        NotificationResponse response = new NotificationResponse(id);
        return getApiResponse(response);
    }

    @Operation(summary = "Turn offing task by id")
    @PostMapping("/task/shutdown")
    public ResponseEntity<NotificationResponse> shutdownTask(@RequestParam(value = "id") UUID id) {
        ScheduledExecutorService scheduledExecutorService = SCHEDULE_EXECUTORS_MAP.get(id);
        if (!scheduledExecutorService.isShutdown()) scheduledExecutorService.shutdown();
        NotificationResponse response = new NotificationResponse();
        return getApiResponse(response);
    }

    private ResponseEntity<NotificationResponse> getApiResponse(final NotificationResponse response) {
        return ResponseEntity.status(response.getHttpCode()).body(response);
    }
}
