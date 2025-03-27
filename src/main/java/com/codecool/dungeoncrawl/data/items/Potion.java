package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Player;

public class Potion extends Item {
    private static final int ACTION_POINT = 20;
    public Potion(Cell cell) {
        super(cell, "potion");
    }

    @Override
    public String getTileName() {
        return "potion";
    }


    @Override
    public void applyEffect(Player player) {
        player.setHealth(player.getHealth() + ACTION_POINT);
    }
}
