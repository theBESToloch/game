package ru.cheatbattle.client.services.connection;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

@Service
public class ServerConnectionServiceImpl implements ServerConnectionService {

    public Connection connect(String ip, int port) throws IOException {
        Socket socket = new Socket(ip, port);

        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        return new Connection(socket, inputStream, outputStream);
    }

}
