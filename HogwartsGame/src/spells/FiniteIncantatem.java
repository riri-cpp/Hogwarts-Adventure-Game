package spells;

public class FiniteIncantatem extends Spell { // from Spell class
    
    public FiniteIncantatem(){
        super("Finite Incantatem", "A special spell that you learned during your investigation.", 0);
    }

    @Override
    public void cast(){
        System.out.println("\nYou point your wand and shout \"FINITE INCANTATEM!\"");
        System.out.println("Nothing happened...");
    }

    public static void specialCast(){
        System.out.println("You point your wand and shout \"FINITE INCANTATEM!\"");
        System.out.println("A blast of white light cancels the curse!");
    }

}
