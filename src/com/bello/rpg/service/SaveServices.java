package com.bello.rpg.service;

import com.bello.rpg.model.Character;
import com.bello.rpg.model.Item;
import com.bello.rpg.model.Mage;
import com.bello.rpg.model.Warrior;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class SaveServices {

    public static int loadedRoomIndex = 0;

    public static void saveGame(Character player, int currentRoomIndex) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("savegame.txt"))) {
            // Write core character stats & current room index
            writer.println(player.getClass().getSimpleName());
            writer.println(player.getName());
            writer.println(player.getHealth());
            writer.println(player.getMaxHealth());
            writer.println(player.getAttackPower());
            writer.println(currentRoomIndex);

            // Save inventory item count and individual item names
            List<Item> items = player.getInventory().getItems();
            writer.println(items.size());
            for (Item item : items) {
                writer.println(item.getName());
            }

            System.out.println("\n[✔] Game saved successfully to savegame.txt!");
        } catch (IOException e) {
            System.out.println("\n[✖] Error saving game: " + e.getMessage());
        }
    }

    public static Character loadGame() {
        File file = new File("savegame.txt");
        if (!file.exists()) {
            System.out.println("\n[✖] No save file found!");
            return null;
        }

        try (Scanner reader = new Scanner(file)) {
            if (!reader.hasNextLine()) return null;

            // Read character stats and room index in exact order written
            String characterType = reader.nextLine();
            String name = reader.nextLine();
            int health = Integer.parseInt(reader.nextLine());
            int maxHealth = Integer.parseInt(reader.nextLine());
            int attackPower = Integer.parseInt(reader.nextLine());
            loadedRoomIndex = Integer.parseInt(reader.nextLine());

            // Instantiate correct character class and restore current health
            Character loadedPlayer;
            if (characterType.equalsIgnoreCase("Warrior")) {
                loadedPlayer = new Warrior(name, maxHealth, attackPower);
            } else {
                loadedPlayer = new Mage(name, maxHealth, attackPower);
            }
            loadedPlayer.setHealth(health);

            // Restore saved inventory items
            if (reader.hasNextLine()) {
                int itemCount = Integer.parseInt(reader.nextLine());
                for (int i = 0; i < itemCount; i++) {
                    String itemName = reader.nextLine();

                    // Re-create items (adjust heal amount parameter if your Item constructor differs)
                    if (itemName.equalsIgnoreCase("Health Potion")) {
                        loadedPlayer.getInventory().addItem(new Item("Health Potion", 20));
                    } else {
                        loadedPlayer.getInventory().addItem(new Item(itemName, 20));
                    }
                }
            }

            System.out.println("\n[✔] Game loaded successfully!");
            return loadedPlayer;

        } catch (Exception e) {
            System.out.println("\n[✖] Error loading save file: " + e.getMessage());
            return null;
        }
    }
}