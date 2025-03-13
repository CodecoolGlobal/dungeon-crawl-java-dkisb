package com.codecool.dungeoncrawl.data;

import com.codecool.dungeoncrawl.data.actors.*;
import com.codecool.dungeoncrawl.data.items.*;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;

    private List<Enemy> entities;
    private List<Item> items;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.items = new ArrayList<>();
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
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addItem(Item item) {
        items.add(item);
    }
    public void addEntities(Enemy enemy) {
        entities.add(enemy);
    }




}
