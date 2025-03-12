package com.codecool.dungeoncrawl.data;

import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.actors.Skeleton;

import java.util.List;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;
    private Skeleton skeleton;
    private List<Skeleton> skeletons;

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

    public void setSkeletons(List<Skeleton> skeletons) {
        this.skeletons = skeletons;
    }

    public Player getPlayer() {
        return player;
    }
    public Skeleton getSkeleton() {
        return skeleton;
    }

    public List<Skeleton> getSkeletons() {
        return skeletons;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
