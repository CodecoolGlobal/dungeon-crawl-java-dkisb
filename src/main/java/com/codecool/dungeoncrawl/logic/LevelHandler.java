package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Player;

public class LevelHandler {
    private static final String[] LEVELS = {"map1.txt", "map2.txt", "map3.txt", "map4.txt", "map5.txt", "map6.txt", "map7.txt"};
    private int currentLevel = -1;


    public boolean hasNextLevel() {
        return currentLevel < LEVELS.length - 1;
    }

    public GameMap loadLevel(Player existingPlayer) {
        if (hasNextLevel()) {
            currentLevel++;
        }
        return MapLoader.loadMap(LEVELS[currentLevel], existingPlayer);
    }

    public int getCurrentLevel() {
        return currentLevel;
    }
/*
    public GameMap setLevel(int level, Player existingPlayer, boolean ) {
        currentLevel = level;
        return MapLoader.loadMap(LEVELS[currentLevel], existingPlayer);
    }

 */
}
