package locations;

import characters.Character;
import java.util.List;
import java.util.ArrayList;

public abstract class Location {
    protected String name;
    protected String description;
    protected List<Character> charactersPresent;
    
    public Location(String name, String description){
        this.name = name;
        this.description = description;
        this.charactersPresent = new ArrayList<>();
    }

    public abstract void describe();
    public abstract List<String> getAvailableActions();

    public void addCharacter(Character character){
        charactersPresent.add(character);
    }

    public void removeCharacter(Character character){
        charactersPresent.remove(character);
    }

    public List<Character> getCharactersPresent(){
        return new ArrayList<>(charactersPresent);
    }

    public String getName(){
        return name;
    }
}
