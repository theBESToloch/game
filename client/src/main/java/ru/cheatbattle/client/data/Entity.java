package ru.cheatbattle.client.data;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.UUID;

@Accessors(chain = true)
@Data
public class Entity implements Serializable {
    private UUID uuid;
    private volatile double x;
    private volatile double y;

    public Entity(){
        this.uuid = UUID.randomUUID();
    }
}
