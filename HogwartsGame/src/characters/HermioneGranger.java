// src/characters/HermioneGranger.java
package characters;

public class HermioneGranger extends Student {
    
    public HermioneGranger() {
        super("Hermione Granger", "Gryffindor", 2, "Every Subject");
    }
    
    @Override
    public void introduce() {
        System.out.println("Hermione: \"I've already read all our textbooks. Have you started studying?\"");
    }
    
    @Override
    public String reactToAction(String action) {
        switch (action) {
            case "ask about homework":
                increaseFriendship(20);
                return "I finished it weeks ago! I can help you if you'd like.";
            case "mention library":
                increaseFriendship(15);
                return "The library is the best place! Did you know Hogwarts has over 10,000 books?";
            case "break rules":
                decreaseFriendship(25);
                return "That's against school rules! We could get expelled!";
            case "ask about spells":
                increaseFriendship(12);
                return "I've been practicing! Wingardium Leviosa is particularly useful.";
            case "talk about books":
                increaseFriendship(18);
                return "Oh! Have you read 'Hogwarts: A History'? It's fascinating!";
            case "ask about time turner":
                decreaseFriendship(30);
                return "I have no idea what you're talking about!";
            case "study together":
                increaseFriendship(15);
                return "Excellent! We should start with Potions, it's the most difficult.";
            case "ask about family":
                increaseFriendship(5);
                return "My parents are dentists. They don't really understand magic, but they try.";
            default:
                return "We should be studying, you know.";
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
    
    public void suggestResearch() {
        System.out.println("Hermione: \"We should look this up in 'Hogwarts: A History'!\"");
    }
    
    public void offerStudyHelp() {
        System.out.println("Hermione: \"If you need help with any subjects, just ask!\"");
    }
}