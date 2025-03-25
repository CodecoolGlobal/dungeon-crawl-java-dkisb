package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Player;

public class LevelHandler {
    private static final String[] LEVELS = {"map1.txt", "map2.txt", "map3.txt", "map4.txt", "map5.txt", "map6.txt", "map7.txt"};
    private int currentLevel = 0;

    public GameMap loadLevel(){
        GameMap map = MapLoader.loadMap(LEVELS[currentLevel]);
        Player player = map.getPlayer();
        map.setPlayer(player);
        return map;
    }

    public boolean hasNextLevel(){
        return currentLevel < LEVELS.length - 1;
    }

    public void nextLevel(){
        if(hasNextLevel()){
            currentLevel++;
        }
    }

    public void setCurrentLevel(int currentLevel){
        this.currentLevel = currentLevel;
    }

}
