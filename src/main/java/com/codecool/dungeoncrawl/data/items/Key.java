package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;

public class Key extends Item {

    public Key(Cell cell) {
        super(cell);
    }

    @Override
    public int setActionPoints() {
        return 0;
    }

    @Override
    public String getTileName() {
        return "key";
    }
}
