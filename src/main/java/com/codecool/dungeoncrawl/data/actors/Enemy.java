package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;

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

    public void update(GameMap gameMap) {
        if (checkForPlayer()) {
            gameMap.getEntities().remove(this);
            cell.setActor(null);
        } else {
            moveRandomly(gameMap);
        }
    }

    private void moveRandomly(GameMap gameMap) {
        int index = random.nextInt(4);
        switch (index) {
            case 0 -> tryMove(gameMap, 1, 0);
            case 1 -> tryMove(gameMap, -1, 0);
            case 2 -> tryMove(gameMap, 0, -1);
            case 3 -> tryMove(gameMap, 0, 1);
        }
    }

    private void tryMove(GameMap gameMap, int dx, int dy) {
        int newX = cell.getX() + dx;
        int newY = cell.getY() + dy;

        if (newX >= 0 && newX < gameMap.getWidth() && newY >= 0 && newY < gameMap.getHeight()) {
            Cell targetCell = gameMap.getCell(newX, newY);

            if (!targetCell.getTileName().equals("wall") && targetCell.getActor() == null) {
                cell.setActor(null);
                targetCell.setActor(this);
                cell = targetCell;
            }
        }
    }
}
