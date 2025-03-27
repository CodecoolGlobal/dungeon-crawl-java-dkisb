package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.ui.UI;

public class GameReset {
    private final GameLogic gameLogic;

    public GameReset(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    public void resetGame() {
        gameLogic.resetGame();
    }
}
