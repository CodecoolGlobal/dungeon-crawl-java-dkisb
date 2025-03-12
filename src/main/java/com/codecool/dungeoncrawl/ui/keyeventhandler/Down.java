package com.codecool.dungeoncrawl.ui.keyeventhandler;

import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.MapLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Down implements KeyHandler {
    public static final KeyCode code = KeyCode.DOWN;



    @Override
    public void perform(KeyEvent event, GameMap map) {

        //map.getSkeleton().move(0, 1);
        if (code.equals(event.getCode())) {
            map.getPlayer().move(0, 1);
    }
}}
