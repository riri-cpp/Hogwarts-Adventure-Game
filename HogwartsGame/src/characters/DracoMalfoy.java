// src/characters/DracoMalfoy.java
package characters;

public class DracoMalfoy extends Student {
    
    public DracoMalfoy() {
        super("Draco Malfoy", "Slytherin", 2, "Potions");
    }
    
    @Override
    public void introduce() {
        System.out.println("Draco: \"My father will hear about this... I'm Draco Malfoy.\"");
    }
    
    @Override
    public String reactToAction(String action) {
        switch (action) {
            case "compliment slytherin":
                increaseFriendship(15);
                return "Finally, someone who recognizes quality. Slytherin is the superior house.";
            case "ask about father":
                increaseFriendship(10);
                return "My father is a very important wizard. He knows the Minister himself.";
            case "insult gryffindor":
                increaseFriendship(12);
                return "Gryffindors? All brawn and no brains. Potter especially.";
            case "be friendly":
                decreaseFriendship(5);
                return "I don't need new friends. I have Crabbe and Goyle.";
            case "ask about quidditch":
                increaseFriendship(8);
                return "Father is buying the whole team new brooms. We'll crush Gryffindor.";
            case "help with potions":
                increaseFriendship(18);
                return "I suppose I could show you a thing or two. I'm excellent at Potions.";
            default:
                return "What do you want? I'm busy.";
        }
    }
    
    public void boastAboutFather() {
        System.out.println("Draco: \"My father says Hogwarts hasn't been the same since Dumbledore took over.\"");
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