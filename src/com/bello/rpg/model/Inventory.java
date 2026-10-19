package com.bello.rpg.model;

import java.util.*;

public class Inventory {

    private List<Item> items = new ArrayList<>();

    public void addItem( Item item){

    items.add(item);

    }


    public void displayItems() {
        if (items.isEmpty()) {
            System.out.println("Your inventory is empty!");
            return;
        }

        System.out.println("--- INVENTORY ---");
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            System.out.println("[" + (i + 1) + "] " + item.getName() + " (+ " + item.getHealAmount() + " HP)");
        }
    }

    public boolean useItem(int index, Character target) {

        if (index < 0 || index >= items.size()) {
            System.out.println("Invalid item choice!");
            return false;
        }

        Item item = items.remove(index);
        target.heal(item.getHealAmount());
        System.out.println(target.getName() + " used " + item.getName() + "!");
        return true;
    }


    public List<Item> getItems() {
        return items; 
    }

}
