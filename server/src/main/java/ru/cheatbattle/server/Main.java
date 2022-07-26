package ru.cheatbattle.server;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@Slf4j
public class Main {

    private ServerSocket serverSocket;

    private final List<ConnectionHandler> handlers = new ArrayList<>();

    public void start() throws IOException {
        this.serverSocket = new ServerSocket(8000);
    }

    public Socket accept() throws IOException {
        return serverSocket.accept();
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


    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.start();

        while (true) {
            Socket socket = main.accept();
            Connection connection = new Connection(socket);
            ConnectionHandler handler = new ConnectionHandler(connection);
            handler.handle();
            main.addConnectionHandler(handler);
        }
    }


    public static class ConnectionHandler {
        private final Connection connection;

        public ConnectionHandler(Connection connection) {
            this.connection = connection;
        }

        public void handle() {
            Thread thread = new Thread(() -> {
                while (true) {
                    try {
                        String line = connection.readLine();
                        log.info(line);
                        connection.writeLine("server answer: " + line);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            thread.setDaemon(true);
            thread.start();
        }

        public void stop() throws IOException {
            connection.close();
        }
    }

    public static class Connection {
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
    }
}
