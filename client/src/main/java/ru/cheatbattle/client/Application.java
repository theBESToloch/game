package ru.cheatbattle.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        javafx.application.Application.launch(FxApp.class, args);
    }
}
