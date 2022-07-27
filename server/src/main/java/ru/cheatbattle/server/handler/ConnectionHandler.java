package ru.cheatbattle.server.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import ru.cheatbattle.server.data.Entity;

import java.io.IOException;

@Slf4j
public class ConnectionHandler {
    private final Connection connection;
    private final GameHandler gameHandler;
    private final ObjectMapper objectMapper;

    public ConnectionHandler(Connection connection, GameHandler gameHandler) {
        this.connection = connection;
        this.gameHandler = gameHandler;
        this.objectMapper = new ObjectMapper();
    }

    public void handle() {
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

    public void stop() throws IOException {
        connection.close();
    }
}
