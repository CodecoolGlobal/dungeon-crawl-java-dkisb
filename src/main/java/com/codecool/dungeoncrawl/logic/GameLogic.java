package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.Enemy;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.actors.Skeleton;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.databaseaccess.DaoJdbc;

import java.awt.print.Book;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class GameLogic {
    private GameMap map;
    private final LevelHandler levelHandler = new LevelHandler();
    private final CombatHandler combatHandler = new CombatHandler();
    private final GameReset gameReset = new GameReset(this);
    private Player player;

    public GameLogic() {
        this.map = levelHandler.loadNextLevel(null,combatHandler, gameReset);
    }

    public double getMapWidth() {
        return map.getWidth();
    }

    public double getMapHeight() {
        return map.getHeight();
    }

    public void setup() {
    }

    public Cell getCell(int x, int y) {
        return map.getCell(x, y);
    }

    public Player getPlayer() {
        return map.getPlayer();
    }

    public String getPlayerHealth() {
        return Integer.toString(map.getPlayer().getHealth());
    }

    public List<Item> getPlayerInventory() {
        return map.getPlayer().getInventory();
    }

    public List<Item> getItems() {
        return map.getItems();
    }

    public void moveEnemies() {
        for (Enemy enemy : map.getEntities()) {
            if(enemy.isDead()) continue;
            enemy.update();
        }
        map.getEntities().removeIf(Enemy::isDead);
    }

    public void checkForLevelProgression() {
        Player player = getPlayer();
        if (player.isAbleToProgress()) {
            String currentName = player.getTileName();
            player.setTileName(currentName);
            this.map = levelHandler.loadNextLevel(player, combatHandler, gameReset);
        }
    }

    public int save() {
        int id;
        try(Connection conn = DaoJdbc.connect().getConnection()) {
            String sql = "INSERT INTO saves (date, level) VALUES (?, ?)";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            LocalDateTime now = LocalDateTime.now().withNano(0);;
            java.sql.Timestamp timestamp = Timestamp.valueOf(now);
            st.setTimestamp(1, timestamp);
            st.setInt(2, levelHandler.getCurrentLevel());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } catch (SQLException throwables) {
            throw new RuntimeException("Error while adding new Save.", throwables);
        }
        return id;
    }

    public void saveInventory(int id) {
        try(Connection conn = DaoJdbc.connect().getConnection()) {
            for (Item item : getPlayerInventory()) {
                String sql = "INSERT INTO inventory (name, saves_id) VALUES (?, ?)";
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, item.getName());
                st.setInt(2, id);
                st.executeUpdate();
                ResultSet rs = st.getGeneratedKeys();
                rs.next();
            }
        } catch (SQLException throwables) {
            throw new RuntimeException("Error while adding new Save.", throwables);
        }
    }

    public void saveItems(int id) {
        try(Connection conn = DaoJdbc.connect().getConnection()) {
            for (Item item : getItems()) {
                String sql = "INSERT INTO items (name, xcoord, ycoord, saves_id) VALUES (?, ?, ?, ?)";
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, item.getName());
                st.setInt(2, item.getCell().getX());
                st.setInt(3, item.getCell().getY());
                st.setInt(4, id);
                st.executeUpdate();
                ResultSet rs = st.getGeneratedKeys();
                rs.next();
            }
        } catch (SQLException throwables) {
            throw new RuntimeException("Error while adding new Items.", throwables);
        }
    }

    public void saveEnemies(int id) {
        try(Connection conn = DaoJdbc.connect().getConnection()) {
            for (Enemy enemy : map.getEntities()) {
                String sql = "INSERT INTO actors (name, xcoord, ycoord, health, saves_id) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, enemy.getTileName());
                st.setInt(2, enemy.getCell().getX());
                st.setInt(3, enemy.getCell().getY());
                st.setInt(4, enemy.getHealth());
                st.setInt(5, id);
                st.executeUpdate();
                ResultSet rs = st.getGeneratedKeys();
                rs.next(); // Read next returned value - in this case the first one. See ResultSet docs for more explaination
            }
        } catch (SQLException throwables) {
            throw new RuntimeException("Error while adding new Enemies.", throwables);
        }
    }

    public void savePlayer(int id) {
        Player player = getPlayer();
        try(Connection conn = DaoJdbc.connect().getConnection()) {
            String sql = "INSERT INTO actors (name, xcoord, ycoord, health, saves_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, player.getTileName());
            st.setInt(2, player.getCell().getX());
            st.setInt(3, player.getCell().getY());
            st.setInt(4, player.getHealth());
            st.setInt(5, id);
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next();
        } catch (SQLException throwables) {
            throw new RuntimeException("Error while adding new Player.", throwables);
        }
    }

    public void displaySaves() {
        try (Connection conn = DaoJdbc.connect().getConnection()) {
            String sql = "SELECT id, date, level FROM saves";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            String saves = "";
            while (rs.next()) { // while result set pointer is positioned before or on last row read authors
                saves += "ID: " + rs.getString(1) + ", Date: " + rs.getString(2) + ", Level: " + rs.getString(3) + "\n";
            }
            System.out.println(saves);
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all saves", e);
        }
    }
/*
    public List<Actor> getActors(int savesId) {
        List<Actor> actors = new ArrayList<>();
        try (Connection conn = DaoJdbc.connect().getConnection()) {
            String sql = "SELECT name, xcoord, ycoord, health FROM saves WHERE saves_id = " + savesId;
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()) { // while result set pointer is positioned before or on last row read authors
                String name = rs.getString(1);
                int x = rs.getInt(2);
                int y = rs.getInt(3);
                int health = rs.getInt(4);
                //int skeletonX = 1;
                //int skeletonY = 1;
                Cell actorCell = map.getCell(x, y);
                if (name.equals("player")) {
                    Player player = new Player(actorCell);
                    player.setHealth(health);
                } else if (name.equals("skeleton")) {
                    Skeleton skeleton
                }
                Skeleton skeleton = new Skeleton(skeletonCell, random);
                map.addEntities(skeleton);
                

                // THRID STEP - find author with id == authorId
                Author author = authorDao.get(authorId);

                // FOURTH STEP - create a new Book class instance and add it to result list.
                Book book = new Book(author, title);
                book.setId(id);
                result.add(book);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all saves", e);
        }
        return actors;
    }

 */
 

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("If you want to start a new game, press 'n'");
            System.out.println("If you want to continue a saved game, press 'l'");
            String option = scanner.nextLine();
            switch (option) {
                case "n": isRunning = false; break;
                case "l": displaySaves();
                          int id = chooseId();
                          //map = levelHandler.setLevel(2, getPlayer()); //boolean true? 3. parameter
/*

                          //load skeleton
                          int skeletonX = 1;
                          int skeletonY = 1;
                          Cell skeletonCell = map.getCell(skeletonX, skeletonY);
                          Skeleton skeleton = new Skeleton(skeletonCell, random);
                          map.addEntities(skeleton);

                            //map.setLoaded(true);

 */
                    System.out.println(levelHandler.getCurrentLevel());
                          isRunning = false; break;
                default: System.out.println("Invalid option!"); break;
            }
        }
        scanner.close();
    }

    public int chooseId() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        boolean isRunning = true;
        while (isRunning) {
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                isRunning = false;
            } catch (InputMismatchException e) {
                System.out.println("Not a number");
                System.out.println("Please enter a valid ID");
                scanner.nextLine();
            }
        }
        scanner.close();
        return choice;
    }

    public void resetGame() {
        levelHandler.resetToFirstLevel();
        this.map = levelHandler.loadNextLevel(null,combatHandler, gameReset);
    }
}
