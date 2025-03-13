package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;

import java.util.Random;

public class Enemy extends Actor {
    private GameMap gameMap;
    private Random random = new Random();

    public Enemy(Cell cell, int baseHealth) {
        super(cell, baseHealth);
    }

    @Override
    public String getTileName() {
        return "enemy";
    }

    private boolean checkForPlayer(GameMap gameMap) {
        int x = cell.getX();
        int y = cell.getY();

        return isPlayerAt(gameMap, x + 1, y) ||
                isPlayerAt(gameMap, x - 1, y) ||
                isPlayerAt(gameMap, x, y + 1) ||
                isPlayerAt(gameMap, x, y - 1);
    }

    private boolean isPlayerAt(GameMap gameMap, int x, int y) {
        if (x >= 0 && x < gameMap.getWidth() && y >= 0 && y < gameMap.getHeight()) {
            Cell targetCell = gameMap.getCell(x, y);
            return targetCell.getActor() instanceof Player;
        }
        return false;
    }

    public void update(GameMap gameMap) {
        if (checkForPlayer(gameMap)) {
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

            if (!targetCell.getTileName().equals("wall")) {
                cell.setActor(null);
                targetCell.setActor(this);
                cell = targetCell;
            }
        }
    }
}
