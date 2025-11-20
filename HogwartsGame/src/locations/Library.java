package locations;

import java.util.Arrays;
import java.util.List;

public class Library extends Location{
    
    public Library(){
        super("Library", "A vast room filled with thousands of magical books");
    }

    @Override
    public void describe(){
        System.out.println("You're in the Hogwarts Library.");
        System.out.println("Towering bookshelves reach the ceiling. Madam Pince watches carefully.");
    }

    @Override
    public List<String> getAvailableActions() {
        return Arrays.asList(
            "Study magical texts",
            "Research Hogwarts history", 
            "Look for restricted section",
            "Return to corridor"
        );
    }
}
