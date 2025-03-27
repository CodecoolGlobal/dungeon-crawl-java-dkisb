package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.actors.Enemy;
import com.codecool.dungeoncrawl.data.actors.Player;

public class CombatHandler {
    private final LevelHandler levelHandler;

    public CombatHandler(LevelHandler levelHandler) {
        this.levelHandler = levelHandler;
    }

    public void handlePlayerAttacksToEnemy(Player player, Enemy enemy) {
        int damageToEnemy = player.getAttackPower();
        enemy.setHealth(enemy.getHealth() - damageToEnemy);
        System.out.println("Enemy takes " + damageToEnemy + " damage. Remaining health: " + enemy.getHealth());
        if (enemy.getHealth() <= 0) {
            enemy.handleDeath();
        }
    }

    public void handleEnemyAttacksToPlayer(Enemy enemy, Player player) {
        int damageToPlayer = enemy.getAttackPower();
        player.setHealth(player.getHealth() - damageToPlayer);
        System.out.println("Player takes " + damageToPlayer + " damage. Remaining health: " + player.getHealth());
        if (player.getHealth() <= 0) {
            player.handleDeath();
            resetGame(player);
        }

    }

    private void resetGame(Player player) {
        levelHandler.resetToFirstLevel();
        int baseHealthReset = 20;
        player.setHealth(baseHealthReset);
        player.getInventory().clear(); // Clear inventory
    }
}

