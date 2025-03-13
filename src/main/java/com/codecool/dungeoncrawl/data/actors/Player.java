package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.data.items.Potion;
import com.codecool.dungeoncrawl.data.items.Shield;
import com.codecool.dungeoncrawl.data.items.Sword;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {
    private List<Item> inventory = new ArrayList<>();

    private boolean swordBonusApplied = false;
    private boolean shieldBonusApplied = false;
    private boolean potionBonusApplied = false;

    public Player(Cell cell) {
        super(cell);
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
