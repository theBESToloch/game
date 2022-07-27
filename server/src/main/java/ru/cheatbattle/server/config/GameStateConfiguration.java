package ru.cheatbattle.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.cheatbattle.server.handler.GameHandler;

@Configuration
public class GameStateConfiguration {

    @Bean
    public GameHandler gameHandler() {
        return new GameHandler();
    }
}
