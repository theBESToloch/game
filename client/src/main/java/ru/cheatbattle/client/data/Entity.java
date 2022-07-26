package ru.cheatbattle.client.data;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Accessors(chain = true)
@Data
public class Entity implements Serializable {

    private volatile double x;
    private volatile double y;
}
