package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.logic.CombatHandler;
import com.codecool.dungeoncrawl.logic.LevelHandler;

import java.util.List;
import java.util.Random;

public class Enemy extends Actor {
    private Random random;
    private int attackPower;
    private final CombatHandler combatHandler = new CombatHandler();

    public Enemy(Cell cell, int baseHealth, Random random) {
        super(cell, baseHealth);
        this.random = random;
    }

    @Override
    public String getTileName() {
        return "enemy";
    }

    private boolean checkForPlayer() {
        return  isPlayerAt(cell.getNeighbor(1, 0)) ||
                isPlayerAt(cell.getNeighbor(-1,0)) ||
                isPlayerAt(cell.getNeighbor(0,1)) ||
                isPlayerAt(cell.getNeighbor(0, -1));
    }

    private boolean isPlayerAt( Cell neighbor) {
        return neighbor != null && neighbor.getActor() instanceof Player;
    }

    public void update() {
        if(cell == null) return;
        if (checkForPlayer()) {
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (dx == 0 && dy == 0) continue;
                    Cell neighbor = cell.getNeighbor(dx, dy);
                    if (isPlayerAt(neighbor)) {
                        Player player = (Player) neighbor.getActor();
                        combatHandler.handleEnemyAttacksToPlayer(this, player);
                        System.out.println("Combat triggered between enemy and player!");
                    }
                }
            }
        } else {
            moveRandomly();
        }
    }

    private void moveRandomly() {
        int index = random.nextInt(4);
        switch (index) {
            case 0 -> move(1, 0);
            case 1 -> move( -1, 0);
            case 2 -> move( 0, -1);
            case 3 -> move( 0, 1);
        }
    }

    public int getAttackPower () {
        return attackPower;
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);

        if (!nextCell.getTileName().equals("wall") && nextCell.getActor() == null) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    @Override
    public void handleDeath() {
        super.handleDeath();
    }
}
