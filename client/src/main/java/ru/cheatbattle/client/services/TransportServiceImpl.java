package ru.cheatbattle.client.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Service
public class TransportServiceImpl implements TransportService {
    private final static String serverIP = "127.0.0.1";

    private Socket socket;
    private OutputStream out;
    private InputStream in;


    public void connect() throws IOException {
        Socket socket = new Socket(serverIP, 8000);

        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        this.socket = socket;
        this.in = inputStream;
        this.out = outputStream;
    }

    public void sendLine(String line) throws IOException {
        out.write((line + "\n").getBytes(StandardCharsets.UTF_8));
    }

    public String readLine() throws IOException {
        BufferedReader input = new BufferedReader(
                new InputStreamReader(in, StandardCharsets.UTF_8));
        while (true) {
            boolean ready = input.ready();
            if (ready) {
                return input.readLine();
            }
        }
    }

    public void close() throws IOException {
        out.close();
        in.close();
        socket.close();
    }


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        TransportService transportService = new TransportServiceImpl();
        transportService.connect();
        while (true) {
            String line = scanner.nextLine();
            transportService.sendLine(line);
            System.out.println(transportService.readLine());
        }
    }
}
