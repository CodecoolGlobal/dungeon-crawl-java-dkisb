package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;

public class Player extends Actor {
    Cell cell;
    public Player(Cell cell, GameMap gameMap) {
        super(cell, gameMap);
    }

    public String getTileName() {
        return "player";
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
}
