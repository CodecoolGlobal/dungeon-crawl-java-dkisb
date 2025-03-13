package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;

public class Skeleton extends Actor {
    private Cell cell;
    public Skeleton(Cell cell, GameMap gameMap) {
        super(cell, gameMap);
        this.cell = cell;
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (!cell.getNeighbor(dx, dy).getTileName().equals("wall")) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }
}
