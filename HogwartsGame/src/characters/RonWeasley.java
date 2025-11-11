// src/characters/RonWeasley.java
package characters;

public class RonWeasley extends Student {
    
    public RonWeasley() {
        super("Ron Weasley", "Gryffindor", 2, "Chess");
    }
    
    @Override
    public void introduce() {
        System.out.println("Ron: \"Blimey! Another first year? I'm Ron, by the way.\"");
    }
    
    @Override
    public String reactToAction(String action) {
        switch (action) {
            case "ask about family":
                increaseFriendship(10);
                return "I've got five brothers and one sister. It's a bit mad, but they're alright.";
            case "talk about food":
                increaseFriendship(15);
                return "The food here is brilliant! Much better than my mum's cooking, don't tell her I said that.";
            case "ask about chess":
                increaseFriendship(20);
                return "I'm brilliant at chess! I could teach you if you want.";
            case "mention harry":
                increaseFriendship(12);
                return "Harry's my best mate! Got into loads of trouble together last year.";
            case "insult quidditch":
                decreaseFriendship(25);
                return "You take that back! I may not be great but I love Quidditch!";
            case "ask about scar":
                decreaseFriendship(10);
                return "That's a bit personal, don't you think?";
            default:
                return "I should get going...";
        }
    }
    
    public void talkAboutFood() {
        System.out.println("Ron: \"I'm starving! When's dinner?\"");
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