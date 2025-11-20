// src/characters/HarryPotter.java
package characters;

public class HarryPotter extends Student {
    private boolean hasInvisibilityCloak;
    
    public HarryPotter() {
        super("Harry Potter", "Gryffindor", 2, "Defense Against Dark Arts");
        this.hasInvisibilityCloak = true;
    }
    
    @Override
    public void introduce() {
        System.out.println("Harry: \"I'm Harry. This is my second year... it's already been... eventful.\"");
    }
    
    @Override
    public String reactToAction(String action) {
        switch (action) {
            case "help with mystery":
                increaseFriendship(25);
                return "Ron, Hermione, we've got another helper! Thanks!";
            case "mention chamber":
                increaseFriendship(10);
                return "You've heard the voices too? We should talk...";
            case "ask about scar":
                increaseFriendship(5);
                return "It hurts when... well, when something dark is near.";
            case "insult slytherin":
                decreaseFriendship(15);
                return "Not all Slytherins are bad!";
            case "ask about quidditch":
                increaseFriendship(8);
                return "I love Quidditch! I'm the Seeker for Gryffindor.";
            case "talk about family":
                decreaseFriendship(10);
                return "I... don't really like talking about my family.";
            case "offer friendship":
                increaseFriendship(20);
                return "Thanks. It's nice to have another friend around here.";
            case "ask about voldemort":
                decreaseFriendship(5);
                return "Let's not talk about... him.";
            default:
                return "I should probably find Ron and Hermione...";
        }
    }
    
    public void increaseFriendship(int amount) {
        socialLink += amount;
        System.out.println(name + "'s friendship +" + amount + "!");
    }
    public void decreaseFriendship(int amount) {
        socialLink -= amount;
        System.out.println(name + "'s friendship -" + amount + "!");
    }
    
    public void talkAboutChamber() {
        System.out.println("Harry whispers: \"I can hear voices in the walls... 'kill... tear... rip...'\"");
    }
    
    public void showInvisibilityCloak() {
        if (hasInvisibilityCloak) {
            System.out.println("Harry shows you his silvery invisibility cloak. It shimmers mysteriously.");
        }
    }
}