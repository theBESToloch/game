package ru.cheatbattle.client.UIControllers;

import javafx.animation.AnimationTimer;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.cheatbattle.client.data.Entity;
import ru.cheatbattle.client.data.Game;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Slf4j
@Component
public class CanvasWindowController implements Initializable {
    private static final double RADIUS = 10;
    public ScrollPane scrollPane;
    public Canvas canvas;

    private final Game game;
    private final Entity currentEntity;

    private Image[] i;

    public CanvasWindowController(Game game) {
        this.game = game;
        this.currentEntity = game.getCurrentEntity();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        i = new Image[3];
        i[0] = new Image("obj1-f1.png", 64, 64, false, false);
        i[1] = new Image("obj1-f2.png", 64, 64, false, false);
        i[2] = new Image("obj1-f3.png", 64, 64, false, false);
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                calculate();
                updateCanvas();
            }
        };

        animationTimer.start();
    }

    private volatile boolean up = false;
    private volatile boolean down = false;
    private volatile boolean left = false;
    private volatile boolean right = false;

    public void onKeyPressed(KeyEvent keyEvent) {
        log.info("pressed {}", keyEvent.getCode());
        switch (keyEvent.getCode()) {
            case LEFT -> left = true;
            case RIGHT -> right = true;
            case UP -> up = true;
            case DOWN -> down = true;
        }
    }

    public void onKeyReleased(KeyEvent keyEvent) {
        log.info("released {}", keyEvent.getCode());
        switch (keyEvent.getCode()) {
            case LEFT -> left = false;
            case RIGHT -> right = false;
            case UP -> up = false;
            case DOWN -> down = false;
        }
    }

    private void calculate() {
        if (up) {
            currentEntity.setY(currentEntity.getY() - 5);
        }
        if (down) {
            currentEntity.setY(currentEntity.getY() + 5);
        }
        if (left) {
            currentEntity.setX(currentEntity.getX() - 5);
        }
        if (right) {
            currentEntity.setX(currentEntity.getX() + 5);
        }
    }

    private void updateCanvas() {

        GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();
        graphicsContext2D.setFill(Color.WHITE);
        graphicsContext2D.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        Entity currentEntity = game.getCurrentEntity();
        graphicsContext2D.drawImage(getFrame(), currentEntity.getX(), currentEntity.getY());


        List<Entity> entities = game.getEntities();
        entities.forEach(e -> {
            if (!currentEntity.getUuid().equals(e.getUuid())) {
                graphicsContext2D.setFill(Color.BLUE);
                graphicsContext2D.fillOval(e.getX() - RADIUS, e.getY() - RADIUS, RADIUS * 2, RADIUS * 2);
            }
        });
    }

    private int currentFrame = 0;
    private byte frame = 0;

    private Image getFrame() {
        if (frame != 4) {
            frame++;
            return i[currentFrame];
        }
        if (!up && !down && !left && !right) {
            currentFrame = 0;
        } else if (currentFrame == 0 || currentFrame == 2) {
            currentFrame = 1;
        } else {
            currentFrame = 2;
        }
        frame = 0;
        return i[currentFrame];
    }

}
