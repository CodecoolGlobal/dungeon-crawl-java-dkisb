package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.data.items.Key;
import com.codecool.dungeoncrawl.logic.CombatHandler;
import com.codecool.dungeoncrawl.logic.GameReset;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {
    private List<Item> inventory = new ArrayList<>();
    private String name = "player";
    private static final int BASE_HEALTH = 20;
    private int ATTACK_POWER = 0;
    private final CombatHandler combatHandler;
    private GameReset gameReset;

    public Player(Cell cell, CombatHandler combatHandler, GameReset gameReset) {
        super(cell, BASE_HEALTH);
        this.combatHandler = combatHandler;
        this.gameReset = gameReset;
    }

    public String getTileName() {
        return name;
    }

    public void setTileName(String name) {
        this.name = name;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setAttackPower(int attackPower) {
        this.ATTACK_POWER = attackPower;
    }

    public int getAttackPower() {
        return ATTACK_POWER;
    }


    public void checkItemPickup() {
        Item item = cell.getItem();
        if (item != null) {
            inventory.add(item);
            item.applyEffect(this);
            cell.setItem(null);
        }
    }

    public boolean isAbleToProgress() {
        if (getHealth() > 0 && hasKey()) {
            useKey();
            return true;
        }
        return false;
    }

    public boolean hasKey() {
        return inventory.stream().anyMatch(item -> item instanceof Key);
    }

    public void useKey() {
        inventory.removeIf(item -> item instanceof Key);
    }

    @Override
    public void move(int dx, int dy) {
        if(cell != null) {
            Cell nextCell = cell.getNeighbor(dx, dy);
            if (nextCell.getActor() instanceof Enemy enemy) {
                combatHandler.handlePlayerAttacksToEnemy(this, enemy);
                return;
            }
            if (!cell.getNeighbor(dx, dy).getTileName().equals("wall") && nextCell.getActor() == null) {
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
                checkItemPickup();
            }
        }
    }

    @Override
    public void handleDeath() {
        super.handleDeath();
        gameReset.resetGame();
    }
}
