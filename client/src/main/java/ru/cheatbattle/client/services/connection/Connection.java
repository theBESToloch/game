package ru.cheatbattle.client.services.connection;


import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public record Connection(Socket socket, InputStream in, OutputStream out) {
}
