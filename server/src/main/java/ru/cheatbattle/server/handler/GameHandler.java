package ru.cheatbattle.server.handler;

import ru.cheatbattle.server.data.Entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GameHandler {
    private final Map<UUID, Entity> entityMap;

    public GameHandler() {
        entityMap = new HashMap<>();
    }

    public void addEntity(Entity entity) {
        entityMap.put(entity.getUuid(), entity);
    }

    public void changeState(Entity entity) {
        entityMap.replace(entity.getUuid(), entity);
    }

    public Collection<Entity> getAllState() {
        return entityMap.values();
    }
}
