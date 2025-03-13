package com.codecool.dungeoncrawl.data;

import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.Ghost;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.actors.Skeleton;
import com.codecool.dungeoncrawl.data.items.*;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;
    private Actor actor;
    private Item item;
    private List<Actor> entities;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }


    public Player getPlayer() {
        return player;
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
    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    public void setEntity(Actor actor) {
        this.actor = actor;
    }
    public void setItem(Item item) {
        this.item = item;
    }
    public void addEntities(Actor actor) {
        entities.add(actor);
    }




}
