package com.bello.rpg.service;

import com.bello.rpg.model.Character;
import com.bello.rpg.model.Mage;
import com.bello.rpg.model.Warrior;
import java.util.*;

public class BattleService {

    public void startBattle(Character player, Character enemy) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Battle Begins!!! Round 1: " + player.getName() + " vs " + enemy.getName());

        while (player.isAlive() && enemy.isAlive()) {
            System.out.println("\nYOUR TURN! " + player.getName());
            System.out.println("[1] Attack ");
            System.out.println("[2] Heal ");
            System.out.println("[3] Special Ability ");
            System.out.println("[4] Use item! ");
            System.out.print("Choose action: ");

            int choice = scanner.nextInt();

            // 1. Handle Player Choices
            if (choice == 1) {
                player.attack(enemy);
            } else if (choice == 2) {
                player.heal(15);
                System.out.println(player.getName() + " heals for 15 HP");
            } else if (choice == 3) {
                if (player instanceof Mage) {
                    ((Mage) player).castFireball(enemy);
                } else if (player instanceof Warrior) {
                    ((Warrior) player).heavyStrike(enemy);
                }
            } else if (choice == 4) {
                player.getInventory().displayItems();
                System.out.print("Choose item number to use (or 0 to go back): ");
                int itemChoice = scanner.nextInt();

                if (itemChoice > 0) {
                    boolean used = player.getInventory().useItem(itemChoice - 1, player);
                    if (!used) {
                        System.out.println("Choose something else!");
                    }
                } else {
                    System.out.println("Cancelled item usage.");
                }
            } else {
                System.out.println("Invalid choice, you lost your turn!");
            }

            // 2. Check if the player killed the enemy
            if (!enemy.isAlive()) {
                break;
            }

            // 3. Enemy Turn (Now executes properly for ALL choices!)
            System.out.println("\n--- ENEMY TURN (" + enemy.getName() + ") ---");
            enemy.attack(player);
            System.out.println("STATUS! " + player.getName() + ": " + player.getHealth() + " HP | " + enemy.getName() + ": " + enemy.getHealth() + " HP\n");
        }

        // 4. Post-Battle Outcome
        if (player.isAlive()) {
            System.out.println("  " + player.getName() + " Wins!!");
        } else {
            System.out.println(" " + enemy.getName() + " Wins!!");
        }
    }
}