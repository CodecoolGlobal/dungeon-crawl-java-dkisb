package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

import java.util.Random;

public class Ghost extends Enemy {
    private static final int BASE_HEALTH = 10;

    public Ghost(Cell cell, Random random) {
        super(cell, BASE_HEALTH, random);
    }

    @Override
    public String getTileName() {
        return "ghost";
    }

    @Override
    public void move(int dx, int dy) { //remove magic numbers
        if (cell != null) {
            Cell nextCell = cell.getNeighbor(dx, dy);
            if (nextCell != null) {
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
            }
        }
    }
}
