package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;

public class Skeleton extends Actor { ;
    public Skeleton(Cell cell, GameMap gameMap) {
        super(cell, gameMap);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
