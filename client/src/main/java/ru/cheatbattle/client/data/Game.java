package ru.cheatbattle.client.data;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class Game {
    private Entity currentEntities;
}
