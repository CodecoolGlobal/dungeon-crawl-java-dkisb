package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;

public class Ghost extends Actor {
    public Ghost(Cell cell, GameMap gameMap) {
        super(cell, gameMap);
    }

    @Override
    public String getTileName() {
        return "ghost";
    }
}
