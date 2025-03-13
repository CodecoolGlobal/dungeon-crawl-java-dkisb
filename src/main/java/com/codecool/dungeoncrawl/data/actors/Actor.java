package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Drawable;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.items.Item;

import java.util.List;

public abstract class Actor implements Drawable {
    private Cell cell;
    private Player player;
    private GameMap gameMap;
    private int health = 100;

    public Actor(Cell cell, GameMap gameMap) {
        this.cell = cell;
        this.cell.setActor(this);
        this.gameMap = gameMap;
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
            /*
            int x;
            int y;

            if (cell.getActor() instanceof Player) {
                x = cell.getX();
                y = cell.getY();
                Actor actorToRemove = null;
                for (Actor actor : gameMap.getEntities()) {
                    if (actor.getX() == x && actor.getY() == y) {
                        actorToRemove = actor;
                    }
                }
                gameMap.getEntities().remove(actorToRemove);
            }
             */

    }

    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
}
