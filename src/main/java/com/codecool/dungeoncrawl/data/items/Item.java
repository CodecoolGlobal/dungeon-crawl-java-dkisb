package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Drawable;

public abstract class Item implements Drawable {
    private Cell cell;
    private int actionPoints;


    public Item(Cell cell) {
        if (cell != null) {
            this.cell = cell;
            this.cell.setItem(this);
        } else {
            throw new IllegalArgumentException("Cell cannot be null when placing an item.");
        }
    }

    public void placeItem(int dx, int dy) {
        Cell targetCell = cell.getItem().getCell();
        if (targetCell != null && "floor".equals(targetCell.getTileName())) {
            this.cell.setItem(null);
            this.cell = targetCell;
            this.cell.setItem(this);
        } else {
            System.err.println("Cannot place item at (" + dx + ", " + dy + ") - Invalid cell.");
        }

        if (cell != null && cell.getTileName().equals("floor")) {
            cell.setItem(this);
        }
    }

    public Cell getCell() {
        return cell;
    }

    public int getActionPoints() {
        return actionPoints;
    }

    public abstract int setActionPoints();
}
