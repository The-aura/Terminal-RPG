package com.bello.rpg.model;

public class Warrior extends Character{

    public Warrior(String name, int maxHealth, int attackPower){

        super(name, maxHealth, attackPower);


    }

@Override
    public void attack(Character target){

        if(target.isAlive()){

            target.takeDamage(getAttackPower());

            System.out.println("[Warrior] " + getName() + " slashes " + target.getName() + " for " + getAttackPower() + " damage!!");

        }

    }
public void heavyStrike(Character target){

        if(target.isAlive()){

            target.takeDamage(getAttackPower() * 2);
            this.takeDamage(5);
            System.out.println("Heavy Strike unleashed, recioil damage of 5HP taken! ");

        }
}
}