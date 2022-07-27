package ru.cheatbattle.client.data;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Accessors(chain = true)
@Data
public class Game {
    private volatile Entity currentEntity;

    private volatile List<Entity> entities = new ArrayList<>();
}
