package cv.vladislavchunikhin.discord.web;

import cv.vladislavchunikhin.discord.http.GeneralResponse;
import cv.vladislavchunikhin.discord.discord.DiscordService;
import cv.vladislavchunikhin.discord.web.payload.DiscordDataPayload;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
@Tag(name = "Notification API")
public class DiscordController extends BaseController {
    private final DiscordService discordService;

    @Operation(summary = "Sending daily status to discord")
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
    @GetMapping("/daily-status/techforce")
    public ResponseEntity<GeneralResponse> sendDailyStatusNotification() {
        GeneralResponse response = discordService.sendMessage();
        return getApiResponse(response);
    }

    @Operation(summary = "Making discord notification")
    @PostMapping("/discord/create")
    public ResponseEntity<GeneralResponse> createDiscordNotification(@RequestBody final DiscordDataPayload payload) {
        GeneralResponse response = new GeneralResponse();
        return getApiResponse(response);
    }

    @Operation(summary = "Turn offing task by id")
    @PostMapping("/task/shutdown")
    public ResponseEntity<GeneralResponse> shutdownTask(@RequestParam(value = "id") UUID id) {
        GeneralResponse response = new GeneralResponse();
        return getApiResponse(response);
    }
}

