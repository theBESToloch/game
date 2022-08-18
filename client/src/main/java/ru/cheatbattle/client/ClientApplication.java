package ru.cheatbattle.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        javafx.application.Application.launch(FxApp.class, args);
    }
}
