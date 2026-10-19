package com.bello.rpg.model;

public class Mage extends Character{

    private int mana, maxMana;

    public Mage(String name, int maxHealth, int attackPower){



        super(name, maxHealth, attackPower);
        this.maxMana = 50;
        this.mana = 50;

    }

    @Override
    public void attack(Character target){

        int spellDamage = getAttackPower() + 5;

        if(target.isAlive()){

            target.takeDamage(spellDamage);

            System.out.println("[Mage] " + getName() + " slashes " + target.getName() + " for " + spellDamage + " damage!!");



        }


    }

    public int getMana(){

        return mana;
    }

     public void castFireball(Character target){
        int manaCost = 20;
        if(mana >= manaCost){

            mana -= 20;
            target.takeDamage(getAttackPower() + 15);
            System.out.println("Fireball unleashed, remaining mana:" + getMana() );

        }

        if(mana < manaCost){

            System.out.println("Not enough mana, normal attack will be applied");
            target.takeDamage(getAttackPower());
        }
     }

}
