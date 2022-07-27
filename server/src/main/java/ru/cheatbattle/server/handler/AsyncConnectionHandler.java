package ru.cheatbattle.server.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import ru.cheatbattle.server.data.Entity;

import java.io.IOException;

@Slf4j
public class AsyncConnectionHandler {

    private final ServerSocketHandler serverSocketHandler;
    private final GameHandler gameHandler;
    private final ObjectMapper objectMapper;

    public AsyncConnectionHandler(ServerSocketHandler serverSocketHandler,
                                  GameHandler gameHandler) {
        this.serverSocketHandler = serverSocketHandler;
        this.gameHandler = gameHandler;
        this.objectMapper = new ObjectMapper();
    }

    public void handle() throws IOException {
        while (true) {
            Connection connection = serverSocketHandler.accept();
            registerEntity(connection);
            ConnectionHandler handler = new ConnectionHandler(connection, gameHandler);
            handler.handle();
            serverSocketHandler.addConnectionHandler(handler);
        }
    }

    private void registerEntity(Connection connection) throws IOException {
        Entity entity = objectMapper.readValue(connection.readLine(), Entity.class);
        gameHandler.addEntity(entity);
    }

    public void asyncHandle() {
        Thread thread = new Thread(() -> {
            try {
                handle();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        thread.setUncaughtExceptionHandler((thd, e) -> log.error("asyncHandler error", e));
        thread.start();

    }
}
