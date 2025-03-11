package com.codecool.dungeoncrawl.ui.keyeventhandler;

import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.MapLoader;
import javafx.scene.input.KeyCode;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.Random;

import static java.lang.Math.random;

public class SkeletonMove implements KeyHandler {
    //public static final KeyCode code = KeyCode.LEFT;
    //int[][] coordinateModifiers = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    Random random = new Random();
    MapLoader mapLoader;

    @Override
    public void perform(javafx.scene.input.KeyEvent event, GameMap map) {
        int index = random.nextInt(4);
        switch (index) {
            case 0 -> map.getSkeleton().move(1, 0);
            case 1 -> map.getSkeleton().move(-1, 0);
            case 2 -> map.getSkeleton().move(0, -1);
            case 3 -> map.getSkeleton().move(0, 1);
        }
    }




}
