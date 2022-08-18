package ru.cheatbattle.client.services.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ConnectionWorkerImpl implements ConnectionWorker {

    private final Connection connection;

    public ConnectionWorkerImpl(Connection connection) {
        this.connection = connection;
    }

    public void sendLine(String line) throws IOException {
        connection.out().write((line + "\n").getBytes(StandardCharsets.UTF_8));
    }

    public String readLine() throws IOException {
        BufferedReader input = new BufferedReader(
                new InputStreamReader(connection.in(), StandardCharsets.UTF_8));
        while (true) {
            boolean ready = input.ready();
            if (ready) {
                return input.readLine();
            }
        }
    }

    public void close() throws IOException {
        connection.out().close();
        connection.in().close();
        connection.socket().close();
    }

}
