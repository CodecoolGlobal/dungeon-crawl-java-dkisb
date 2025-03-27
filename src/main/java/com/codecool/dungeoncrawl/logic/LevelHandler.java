package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Player;

public class LevelHandler {
    private static final String[] LEVELS = {"map1.txt", "map2.txt", "map3.txt", "map4.txt", "map5.txt", "map6.txt", "map7.txt"};
    private int currentLevel = -1;


    public boolean hasNextLevel() {
        return currentLevel < LEVELS.length - 1;
    }

    public void resetToFirstLevel() {
        currentLevel = -1;
    }

    public GameMap loadNextLevel(Player existingPlayer,  CombatHandler combatHandler, GameReset gameReset) {
        if (hasNextLevel()) {
            currentLevel++;
        }
        return loadCurrentLevel(existingPlayer, combatHandler, gameReset);

    }

    public GameMap loadCurrentLevel(Player existingPlayer, CombatHandler combatHandler, GameReset gameReset) {
        return MapLoader.loadMap(LEVELS[currentLevel], existingPlayer, combatHandler, gameReset);
    }
}
