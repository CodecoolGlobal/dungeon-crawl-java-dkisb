package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;

public class Shield extends Item {

    public Shield(Cell cell) {
        super(cell, "shield");
    }

    @Override
    public String getTileName() {
        return "shield";
    }

    @Override
    public int setActionPoints() {
        return 10;
    }
}
