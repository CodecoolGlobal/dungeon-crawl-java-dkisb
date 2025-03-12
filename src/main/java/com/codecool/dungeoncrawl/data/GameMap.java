package com.codecool.dungeoncrawl.data;

import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.Ghost;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.actors.Skeleton;
import com.codecool.dungeoncrawl.data.items.*;

import java.util.List;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;
    private Skeleton skeleton;
    private Ghost ghost;
    private Key key;
    private Potion potion;
    private Sword sword;
    private Shield shield;
    private List<Actor> entities;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setSkeleton(Skeleton skeleton) {
        this.skeleton = skeleton;
    }
    public void setGhost(Ghost ghost) {
        this.ghost = ghost;
    }
    public void setKey(Key key) {
        this.key = key;
    }
    public void setPotion(Potion potion) {
        this.potion = potion;
    }
    public void setSword(Sword sword) {
        this.sword = sword;
    }
    public void setShield(Shield shield) {
        this.shield = shield;
    }

    public void setEntities(List<Actor> entities) {
        this.entities = entities;
    }

    public Player getPlayer() {
        return player;
    }
    public Skeleton getSkeleton() {
        return skeleton;
    }

    public List<Actor> getEntities() {
        return entities;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
