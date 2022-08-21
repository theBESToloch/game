package ru.cheatbattle.server.data;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@Accessors(chain = true)
public class Entity implements Serializable {
    @Serial
    private static final long serialVersionUID = -6464944317427000351L;

    private UUID uuid;

    private double x;
    private double y;
}
