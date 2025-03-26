package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Enemy;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.items.Item;

import java.util.List;

public class GameLogic {
    private GameMap map;
    private final LevelHandler levelHandler;
    private Player player;

    public GameLogic() {
        this.levelHandler = new LevelHandler();
        this.map = levelHandler.loadLevel(null);
        this.player = getPlayer();
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
        map.getEntities().removeIf(Enemy::isDead);
    }

    public void checkForLevelProgression() {
        Player player = getPlayer();
        if (player.isAbleToProgress()) {
            String currentName = player.getTileName();
            player.setTileName(currentName);
            this.map = levelHandler.loadLevel(player);
        }
    }

}
