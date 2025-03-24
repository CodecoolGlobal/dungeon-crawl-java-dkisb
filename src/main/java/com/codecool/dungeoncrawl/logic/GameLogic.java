package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Enemy;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameLogic {
    private GameMap map;

    public GameLogic(String mapName) {
        this.map = MapLoader.loadMap(mapName);
    }

    public double getMapWidth() {
        return map.getWidth();
    }

    public double getMapHeight() {
        return map.getHeight();
    }

    public void setup() {
    }

    public Cell getCell(int x, int y) {
        return map.getCell(x, y);
    }
    public Player getPlayer() {
        return map.getPlayer();
    }
    public String getPlayerHealth() {
        return Integer.toString(map.getPlayer().getHealth());
    }
    public List<Item> getPlayerInventory() {
        return map.getPlayer().getInventory();
    }

    public void moveEnemies() {
        for (Enemy enemy : map.getEntities()) {
            enemy.update();
        }
        map.getEntities().removeIf(Enemy:: isDead);

    }
}
