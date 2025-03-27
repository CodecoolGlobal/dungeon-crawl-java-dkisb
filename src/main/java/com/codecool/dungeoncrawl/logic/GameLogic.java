package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.Enemy;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.data.items.Key;
import com.codecool.dungeoncrawl.databaseaccess.DaoJdbc;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameLogic {
    private GameMap map;

    public GameLogic(String mapName) {
        this.map = MapLoader.loadMap(mapName);
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

    public void moveEnemies() {
        for (Enemy enemy : map.getEntities()) {
            enemy.update();
        }
        map.getEntities().removeIf(Enemy:: isDead);

    }

    public int save() {
        int id;
        try(Connection conn = DaoJdbc.connect().getConnection()) {
            String sql = "INSERT INTO saves (date, level) VALUES (?, ?)";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            LocalDateTime now = LocalDateTime.now();
            java.sql.Date date = java.sql.Date.valueOf(now.toLocalDate());
            //st.setString(1, now.toString());
            st.setDate(1, date);
            st.setInt(2, 1);
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next(); // Read next returned value - in this case the first one. See ResultSet docs for more explaination
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
                rs.next(); // Read next returned value - in this case the first one. See ResultSet docs for more explaination
                //id = rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throw new RuntimeException("Error while adding new Save.", throwables);
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
                //id = rs.getInt(1);
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
}
