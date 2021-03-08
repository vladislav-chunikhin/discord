package cv.vladislavchunikhin.discord;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for application running.
 */
@SpringBootApplication
public class DiscordApplication {
    /**
     * @param args arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(DiscordApplication.class, args);
    }

}
