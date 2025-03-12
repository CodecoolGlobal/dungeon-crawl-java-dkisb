package com.codecool.dungeoncrawl.ui.keyeventhandler;

import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.MapLoader;
import java.util.Random;

public class SkeletonMove implements KeyHandler {
    Random random = new Random();

    @Override
    public void perform(javafx.scene.input.KeyEvent event, GameMap map) {
        for (Skeleton skeleton : map.getSkeletons()) {
            int index = random.nextInt(4);
            switch (index) {
                case 0 -> skeleton.move(1, 0);
                case 1 -> skeleton.move(-1, 0);
                case 2 -> skeleton.move(0, -1);
                case 3 -> skeleton.move(0, 1);
            }
        }
    }

}
