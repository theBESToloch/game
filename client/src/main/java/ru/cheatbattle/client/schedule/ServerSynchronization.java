package ru.cheatbattle.client.schedule;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.cheatbattle.client.data.Entity;
import ru.cheatbattle.client.data.Game;
import ru.cheatbattle.client.services.TransportService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

@Slf4j
@Component
public class ServerSynchronization {

    private final TransportService transportService;
    private final Game game;
    private final ObjectMapper objectMapper;

    public ServerSynchronization(TransportService transportService, Game game) throws IOException {
        this.transportService = transportService;
        this.game = game;
        this.objectMapper = new ObjectMapper();
        transportService.connect();
        transportService.sendLine(objectMapper.writeValueAsString(game.getCurrentEntity()));
    }

    @Scheduled(fixedDelay = 33)
    public void sync() throws IOException {
        transportService.sendLine(objectMapper.writeValueAsString(game.getCurrentEntity()));
        List<Entity> entities = objectMapper.readValue(transportService.readLine(), new TypeReference<List<Entity>>() {
        });
        game.setEntities(entities);
    }
}
