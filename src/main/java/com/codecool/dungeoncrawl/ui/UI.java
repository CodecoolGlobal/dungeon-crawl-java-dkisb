package com.codecool.dungeoncrawl.ui;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.logic.GameLogic;
import com.codecool.dungeoncrawl.ui.elements.MainStage;
import com.codecool.dungeoncrawl.ui.keyeventhandler.KeyHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Set;
import java.util.stream.Collectors;

public class UI {
    private Canvas canvas;
    private GraphicsContext context;

    private MainStage mainStage;
    private GameLogic logic;
    private Set<KeyHandler> keyHandlers;

    private String previousHealthValue;
    private String previousInventory;

    public UI(GameLogic logic, Set<KeyHandler> keyHandlers) {
        this.canvas = new Canvas(
                logic.getMapWidth() * Tiles.TILE_WIDTH,
                logic.getMapHeight() * Tiles.TILE_WIDTH);
        this.logic = logic;
        this.context = canvas.getGraphicsContext2D();
        this.mainStage = new MainStage(canvas);
        this.keyHandlers = keyHandlers;
    }

    public void setUpPain(Stage primaryStage) {
        Scene scene = mainStage.getScene();
        primaryStage.setScene(scene);
        logic.setup();
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        for (KeyHandler keyHandler : keyHandlers) {
            if (!keyEvent.getCode().equals(KeyCode.S)) {
                keyHandler.perform(keyEvent, logic.getPlayer()); //logic.getPlayer() instead
            }
        }

        if (keyEvent.getCode().equals(KeyCode.S)) {
            int id = logic.save();
            logic.saveInventory(id);
            logic.saveEnemies(id);
            logic.savePlayer(id);
            logic.saveItems(id);

        } else {
            logic.moveEnemies();
        }
        logic.checkForLevelProgression();
        refresh();
    }

    public void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < logic.getMapWidth(); x++) {
            for (int y = 0; y < logic.getMapHeight(); y++) {
                Cell cell = logic.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }

        String currentHealth = logic.getPlayerHealth();
        if (!currentHealth.equals(previousHealthValue)) {
            mainStage.setHealthLabelText(currentHealth);
            previousHealthValue = currentHealth;
        }

        if (!logic.getPlayerInventory().isEmpty()) {
            String inventoryText = logic.getPlayerInventory().stream()
                    .map(Item::getName)
                    .collect(Collectors.joining("\n"));
            if (!inventoryText.equals(previousInventory)) {
                mainStage.setInventoryContentText(inventoryText.isEmpty() ? "Inventory is empty." : inventoryText);
                previousInventory = inventoryText;
            }
        } else {
            mainStage.setInventoryContentText("Inventory is empty.");
        }
    }
}
