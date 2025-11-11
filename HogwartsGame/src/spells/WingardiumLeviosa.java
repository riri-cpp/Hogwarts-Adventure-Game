package spells;

public class WingardiumLeviosa extends Spell{ // from Spell class
    
    public WingardiumLeviosa(){
        super("Wingardium Leviosa", "Makes objects levitate", 50);
    }

    @Override
    public void cast(){
        System.out.println("You swish and flick your wand: \"Wingardium Leviosa!\"");
        System.out.println("A feather rises into the air and floats gracefully!");
    }
}
