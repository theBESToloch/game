package ru.cheatbattle.client.services.connection;

import java.io.IOException;

public interface ConnectionWorker {

    void sendLine(String line) throws IOException;

    String readLine() throws IOException;

    void close() throws IOException;
}
