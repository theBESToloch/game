package ru.cheatbattle.server.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import ru.cheatbattle.server.data.Entity;

import java.io.IOException;

@Slf4j
public class ConnectionHandler {
    private Connection connection;
    private final GameHandler gameHandler;
    private final ObjectMapper objectMapper;

    public ConnectionHandler(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
        this.objectMapper = new ObjectMapper();
    }

    private void handle() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Entity entity = objectMapper.readValue(connection.readLine(), Entity.class);
                    gameHandler.changeState(entity);
                    connection.writeLine(objectMapper.writeValueAsString(gameHandler.getAllState()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public void registerConnection(Connection connection) throws IOException {
        this.connection = connection;

        Entity entity = objectMapper.readValue(connection.readLine(), Entity.class);
        gameHandler.addEntity(entity);

        handle();
    }

    public void stop() throws IOException {
        connection.close();
    }
}
