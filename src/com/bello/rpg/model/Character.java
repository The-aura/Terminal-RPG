package com.bello.rpg.model;
public abstract class Character{
   private int health, maxHealth, attackPower;
   private String name;

   public Character( String name, int maxHealth, int attackPower){

      this.name = name;
      this.attackPower = attackPower;
      this.maxHealth = maxHealth;

      health = maxHealth;

   }

    public String getName(){

      //System.out.println("My name is " + name);
 return name;
    }

   public int getAttackPower() {
      return attackPower;
   }

   public int getHealth() {
      return health;

   }

   public int getMaxHealth() {
      return maxHealth;
   }

   public void takeDamage(int damage) {

      if (damage <= 0) {

          System.out.println("Invalid attack");

         return;


      }
      health -= damage;

      if (health < 0) {

         health = 0;


      }

   }

   public void heal(int amount){

         if (amount <= 0) {
            return;
         }
         health += amount;

         if (health > maxHealth){


            health = maxHealth;

         }


      }

   public abstract void attack( Character target);

   public boolean isAlive(){

      return (health > 0);

   }

    // Add this field inside Character.java
    protected Inventory inventory = new Inventory();

    // Add this getter so other classes can access the inventory
    public Inventory getInventory() {
        return inventory;
    }


    public void setName(){

        this.name = name;
    }

    public void setHealth(int health){
        this.health = health;

    }

    public void setMaxHealth(int maxHealth){
        this.maxHealth = maxHealth;

    }

    public void setAttackPower(int attackPower) {
            this.attackPower = attackPower;
    }
}






