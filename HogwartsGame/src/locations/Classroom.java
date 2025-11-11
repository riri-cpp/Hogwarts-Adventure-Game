package locations;

import java.util.Arrays;
import java.util.List;

public class Classroom extends Location {
    private String subject;
    
    public Classroom(String subject) {
        super(subject + " Classroom", "A classroom for " + subject + " lessons");
        this.subject = subject;
    }
    
    @Override
    public void describe() {
        System.out.println("You're in the " + subject + " classroom.");
        System.out.println("Desks are arranged in rows. There's a teacher's desk at the front.");
    }
    
    @Override
    public List<String> getAvailableActions() {
        return Arrays.asList(
            "Practice " + subject + " spells",
            "Review notes",
            "Ask questions",
            "Return to corridor"
        );
    }
}
