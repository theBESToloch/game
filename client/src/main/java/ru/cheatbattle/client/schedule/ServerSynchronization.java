package ru.cheatbattle.client.schedule;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import ru.cheatbattle.client.data.Entity;
import ru.cheatbattle.client.data.Game;
import ru.cheatbattle.client.services.connection.ConnectionWorker;

import java.util.List;

@Slf4j
public class ServerSynchronization {

    private final ConnectionWorker serverConnectionWorker;
    private final Game game;
    private final ObjectMapper objectMapper;

    public ServerSynchronization(ConnectionWorker serverConnectionWorker, Game game) {
        this.serverConnectionWorker = serverConnectionWorker;
        this.game = game;
        this.objectMapper = new ObjectMapper();
    }

    @SneakyThrows
    public void sendCurrentState() {
        serverConnectionWorker.sendLine(objectMapper.writeValueAsString(game.getCurrentEntity()));
    }

    @SneakyThrows
    public void sync() {
        serverConnectionWorker.sendLine(objectMapper.writeValueAsString(game.getCurrentEntity()));
        List<Entity> entities = objectMapper.readValue(serverConnectionWorker.readLine(), new TypeReference<List<Entity>>() {
        });
        game.setEntities(entities);
    }
}
