package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.actors.Enemy;
import com.codecool.dungeoncrawl.data.actors.Player;

public class CombatHandler {
    private final LevelHandler levelHandler;

    public CombatHandler(LevelHandler levelHandler) {
        this.levelHandler = levelHandler;
    }

    public void handleCombat(Player player, Enemy enemy) {
        while(player.getHealth() <= 0 || enemy.getHealth() <= 0) {
        enemy.setHealth(enemy.getHealth() - player.getAttackPower());

        if(enemy.getHealth() <= 0){
            enemy.handleDeath();
            enemy.getCell().setActor(null);
            return;
        }

        player.setHealth(player.getHealth() - enemy.getAttackPower());

        if(player.getHealth() <= 0){
            player.handleDeath();
            resetGame(player);
            return;
        }
        }
    }

    private void resetGame(Player player) {
        levelHandler.resetToFirstLevel();
        int baseHealthReset = 20;
        player.setHealth(baseHealthReset);
        player.getInventory().clear(); // Clear inventory
    }
}

