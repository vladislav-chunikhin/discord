package cv.vladislavchunikhin.discord.web;

import cv.vladislavchunikhin.discord.http.GeneralResponse;
import cv.vladislavchunikhin.discord.discord.DiscordService;
import cv.vladislavchunikhin.discord.web.payload.DiscordDataTaskPayload;
import cv.vladislavchunikhin.discord.web.payload.SimpleNotificationPayload;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Discord endpoints. */
@RestController
@RequestMapping("/api/discord")
@RequiredArgsConstructor
@Tag(name = "Discord API")
public class DiscordController extends BaseController {

    private final DiscordService discordService;

    @Operation(summary = "One-time notification sending")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Sending is successfully",
            content = {@Content(schema = @Schema(implementation = GeneralResponse.class))}
            ),
        @ApiResponse(
            responseCode = "500",
            description = "Sending failed",
            content = {@Content(schema = @Schema(implementation = GeneralResponse.class))}
        )
    })
    @PostMapping("/notification/send")
    public ResponseEntity<GeneralResponse> sendNotification(@RequestBody final SimpleNotificationPayload payload) {
        final GeneralResponse response = discordService.sendNotification(payload);
        return getApiResponse(response);
    }

    @Operation(summary = "Creating notification task")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Creating notification task is successfully",
                    content = {@Content(schema = @Schema(implementation = GeneralResponse.class))}
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Creating notification task failed",
                    content = {@Content(schema = @Schema(implementation = GeneralResponse.class))}
            )
    })
    @PostMapping("/notification-task/create")
    public ResponseEntity<GeneralResponse> createNotificationTask(@Validated @RequestBody final DiscordDataTaskPayload payload) {
        GeneralResponse response = discordService.createNotificationTask(payload);
        return getApiResponse(response);
    }

    @Operation(summary = "Turn offing notification tasks")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Turn offing notification tasks is successfully",
                    content = {@Content(schema = @Schema(implementation = GeneralResponse.class))}
            )
    })
    @PostMapping("/notification-task/shutdown")
    public ResponseEntity<GeneralResponse> shutdownNotificationTask(
            @RequestParam(value = "id", required = false) UUID id
    ) {
        GeneralResponse response = discordService.shutdownNotificationTask(id);
        return getApiResponse(response);
    }

    @Operation(summary = "Getting all notification tasks")
    @ApiResponse(
            responseCode = "200",
            description = "Getting is successfully",
            content = {@Content(schema = @Schema(implementation = GeneralResponse.class))}
    )
    @GetMapping("/notification-task")
    public ResponseEntity<GeneralResponse> getAllNotificationTasks() {
        GeneralResponse response = discordService.getAllNotificationTasks();
        return getApiResponse(response);
    }
}

