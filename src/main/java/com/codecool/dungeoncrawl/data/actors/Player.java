package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.data.items.Potion;
import com.codecool.dungeoncrawl.data.items.Shield;
import com.codecool.dungeoncrawl.data.items.Sword;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {
    Cell cell;
    private List<Item> inventory = new ArrayList<>();
  
    public Player(Cell cell, GameMap gameMap) {
        super(cell,20, gameMap);
        this.cell = cell;
    }
  
    private boolean swordBonusApplied = false;
    private boolean shieldBonusApplied = false;
    private boolean potionBonusApplied = false;


    public String getTileName() {
        boolean hasSword = hasItem(Sword.class);
        boolean hasShield = hasItem(Shield.class);
        boolean hasPotion = hasItem(Potion.class);

        applyHealthBonus(hasSword, hasShield, hasPotion);

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

    public void checkItemPickup(){
        if (this.getCell().getItem() != null) {
            this.setInventoryContent(this.getCell().getItem());
            this.getCell().setItem(null);
        }
    }
    private boolean hasItem(Class<? extends Item> itemType) {
        return inventory.stream().anyMatch(itemType::isInstance);
    }

    private void applyHealthBonus(boolean hasSword, boolean hasShield, boolean hasPotion) {
        if (hasSword && hasShield && !shieldBonusApplied) {
            shieldBonusApplied = true;
            setHealth(getHealth() + 10);
        } else if (hasSword && !swordBonusApplied) {
            swordBonusApplied = true;
            setHealth(getHealth() + 5);
        } else if (hasPotion && !potionBonusApplied) {
            potionBonusApplied = true;
            setHealth(getHealth() + 15);
        }
    }

}
