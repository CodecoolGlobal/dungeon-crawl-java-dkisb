package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Player;

public class Key extends Item {

    public Key(Cell cell) {
        super(cell, "key");
    }

    @Override
    public String getTileName() {
        return "key";
    }


    @Override
    public void applyEffect(Player player) {
    }
}
