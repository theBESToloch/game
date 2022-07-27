package ru.cheatbattle.server.handler;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class ServerSocketHandler {
    private ServerSocket serverSocket;

    private final List<ConnectionHandler> handlers = new ArrayList<>();

    public void start() throws IOException {
        this.serverSocket = new ServerSocket(8000);
    }

    public Connection accept() throws IOException {
        return new Connection(serverSocket.accept());
    }

    public void stop() throws IOException {
        for (ConnectionHandler handler : handlers) {
            handler.stop();
        }
        this.serverSocket.close();
    }

    public void addConnectionHandler(ConnectionHandler connectionHandler) {
        handlers.add(connectionHandler);
    }

}
