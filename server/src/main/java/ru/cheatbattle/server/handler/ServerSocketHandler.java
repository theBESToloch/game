package ru.cheatbattle.server.handler;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerSocketHandler {
    private ServerSocket serverSocket;

    public void start() throws IOException {
        this.serverSocket = new ServerSocket(80);
    }

    public Connection accept() throws IOException {
        return new Connection(serverSocket.accept());
    }

    public void stop() throws IOException {
        this.serverSocket.close();
    }

}
