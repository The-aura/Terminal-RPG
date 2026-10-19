package com.bello.rpg.model;
import com.bello.rpg.model.Character;
import com.bello.rpg.model.Warrior;
import com.bello.rpg.model.Mage;


public class Item {
    int healAmount;
    String name;

    public Item( String name, int healAmount){
        this.name = name;
        if (healAmount <= 0) {

            this.healAmount = 10;

        }else{

            this.healAmount = healAmount;
        }


    }

    public String getName(){

        return name;
    }

    public int getHealAmount() {


        return healAmount;

    }



}
