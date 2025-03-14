package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;


public class Skeleton extends Enemy {
    //private Cell cell;
    private static final int BASE_HEALTH = 15;

    public Skeleton(Cell cell) {
        super(cell, BASE_HEALTH);
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
