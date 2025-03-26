package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.Ghost;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.actors.Skeleton;
import com.codecool.dungeoncrawl.data.items.Key;
import com.codecool.dungeoncrawl.data.items.Potion;
import com.codecool.dungeoncrawl.data.items.Shield;
import com.codecool.dungeoncrawl.data.items.Sword;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MapLoader {

    public static GameMap loadMap(String mapName, Player player) {
        InputStream is = MapLoader.class.getResourceAsStream("/" + mapName);
        Scanner scanner = new Scanner(is);
        Random random = new Random();
        int width = scanner.nextInt();
        int height = scanner.nextInt();


        scanner.nextLine();


        GameMap map = new GameMap(width, height, CellType.EMPTY);
        Cell playerSpawn = null;

        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 'e':
                            cell.setType(CellType.FLOOR);
                            int result = random.nextInt(2);
                            if (result == 0) {
                                Skeleton skeleton = new Skeleton(cell, random);
                                map.addEntities(skeleton);
                            } else {
                                Ghost ghost = new Ghost(cell, random);
                                map.addEntities(ghost);
                            }
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            playerSpawn = cell;
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            cell.setItem(new Key(cell));
                            break;
                        case 'y':
                            cell.setType(CellType.FLOOR);
                            cell.setItem(new Potion(cell));
                            break;
                        case 'x':
                            cell.setType(CellType.FLOOR);
                            cell.setItem(new Shield(cell));
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            cell.setItem(new Sword(cell));
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        if(player != null) {
            player.setCell(playerSpawn);
            map.setPlayer(player);
        } else {
            map.setPlayer(new Player(playerSpawn));
        }
        return map;

    }
}
