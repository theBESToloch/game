package ru.cheatbattle.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.net.URL;

public class FxApp extends Application {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void start(Stage canvasStage) {

        ScrollPane scrollPane = load(getClass().getResource("/CanvasWindow.fxml"));
        Scene scene = new Scene(scrollPane, 480, 320);
        canvasStage.setScene(scene);

        Canvas canvas = (Canvas) scrollPane.getContent();
        canvas.setWidth(scrollPane.getWidth() - 50);
        scrollPane.widthProperty().addListener(((observable, oldValue, newValue) -> canvas.setWidth((Double) newValue - 50)));
        canvas.setHeight(scrollPane.getHeight() - 50);
        scrollPane.heightProperty().addListener(((observable, oldValue, newValue) -> canvas.setHeight((Double) newValue - 50)));

        canvasStage.show();
    }

    public <T> T load(URL resource) {
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        fxmlLoader.setControllerFactory((aClass) -> applicationContext.getBean(aClass));
        try {
            return fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void init() {
        applicationContext = new SpringApplicationBuilder(ru.cheatbattle.client.Application.class).run();
    }

    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }
}
