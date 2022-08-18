package ru.cheatbattle.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.cheatbattle.server.handler.AsyncConnectionHandler;
import ru.cheatbattle.server.handler.GameHandler;
import ru.cheatbattle.server.handler.ServerSocketHandler;

import java.io.IOException;

@Configuration
public class ServerSocketConfiguration {

    @Bean
    public ServerSocketHandler serverSocketHandler() throws IOException {
        ServerSocketHandler serverSocketHandler = new ServerSocketHandler();
        serverSocketHandler.start();
        return serverSocketHandler;
    }

    @Bean
    public AsyncConnectionHandler asyncConnectionHandler(ServerSocketHandler serverSocketHandler, GameHandler gameHandler) {
        AsyncConnectionHandler asyncConnectionHandler = new AsyncConnectionHandler(serverSocketHandler, gameHandler);
        asyncConnectionHandler.asyncHandle();
        return asyncConnectionHandler;
    }

}
