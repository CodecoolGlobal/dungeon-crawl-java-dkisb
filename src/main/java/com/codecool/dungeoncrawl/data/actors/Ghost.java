package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Ghost extends Enemy {
    //Cell cell;
    private static final int BASE_HEALTH = 10;

    public Ghost(Cell cell) {
        super(cell, BASE_HEALTH);
        this.cell = cell;
    }

    @Override
    public String getTileName() {
        return "ghost";
    }

    @Override
    public void move(int dx, int dy) { //remove magic numbers
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
