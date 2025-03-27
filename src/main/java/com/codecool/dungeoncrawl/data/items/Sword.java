package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Player;

public class Sword extends Item {

    private static final int ACTION_POINT = 5;

    public Sword(Cell cell) {
        super(cell, "sword");
    }

    @Override
    public String getTileName() {
        return "sword";
    }


    @Override
    public void applyEffect(Player player) {
        player.setAttackPower(ACTION_POINT);
        player.setTileName("swordedPlayer");
    }
}
