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
    private String name = "player"; //TODO: getter and setter
    private static final int BASE_HEALTH = 20;


    public Player(Cell cell) {
        super(cell, BASE_HEALTH);
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


    public void checkItemPickup(){
        Item item = cell.getItem();
        if (item != null) {
            inventory.add(item);
            item.applyEffect(this); //TODO: Implement this abstract method
            cell.setItem(null);
        }
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getActor() instanceof Enemy) {
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
