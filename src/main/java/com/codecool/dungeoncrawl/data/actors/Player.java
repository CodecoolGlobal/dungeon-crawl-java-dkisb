package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.data.items.Potion;
import com.codecool.dungeoncrawl.data.items.Shield;
import com.codecool.dungeoncrawl.data.items.Sword;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {
    Cell activeCell;
    private List<Item> inventory = new ArrayList<>();
    private boolean swordBonusApplied = false;
    private boolean shieldBonusApplied = false;
    private boolean potionBonusApplied = false;
    private static int BASE_HEALTH = 20;


    public Player(Cell cell) {
        super(cell, BASE_HEALTH);
        this.activeCell = cell;
    }

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
            setHealth(BASE_HEALTH += 10);
        } else if (hasSword && !swordBonusApplied) {
            swordBonusApplied = true;
            setHealth(BASE_HEALTH += 5);
        } else if (hasPotion && !potionBonusApplied) {
            potionBonusApplied = true;
            setHealth(BASE_HEALTH += 15);
        }
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (!cell.getNeighbor(dx, dy).getTileName().equals("wall")) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
            checkItemPickup();
        }
    }

}
