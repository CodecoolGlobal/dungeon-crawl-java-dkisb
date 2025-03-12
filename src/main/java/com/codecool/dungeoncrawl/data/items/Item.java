package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Drawable;

public abstract class Item implements Drawable {
    private Cell cell;
    private String name;
    private int actionPoints;


    public Item(Cell cell, String name) {
        this.name = name;
        if (cell != null) {
            this.cell = cell;
            this.cell.setItem(this);
        } else {
            throw new IllegalArgumentException("Cell cannot be null when placing an item.");
        }
    }



    public Cell getCell() {
        return cell;
    }

    public String getName() {
        return name;
    }

    public int getActionPoints() {
        return actionPoints;
    }

    public abstract int setActionPoints();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("\n");
        return sb.toString();
    }
}
