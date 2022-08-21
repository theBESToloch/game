package ru.cheatbattle.server.handler;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Connection {
    private final Socket socket;
    private final OutputStream out;
    private final InputStream in;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        this.in = socket.getInputStream();
        this.out = socket.getOutputStream();
    }

    public String readLine() throws IOException {
        BufferedReader input = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        while (true) {
            boolean ready = input.ready();
            if (ready) {
                return input.readLine();
            }
        }
    }

    public void writeLine(String line) throws IOException {
        DataOutputStream outputStream = new DataOutputStream(out);
        outputStream.write((line + "\n").getBytes(StandardCharsets.UTF_8));
    }

    public void close() throws IOException {
        out.close();
        in.close();
        socket.close();
    }

    public Socket getSocket() {
        return socket;
    }

}
