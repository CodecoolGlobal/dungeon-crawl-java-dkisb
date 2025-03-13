package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.data.items.Shield;
import com.codecool.dungeoncrawl.data.items.Sword;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {
    Cell cell;
    private List<Item> inventory = new ArrayList<>();
    public Player(Cell cell, GameMap gameMap) {
        super(cell, gameMap);
        this.cell = cell;
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

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        System.out.println(nextCell.getX() + " " + nextCell.getY());
        if (!cell.getNeighbor(dx, dy).getTileName().equals("wall")) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventoryContent(Item item) {
        inventory.add(item);
    }
}
