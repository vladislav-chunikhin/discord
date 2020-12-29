package ru.cosysoft.discord.discordbot.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cosysoft.discord.discordbot.http.NotificationResponse;
import ru.cosysoft.discord.discordbot.notification.NotificationService;
import ru.cosysoft.discord.discordbot.web.payload.DiscordNotificationDataPayload;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
@Tag(name = "Notification API")
public class NotificationController {

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
    public ResponseEntity<?> sendDailyStatusNotification() {
        NotificationResponse response = notificationService.sendTechforceDailyStatusNotification();
        return ResponseEntity
            .status(response.getHttpCode())
            .body(response);
    }

    @Operation(summary = "Making discord notification")
    @PostMapping("/discord/create")
    public void createDiscordNotification(@RequestBody final DiscordNotificationDataPayload payload) {

    }
}
