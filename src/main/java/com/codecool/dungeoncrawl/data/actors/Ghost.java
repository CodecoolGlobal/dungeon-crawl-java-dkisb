package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Ghost extends Actor {
    public Ghost(Cell cell) {
        super(cell, 10);
    }

    @Override
    public String getTileName() {
        return "ghost";
    }
}
