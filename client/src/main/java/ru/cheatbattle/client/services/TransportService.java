package ru.cheatbattle.client.services;

import java.io.IOException;

public interface TransportService {
    void connect() throws IOException;

    void sendLine(String line) throws IOException;

    String readLine() throws IOException;

    void close() throws IOException;

}
