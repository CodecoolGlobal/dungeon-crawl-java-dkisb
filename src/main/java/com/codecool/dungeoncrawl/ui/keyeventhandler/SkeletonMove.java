package com.codecool.dungeoncrawl.ui.keyeventhandler;

import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Actor;
import java.util.Random;

public class SkeletonMove {
    Random random = new Random();


    public void execute(GameMap map) {
        for (Actor actor : map.getEntities()) {
            int index = random.nextInt(4);
            switch (index) {
                case 0 -> actor.move(1, 0);
                case 1 -> actor.move(-1, 0);
                case 2 -> actor.move(0, -1);
                case 3 -> actor.move(0, 1);
            }
        }
    }

}
