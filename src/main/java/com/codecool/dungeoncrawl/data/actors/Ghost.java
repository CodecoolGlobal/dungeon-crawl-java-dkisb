package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;

public class Ghost extends Actor {
    Cell cell;
    public Ghost(Cell cell, GameMap gameMap) {
        super(cell,10 ,gameMap);
        this.cell = cell;
    }

    @Override
    public String getTileName() {
        return "ghost";
    }

    @Override
    public void move(int dx, int dy) {
        if (cell.getX() == 0 && dx == -1) {
            dx = 0;
        }
        if (cell.getX() == 24 && dx == 1) {
            dx = 0;
        }
        if (cell.getY() == 0 && dy == -1) {
            dy = 0;
        }
        if (cell.getY() == 19 && dy == 1) {
            dy = 0;
        }
        Cell nextCell = cell.getNeighbor(dx, dy);
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }
}
