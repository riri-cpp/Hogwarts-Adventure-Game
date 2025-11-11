// src/characters/LunaLovegood.java
package characters;

public class LunaLovegood extends Student {
    
    public LunaLovegood() {
        super("Luna Lovegood", "Ravenclaw", 1, "Magical Creatures");
    }
    
    @Override
    public void introduce() {
        System.out.println("Luna: \"Hello! Have you seen any Nargles around? I'm Luna.\"");
    }
    
    @Override
    public String reactToAction(String action) {
        switch (action) {
            case "ask about nargles":
                increaseFriendship(20);
                return "Oh! You know about Nargles! They're particularly fond of mistletoe.";
            case "talk about creatures":
                increaseFriendship(15);
                return "I believe in Crumple-Horned Snorkacks. Daddy's writing about them.";
            case "ask about ravenclaw":
                increaseFriendship(10);
                return "The common room has a lovely ceiling with stars. And the door asks riddles!";
            case "be logical":
                decreaseFriendship(8);
                return "Not everything has to make sense, you know.";
            case "ask about glasses":
                increaseFriendship(5);
                return "They help me see the Wrackspurts. You have quite a few around your head.";
            case "offer friendship":
                increaseFriendship(25);
                return "That's very nice of you. Most people think I'm a bit odd.";
            default:
                return "See you when I see you.";
        }
    }
    
    public void mentionWrackspurts() {
        System.out.println("Luna: \"You might want to check for Wrackspurts. They make your brain go fuzzy.\"");
    }

    public void increaseFriendship(int amount) {
        socialLink += amount;
        System.out.println(name + "'s friendship +" + amount + "!");
    }
    
    public void decreaseFriendship(int amount) {
        socialLink -= amount;
        System.out.println(name + "'s friendship -" + amount + "!");
    }
}
