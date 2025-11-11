// src/characters/Student.java
package characters;

public class Student extends Character {
    private int year;
    private String favoriteSubject;
    
    public Student(String name, String house, int year, String favoriteSubject) {
        super(name, house);
        this.year = year;
        this.favoriteSubject = favoriteSubject;
    }
    
    @Override
    public void introduce() {
        System.out.println(name + ": \"I'm in " + house + ", year " + year + ". I love " + favoriteSubject + "!\"");
    }
    
// In Student.java - remove the friendship changes from reactToAction
    @Override
    public String reactToAction(String action) {
        switch (action) {
            case "help": 
                return "Thanks for helping me!";
            case "ask about house":
                return house + " is the best house! We're known for our " + getHouseTrait() + ".";
            case "ask about subject":
                return favoriteSubject + " is amazing! The professor really knows their stuff.";
            case "ask about hobbies":
                return "I like exploring the castle and practicing magic in my free time.";
            case "hello":
                return "Hello! Nice to meet you!";
            default:
                return "Nice to meet you!";
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
    
    private String getHouseTrait() {
        switch (house) {
            case "Gryffindor": return "bravery";
            case "Slytherin": return "ambition";
            case "Ravenclaw": return "wisdom";
            case "Hufflepuff": return "loyalty";
            default: return "magic";
        }
    }
}