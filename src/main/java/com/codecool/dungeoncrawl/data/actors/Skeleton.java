package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

import java.util.Random;


public class Skeleton extends Enemy {
    private static final int BASE_HEALTH = 15;
    private static final int BASE_DAMAGE = 5;

    public Skeleton(Cell cell, Random random) {
        super(cell, BASE_HEALTH, random);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    @Override
    public int getAttackPower() {
        return BASE_DAMAGE;
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
