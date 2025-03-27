package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.actors.Enemy;
import com.codecool.dungeoncrawl.data.actors.Player;

public class CombatHandler {

    public CombatHandler() {
    }

    public void handlePlayerAttacksToEnemy(Player player, Enemy enemy) {
        int damageToEnemy = player.getAttackPower();
        enemy.setHealth(enemy.getHealth() - damageToEnemy);
        if (enemy.getHealth() <= 0) {
            enemy.handleDeath();
        }
    }

    public void handleEnemyAttacksToPlayer(Enemy enemy, Player player) {
        int damageToPlayer = enemy.getAttackPower();
        player.setHealth(player.getHealth() - damageToPlayer);
        if (player.getHealth() <= 0) {
            player.handleDeath();
        }
    }
}

