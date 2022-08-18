package ru.cheatbattle.client.data;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Accessors(chain = true)
@Data
public class Entity implements Serializable {
    @Serial
    private static final long serialVersionUID = 310720221308L;
    private UUID uuid;
    private int x;
    private int y;
}
