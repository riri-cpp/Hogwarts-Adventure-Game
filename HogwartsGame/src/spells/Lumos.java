package spells;

public class Lumos extends Spell { // from Spell class
    
    public Lumos(){
        super("Lumos", "Creates light at the tip of the wand", 30);
    }

    @Override
    public void cast(){
        System.out.println("You point your wand and shout \"LUMOS!\"");
        System.out.println("The tip of your wand glows with a bright white light!");
    }
}
