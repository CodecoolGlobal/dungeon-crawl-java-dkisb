package com.codecool.dungeoncrawl.data;

import com.codecool.dungeoncrawl.data.actors.Enemy;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.items.Item;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;
    private boolean isLoaded;

    private List<Enemy> entities;
    private List<Item> items;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.items = new ArrayList<>();
        this.isLoaded = false;
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

    public List<Enemy> getEntities() {
        return entities;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Cell getCell(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return null;
        }
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntities(Enemy enemy) {
        entities.add(enemy);
    }

    public void setLoaded(boolean loaded) {
        isLoaded = loaded;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

}
