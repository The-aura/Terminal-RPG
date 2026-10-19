package com.bello.rpg.model;
import com.bello.rpg.model.Character;
import com.bello.rpg.model.Mage;
import com.bello.rpg.model.Warrior;
import com.bello.rpg.service.BattleService;

import java.util.*;

public class Room {
    int roomNumber;
    String description;
    boolean isCleared;
    Character enemy;


    public Room(int roomNumber, String description, Character enemy){

        this.description = description;
        this.roomNumber = roomNumber;
        this.isCleared = false;
        this.enemy = enemy;



    }

    public int getRoomNumber(){

        return roomNumber;

    }

    public String getDescription(){

        return  description;

    }



    public void enterRoom(Character player, BattleService action){


        if(isCleared){

            System.out.println("Room cleared, proceed to the next room");

        }else{

            System.out.println("You've entered room number:" + getRoomNumber() + ":" + getDescription() + ", guarded by " + enemy.getName()  );

            action.startBattle(player, enemy);


            if(player.isAlive()){

                isCleared = true;

                player.getInventory().addItem(new Item("Health potion", 40 ));
                System.out.println("Potion added to inventory!");
            }

        }


    }


}
