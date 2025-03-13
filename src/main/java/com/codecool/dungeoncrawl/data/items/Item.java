package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Drawable;

public abstract class Item implements Drawable {
    private Cell cell;
    private String name;


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

    @Override
    public String toString() {
        return getName();
    }
}
