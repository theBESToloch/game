package ru.cheatbattle.server.data;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.UUID;

@Data
@Accessors(chain = true)
public class Entity implements Serializable {

    private UUID uuid;

    private double x;
    private double y;
}
