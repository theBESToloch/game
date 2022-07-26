package ru.cheatbattle.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.cheatbattle.client.data.Entity;
import ru.cheatbattle.client.data.Game;

@Configuration
public class GameContext {

    @Bean
    public Game game(Entity entities) {
        Game game = new Game();
        game.setCurrentEntities(entities);

        return game;
    }

    @Bean
    public Entity myEntity() {
        return new Entity()
                .setX(100)
                .setY(100);
    }
}
