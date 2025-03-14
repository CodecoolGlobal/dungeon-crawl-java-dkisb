package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Player;

public class Shield extends Item {
    private static final int ACTION_POINT = 10;
    public Shield(Cell cell) {
        super(cell, "shield");
    }

    @Override
    public String getTileName() {
        return "shield";
    }

    @Override
    public void applyEffect(Player player) {
        player.setHealth(player.getHealth() + ACTION_POINT);
        player.setTileName("shieldedPlayer");
    }
}
