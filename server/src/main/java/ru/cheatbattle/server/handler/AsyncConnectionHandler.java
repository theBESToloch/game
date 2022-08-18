package ru.cheatbattle.server.handler;


import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class AsyncConnectionHandler {

    private final ServerSocketHandler serverSocketHandler;
    private final GameHandler gameHandler;
    public AsyncConnectionHandler(ServerSocketHandler serverSocketHandler, GameHandler gameHandler) {
        this.serverSocketHandler = serverSocketHandler;
        this.gameHandler = gameHandler;
    }

    public void handle() throws IOException {
            Connection connection = serverSocketHandler.accept();
            registerConnection(connection);
    }

    private void registerConnection(Connection connection) throws IOException {
        new ConnectionHandler(gameHandler).registerConnection(connection);
    }

    public void asyncHandle() {
        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    handle();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        thread.setUncaughtExceptionHandler((thd, e) -> log.error("asyncHandler error", e));
        thread.start();

    }
}
