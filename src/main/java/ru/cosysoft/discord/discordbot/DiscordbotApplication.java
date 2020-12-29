package ru.cosysoft.discord.discordbot;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//http://localhost:8080/swagger-ui.html
@SpringBootApplication
@EnableScheduling
@OpenAPIDefinition(
    info = @Info(
        title = "Application for notification by Discord",
        contact = @Contact(
            name = "Vladislav Chunikhin",
            url = "https://www.facebook.com/vladislav.chunikhin.12",
            email = "vladislav.chunikhin.95@gmail.com"
        )
    ),
    servers = {
        @Server(url = "http://localhost:8080"),
        @Server(url = "https://discordbotcosy.herokuapp.com")
    }
)
public class DiscordbotApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscordbotApplication.class, args);
    }

}
