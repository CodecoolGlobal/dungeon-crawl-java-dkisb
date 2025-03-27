package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;

import java.util.List;
import java.util.Random;

public class Enemy extends Actor {
    private Random random;

    public Enemy(Cell cell, int baseHealth, Random random) {
        super(cell, baseHealth);
        this.random = random;
    }

    @Override
    public String getTileName() {
        return "enemy";
    }

    private boolean checkForPlayer() {
        return  isPlayerAt(cell.getNeighbor(1, 0)) ||
                isPlayerAt(cell.getNeighbor(-1,0)) ||
                isPlayerAt(cell.getNeighbor(0,1)) ||
                isPlayerAt(cell.getNeighbor(0, -1));
    }

    private boolean isPlayerAt( Cell neighbor) {
        return neighbor != null && neighbor.getActor() instanceof Player;
    }

    public boolean isDead() {
        return cell == null;
    }

    public void update() {
        if (checkForPlayer()) {
            cell.setActor(null);
            cell = null;
        } else {
            moveRandomly();
        }
    }

    private void moveRandomly() {
        int index = random.nextInt(4);
        switch (index) {
            case 0 -> move(1, 0);
            case 1 -> move( -1, 0);
            case 2 -> move( 0, -1);
            case 3 -> move( 0, 1);
        }
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);

        if (!nextCell.getTileName().equals("wall") && nextCell.getActor() == null) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }
}
