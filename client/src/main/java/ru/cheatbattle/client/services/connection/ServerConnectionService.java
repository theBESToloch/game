package ru.cheatbattle.client.services.connection;

import java.io.IOException;

public interface ServerConnectionService {
    Connection connect(String ip, int port) throws IOException;
}
