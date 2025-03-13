package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.data.items.Shield;
import com.codecool.dungeoncrawl.data.items.Sword;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {
    private List<Item> inventory = new ArrayList<>();
    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        boolean hasSword = getInventory().stream().anyMatch(item -> item instanceof Sword);
        boolean hasShield = getInventory().stream().anyMatch(item -> item instanceof Shield);
        if (hasSword && hasShield) {
            return "shieldedPlayer";
        } else if (hasSword) {
            return "swordedPlayer";
        }
        return "player";
    }
    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventoryContent(Item item) {
        inventory.add(item);
    }

    public void checkItemPickup(){
        if (this.getCell().getItem() != null) {
            this.setInventoryContent(this.getCell().getItem());
            this.getCell().setItem(null);
        }
    }
}
