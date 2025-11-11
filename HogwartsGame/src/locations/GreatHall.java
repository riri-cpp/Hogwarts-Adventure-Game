package locations;

import java.util.Arrays;
import java.util.List;

public class GreatHall extends Location{
    
    public GreatHall(){
        super("Great Hall", "The enormous main hall with four long house tables and enchanted ceiling");
    }

    @Override
    public void describe(){
        System.out.println("You're in the Great Hall. " + description);
        System.out.println("The ceiling shows a cloudy sky. Students are eating and chatting.");
        if (!charactersPresent.isEmpty()) {
            System.out.println("You see: " + charactersPresent.get(0).getName());
        }
    }

    @Override
    public List<String> getAvailableActions() {
        return Arrays.asList(
            "Talk to students",
            "Sit at your house table", 
            "Look at enchanted ceiling",
            "Move to another location"
        );
    }
}