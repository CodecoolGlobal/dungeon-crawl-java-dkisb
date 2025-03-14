package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Enemy;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.items.Item;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {
    private GameMap map;

    public GameLogic() {
        this.map = MapLoader.loadMap();
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
        List<Enemy> enemiesCopy = new ArrayList<>(map.getEntities());
        List<Enemy> deadEnemies = new ArrayList<>();

        for (Enemy enemy : enemiesCopy) {
            enemy.update(map);
            if(!map.getEntities().contains(enemy)){
                deadEnemies.add(enemy);
            }
            map.getEntities().removeAll(deadEnemies);
        }

    }
}
