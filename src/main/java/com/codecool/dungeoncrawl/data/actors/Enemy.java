package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Enemy extends Actor {

    public Enemy(Cell cell, int baseHealth) {
        super(cell, baseHealth);
    }

    @Override
    public String getTileName() {
        return "";
    }
}
