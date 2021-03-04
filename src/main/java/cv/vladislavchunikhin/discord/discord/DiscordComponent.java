package cv.vladislavchunikhin.discord.discord;

import cv.vladislavchunikhin.discord.discord.dto.DiscordDto;

/**
 * Component containing some methods for Discord API.
 */
public interface DiscordComponent {
    /**
     * @param dto discord data transfer object to execute something.
     * @return flag that indicate successful or failed method result.
     */
    boolean sendNotification(DiscordDto dto);
}
