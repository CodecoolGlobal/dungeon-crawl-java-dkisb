package com.codecool.dungeoncrawl.ui.keyeventhandler;

import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Player;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Right implements KeyHandler {
    public static final KeyCode code = KeyCode.RIGHT;

    @Override
    public void perform(KeyEvent event, Player player) {
        if(code.equals(event.getCode())) {
            player.move(1, 0);
        }

    }
}
