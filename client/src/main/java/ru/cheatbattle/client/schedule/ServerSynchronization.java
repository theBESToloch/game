package ru.cheatbattle.client.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.cheatbattle.client.data.Game;
import ru.cheatbattle.client.services.TransportService;

import java.io.IOException;

@Component
public class ServerSynchronization {

    private final TransportService transportService;
    private final Game game;

    public ServerSynchronization(TransportService transportService, Game game) throws IOException {
        this.transportService = transportService;
        this.game = game;
        transportService.connect();
    }

    @Scheduled(fixedDelay = 1000)
    public void sync() throws IOException {
        transportService.sendLine(game.getCurrentEntities().toString());
    }
}
