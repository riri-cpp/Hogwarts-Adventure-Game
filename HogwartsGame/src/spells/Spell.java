package spells;

public abstract class Spell {
    protected String name;
    protected String description;
    protected int difficulty;

    // every spell you learn has a name, description, and difficulty
    public Spell(String name, String description, int difficulty){
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
    }

    // makes you "cast" the spell
    public abstract void cast();

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getDifficulty(){
        return difficulty;
    }
}
