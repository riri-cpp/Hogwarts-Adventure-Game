package game;

import spells.Spell;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private String house;
    private int magicPower;
    private int knowledge;
    private int bravery;
    private int cunning;
    private List<Spell> knownSpells;

    // template of every player object
    public Player(String name){
        this.name = name;
        this.magicPower = 10;
        this.knowledge = 50;
        this.bravery = 50;
        this.cunning = 50;
        this.knownSpells = new ArrayList<>();
    }

    // gets the player's name 
    public String getName(){
        return name;
    }

    // gets the player's house
    public String getHouse(){
        return house;
    }

    public int getMagicPower(){
        return magicPower;
    }

    // sets the player's house based on choice
    public void setHouse(String house){
        if( house.equals("Gryffindor") || house.equals("Slytherin") || house.equals("Ravenclaw") || house.equals("Hufflepuff")){
            this.house = house;
            System.out.println("The Sorting Hat shouts: \"You belong to the House of " + house + "!\"");
        }
        else{
            System.out.println("That is not a valid house.");
        } 
    }

    public void increaseMagic(int amount){
        this.magicPower += amount;
        System.out.println("\nYour magical power has been increased by [" + amount + "]!");
    }

    public void decreaseMagic(int amount){
        this.magicPower -= amount;
        System.out.println("\nYour magical power has been decreased by [" + amount + "]!");
    }

    public void increaseBravery(int amount){
        this.bravery += amount;
        System.out.println("\nYour bravery has been increased by [" + amount + "]!");
    }

    public void decreaseBravery(int amount){
        this.bravery -= amount;
        System.out.println("\nYour bravery has been decreased by [" + amount + "]!");
    }

    public void increaseCunning(int amount){
        this.cunning += amount;
        System.out.println("\nYour cunningness has been increased by [" + amount + "]!");
    }

    public void decreaseCunning(int amount){
        this.cunning -= amount;
        System.out.println("\nYour cunningness has been decreased by [" + amount + "]!");
    }

    public void increaseKnowledge(int amount){
        this.knowledge += amount;
        System.out.println("\nYour knowledge has been increased by [" + amount + "]!");
    }

    public void decreaseKnowledge(int amount){
        this.knowledge -= amount;
        System.out.println("\nYour knowledge has been decreased by [" + amount + "]!");
    }

    public void learnSpell(Spell spell) {
        if (!knownSpells.contains(spell)) {
            knownSpells.add(spell);
            System.out.println("\nYou learned " + spell.getName() + "!\n");
            System.out.println("Spell Description: " + spell.getDescription());
        }
    }

    public boolean knowsSpell(String spellName) {
        return knownSpells.stream().anyMatch(spell -> spell.getName().equals(spellName));
    }

    public void displayStatus(){
        System.out.println("Name: [" + name + "] - House: [" + house + "]");
        System.out.println("Magical Power: " + magicPower);
        System.out.println("Knowledge Possessed: " + knowledge);
        System.out.println("Bravery: " + bravery);
        System.out.println("Cunning: " + cunning);
        System.out.println("\nKnown Spells:");
        for(Spell spell : knownSpells){
            System.out.println(spell.getName());
        }
        System.out.println();
    }

    public void castSpell(String spellName) {
    for (Spell spell : knownSpells) {
        if (spell.getName().equals(spellName)) {
            if (magicPower >= spell.getDifficulty()) {
                spell.cast();
            } else {
                System.out.println("\nYour magic power is too low to cast " + spellName + "!\n");
            }
            return;
        }
    }
    System.out.println("You don't know the spell: " + spellName);
    }

    public boolean canCastComplexSpell(){ 
        return magicPower >= 70; 
    }
    public boolean isBraveEnough() {
        return bravery >= 60; 
    }
}