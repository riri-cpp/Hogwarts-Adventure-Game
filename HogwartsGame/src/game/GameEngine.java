// src/game/GameEngine.java
package game;

import characters.Character;
import characters.HarryPotter;
import characters.HermioneGranger;
import characters.RonWeasley;
import characters.DracoMalfoy;
import characters.LunaLovegood;
import characters.Student;
import locations.Location;
import locations.GreatHall;
import locations.Classroom;
import locations.Library;
import spells.FiniteIncantatem;
import spells.Lumos;
import spells.WingardiumLeviosa;
import java.util.*;

public class GameEngine {
    private Player player;
    private Scanner scanner;
    private List<Character> allCharacters;
    private List<Location> allLocations;
    private Location currentLocation;
    private GameState gameState;
    
    public GameEngine() {
        this.scanner = new Scanner(System.in);
        this.gameState = new GameState();
        initializeGameWorld();
    }
    
    // Clear terminal method
    private void clearTerminal() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Fallback: print empty lines
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
    
    private void waitForEnter() {
        System.out.println("\n[Press Enter to continue...]");
        scanner.nextLine();
    }
    
    private String getInputWithClear(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        clearTerminal();
        return input;
    }
    
    private void initializeGameWorld() {
        // Create extended character list
        allCharacters = new ArrayList<>();
        allCharacters.add(new HarryPotter());
        allCharacters.add(new HermioneGranger());
        allCharacters.add(new RonWeasley());
        allCharacters.add(new DracoMalfoy());
        allCharacters.add(new LunaLovegood());
        allCharacters.add(new Student("Neville Longbottom", "Gryffindor", 2, "Herbology"));
        allCharacters.add(new Student("Ginny Weasley", "Gryffindor", 1, "Charms"));
        allCharacters.add(new Student("Padma Patil", "Ravenclaw", 2, "Arithmancy"));
        allCharacters.add(new Student("Blaise Zabini", "Slytherin", 2, "Dark Arts"));
        allCharacters.add(new Student("Hannah Abbott", "Hufflepuff", 2, "Herbology"));

        // Initialize relationships
        for (Character character : allCharacters) {
            gameState.setRelationship(character.getName(), 50);
        }

        // Create locations
        allLocations = new ArrayList<>();
        allLocations.add(new GreatHall());
        allLocations.add(new Classroom("Charms"));
        allLocations.add(new Classroom("Potions"));
        allLocations.add(new Classroom("Transfiguration"));
        allLocations.add(new Classroom("Defense Against Dark Arts"));
        allLocations.add(new Library());
        
        // Add Forbidden Forest location
        allLocations.add(new Location("Forbidden Forest Edge", "The dark, mysterious forest bordering Hogwarts") {
            @Override
            public void describe() {
                System.out.println("You stand at the edge of the Forbidden Forest.");
                System.out.println("Ancient trees loom overhead. You hear strange rustling from within.");
            }
            
            @Override
            public List<String> getAvailableActions() {
                return Arrays.asList("Explore perimeter", "Look for magical creatures", "Collect herbs", "Return quickly");
            }
        });
        
        // Add Quidditch Pitch location
        allLocations.add(new Location("Quidditch Pitch", "The massive stadium for Quidditch matches") {
            @Override
            public void describe() {
                System.out.println("You're at the Quidditch Pitch. Three golden hoops stand at each end.");
                System.out.println("The stands are empty but you can imagine the roar of the crowd.");
            }
            
            @Override
            public List<String> getAvailableActions() {
                return Arrays.asList("Practice flying", "Watch team practice", "Explore stands", "Return to castle");
            }
        });

        currentLocation = allLocations.get(0);
    }
    
    public void startGame() {
        clearTerminal();
        showTitle();
        createPlayer();
        sortingCeremony();
        mainGameLoop();
    }
    
    private void showTitle() {
        System.out.println("               ██╗░░██╗░█████╗░░██████╗░░██╗░░░░░░░██╗░█████╗░██████╗░████████╗░██████╗");
        System.out.println("               ██║░░██║██╔══██╗██╔════╝░░██║░░██╗░░██║██╔══██╗██╔══██╗╚══██╔══╝██╔════╝");
        System.out.println("               ███████║██║░░██║██║░░██╗░░╚██╗████╗██╔╝███████║██████╔╝░░░██║░░░╚█████╗░");
        System.out.println("               ██╔══██║██║░░██║██║░░╚██╗░░████╔═████║░██╔══██║██╔══██╗░░░██║░░░░╚═══██╗");
        System.out.println("               ██║░░██║╚█████╔╝╚██████╔╝░░╚██╔╝░╚██╔╝░██║░░██║██║░░██║░░░██║░░░██████╔╝");
        System.out.println("               ╚═╝░░╚═╝░╚════╝░░╚═════╝░░░░╚═╝░░░╚═╝░░╚═╝░░╚═╝╚═╝░░╚═╝░░░╚═╝░░░╚═════╝░");
        System.out.println("");
        System.out.println("                ░█████╗░██████╗░██╗░░░██╗███████╗███╗░░██╗████████╗██╗░░░██╗██████╗░███████╗");
        System.out.println("                ██╔══██╗██╔══██╗██║░░░██║██╔════╝████╗░██║╚══██╔══╝██║░░░██║██╔══██╗██╔════╝");
        System.out.println("                ███████║██║░░██║╚██╗░██╔╝█████╗░░██╔██╗██║░░░██║░░░██║░░░██║██████╔╝█████╗░░");
        System.out.println("                ██╔══██║██║░░██║░╚████╔╝░██╔══╝░░██║╚████║░░░██║░░░██║░░░██║██╔══██╗██╔══╝░░");
        System.out.println("                ██║░░██║██████╔╝░░╚██╔╝░░███████╗██║░╚███║░░░██║░░░╚██████╔╝██║░░██║███████╗");
        System.out.println("                ╚═╝░░╚═╝╚═════╝░░░░╚═╝░░░╚══════╝╚═╝░░╚══╝░░░╚═╝░░░░╚═════╝░╚═╝░░╚═╝╚══════╝");
        System.out.println("=============================================================================================================");
        System.out.println("                                        THE CHAMBER'S LEGACY");
        System.out.println("\nPress Enter to begin...");
        scanner.nextLine();
        clearTerminal();
    }
    
    private void createPlayer() {
        while(true){
            System.out.println("     ╔═════════════════════════════════════════════════════════════╗");
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                                                                       ║");
            System.out.println("║                                                                       ║");
            System.out.println("║                                                                       ║");
            System.out.println("║                                                                       ║");
            System.out.println("║      FROM: PROFESSOR ALBUS DUMBLEDORE           TO: THE CHOSEN ONE    ║");
            System.out.println("║      Headmaster, Hogwarts School of Witchcraft and Wizardry           ║");
            System.out.println("║                                                                       ║");
            System.out.println("║                                                                       ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
            System.out.println("A Hogwarts letter arrives by owl...");
            String name = getInputWithClear("Enter your name: ");
            player = new Player(name);

            if(name != null && !name.trim().isEmpty()){
                System.out.println("Welcome, " + player.getName() + "!");
                waitForEnter();
                clearTerminal();
                break;
            }
            else{
                System.out.println("Please enter your name!");
                waitForEnter();
                clearTerminal();
            }
        }
    }
    
    private void sortingCeremony() {
        System.out.println("  __,                           ,___                               ");
        System.out.println(" (           _/_o              /   /                               ");
        System.out.println("  `.  __ _   / ,  _ _   _,    /    _  _   _  _ _ _   __ _ _   __  ,");
        System.out.println("(___)(_)/ (_(__(_/ / /_(_)_  (___/(/_/ (_(/_/ / / /_(_)/ / /_/ (_/_");
        System.out.println("                        /|                                      /  ");
        System.out.println("                       (/                                      '   ");
    
        System.out.println("The Sorting Hat is placed on your head...");
        
        boolean sorted = false;
        while (!sorted) {
            System.out.println("\nChoose your house:");
            System.out.println("1. Gryffindor (Bravery)");
            System.out.println("2. Slytherin (Ambition)");
            System.out.println("3. Ravenclaw (Wisdom)");
            System.out.println("4. Hufflepuff (Loyalty)");

            System.out.println();
            
            String input = getInputWithClear("Choice (1-4): ");
            
            switch (input) {
                case "1": 
                    player.setHouse("Gryffindor");
                    player.increaseMagic(10);
                    sorted = true;
                    break;
                case "2":
                    player.setHouse("Slytherin"); 
                    player.increaseKnowledge(10);
                    sorted = true;
                    break;
                case "3":
                    player.setHouse("Ravenclaw");
                    player.increaseKnowledge(15);
                    sorted = true;
                    break;
                case "4":
                    player.setHouse("Hufflepuff");
                    sorted = true;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    waitForEnter();
                    clearTerminal();
            }
        }
        
        gameState.setStoryFlag("sorted", true);
        System.out.println("You take your seat at the " + player.getHouse() + " table.");
        waitForEnter();
        clearTerminal();
    }
    
    private void mainGameLoop() {
        System.out.println("  __     __)          ____  ___)                            _                ______                     / ");
        System.out.println(" (, )   /            (, /   /                           ___/__) ,  /)       (, /    )       ,          /  ");
        System.out.println("   /   / ___  __       /---/ ___  _   _ _  __ _/_ _    (, /       //  _       /---(   _ _    __  _    /   ");
        System.out.println("  (___/_(_(_(/ (_   ) /   (_(_(_/_(_(/ (_(/ (_(__/_)_    /    _(_/(__(/_   ) / ____)_(/(_/_(_/ (/_)_ o    ");
        System.out.println(" )   /             (_/       .-/                        (_____  /)        (_/ (       .-/                 ");
        System.out.println("(__ /                       (_/                                (/                    (_/                  ");
        waitForEnter();
        clearTerminal();
        
        int totalDays = 5;
        int actionsPerDay = 10;
        
        for (int day = 1; day <= totalDays; day++) {
            System.out.println("══════════════════════════════════════════════════════════════════════════════");
            System.out.println("                                DAY " + day + " MORNING");
            System.out.println("══════════════════════════════════════════════════════════════════════════════");
            System.out.println("You wake up refreshed and ready for a new day!");
            waitForEnter();
            clearTerminal();
            
            // CHECK FOR START-OF-DAY QUESTS (like Quest 2)
            checkAndTriggerQuests(day);
            
            // Daily actions loop
            for (int dailyAction = 1; dailyAction <= actionsPerDay; dailyAction++) {
                System.out.println("═══════════════════════════════════════════════════════════════════════════════════════════════════");
                System.out.println("                                      DAY " + day + " | Action " + dailyAction + "/" + actionsPerDay);
                System.out.println("═══════════════════════════════════════════════════════════════════════════════════════════════════");
                
                showLocationScreen();
                String choice = getInputWithClear("\nChoose: ");
                processAction(choice);
                gameState.incrementActions();
                
            }
            
            // End of day summary
            System.out.println("══════════════════════════════════════════════════════════════════════════════");
            System.out.println("                                END OF DAY " + day);
            System.out.println("══════════════════════════════════════════════════════════════════════════════");
            System.out.println("You return to your dormitory and fall asleep...");
            player.increaseKnowledge(5);
            player.increaseMagic(3);
            System.out.println("\nYou feel a bit wiser and more magical after today's experiences!");
            waitForEnter();
            clearTerminal();
            
            // CHECK FOR END-OF-DAY QUESTS (like Quest 1)
            checkAndTriggerQuests(day);
        }
        
        gameState.setGameCompleted(true);
    }

    private void checkAndTriggerQuests(int day) {
        // Quest 1 triggers at the END of Day 2
        if (day == 2 && !gameState.isQuest1Completed() && gameState.getActionsTaken() == 20) {
            triggerQuest1();
        }
        
        // Quest 2 triggers at the START of Day 3  
        else if (day == 3 && !gameState.isQuest2Completed()) {
            triggerQuest2();
        }

        // Quest 3 triggers at the END of Day 3 and Quest 2 MUST BE COMPLETED
        else if (day == 3 && gameState.isQuest2Completed() && gameState.getActionsTaken() == 30) {
            triggerQuest3();
        }

        // Quest 4 formally starts at the END of Day 4
        else if (day == 4 && gameState.isQuest3Completed() && gameState.getActionsTaken() == 40) {
            triggerQuest4Main();
        }

        // Quest 4's premise is introduced at the START of Day 4
        else if (day == 4 && gameState.isQuest3Completed()) {
            triggerQuest4Intro();
        }

        // Quest 5 starts at the END of Day 5
        else if (day == 5 && gameState.isQuest4Completed() && gameState.getActionsTaken() == 50) {
            triggerQuest5();
        }

    }
    private List<Character> generateRandomEncounter() {
    List<Character> encounter = new ArrayList<>();
    Random random = new Random();
    
    // Base chance of encountering someone (80% chance)
    if (random.nextDouble() > 0.2) {
        // Determine how many characters (1-3)
        int characterCount = 1 + random.nextInt(3);
        
        // Filter characters that make sense for this location
        List<Character> availableCharacters = getCharactersForLocation(currentLocation.getName());

        if(gameState.isQuest2Completed()){
            availableCharacters.removeIf(character -> character.getName().equals("Luna Lovegood"));
        }
        
        // Shuffle and pick random characters
        Collections.shuffle(availableCharacters);
        for (int i = 0; i < Math.min(characterCount, availableCharacters.size()); i++) {
            encounter.add(availableCharacters.get(i));
        }
    }
    
    return encounter;
    }

    private List<Character> getCharactersForLocation(String locationName) {
        List<Character> suitableCharacters = new ArrayList<>();
        
        switch (locationName) {
            case "Great Hall":
                // Anyone can be in the Great Hall
                suitableCharacters.addAll(allCharacters);
                break;
            case "Charms Classroom":
                // Studious characters and those who like Charms
                suitableCharacters.add(findCharacter("Hermione Granger"));
                suitableCharacters.add(findCharacter("Ginny Weasley"));
                suitableCharacters.add(findCharacter("Luna Lovegood"));
                suitableCharacters.add(findCharacter("Padma Patil"));
                break;
            case "Potions Classroom":
                // Slytherins and potions enthusiasts
                suitableCharacters.add(findCharacter("Draco Malfoy"));
                suitableCharacters.add(findCharacter("Hermione Granger"));
                suitableCharacters.add(findCharacter("Blaise Zabini"));
                break;
            case "Library":
                // Studious characters
                suitableCharacters.add(findCharacter("Hermione Granger"));
                suitableCharacters.add(findCharacter("Luna Lovegood"));
                suitableCharacters.add(findCharacter("Padma Patil"));
                break;
            case "Quidditch Pitch":
                // Quidditch fans and players
                suitableCharacters.add(findCharacter("Harry Potter"));
                suitableCharacters.add(findCharacter("Ron Weasley"));
                suitableCharacters.add(findCharacter("Ginny Weasley"));
                suitableCharacters.add(findCharacter("Draco Malfoy"));
                break;
            case "Forbidden Forest Edge":
                // Adventurous characters and creature lovers
                suitableCharacters.add(findCharacter("Harry Potter"));
                suitableCharacters.add(findCharacter("Ron Weasley"));
                suitableCharacters.add(findCharacter("Luna Lovegood"));
                suitableCharacters.add(findCharacter("Hannah Abbott"));
                break;
            default:
                // For other locations, include a mix
                suitableCharacters.addAll(allCharacters);
        }
        
        // Remove nulls and return
        suitableCharacters.removeIf(Objects::isNull);
        return suitableCharacters;
    }

    private Character findCharacter(String name) {
        for (Character character : allCharacters) {
            if (character.getName().equals(name)) {
                return character;
            }
        }
        return null;
    }

    private void giveLocationSpecificIntroduction(Character character) {
        String location = currentLocation.getName();
        
        if (location.equals("Great Hall")) {
            System.out.println("You find " + character.getName() + " sitting at the " + 
                            (character.getHouse().equals(player.getHouse()) ? "your" : character.getHouse()) + 
                            " table in the Great Hall.");
        } else if (location.equals("Library")) {
            System.out.println(character.getName() + " is studying at a table in the library, surrounded by books.");
        } else if (location.equals("Quidditch Pitch")) {
            System.out.println(character.getName() + " is watching the Quidditch practice from the stands.");
        } else if (location.equals("Forbidden Forest Edge")) {
            System.out.println("You spot " + character.getName() + " cautiously peering into the Forbidden Forest.");
        } else if (location.contains("Classroom")) {
            System.out.println(character.getName() + " is in the " + currentLocation.getName() + " before class starts.");
        }
        
        character.introduce();
    }


    private void triggerLocationSpecialInteraction(Character character) {
        String location = currentLocation.getName();
        
        if (location.equals("Library") && character instanceof HermioneGranger) {
            ((HermioneGranger) character).suggestResearch();
        } else if (location.equals("Quidditch Pitch") && character instanceof HarryPotter) {
            System.out.println("Harry points to the Gryffindor seeker practicing: \"That's going to be me next match!\"");
        } else if (location.equals("Forbidden Forest Edge") && character instanceof LunaLovegood) {
            ((LunaLovegood) character).mentionWrackspurts();
        } else if (location.equals("Great Hall") && character instanceof RonWeasley) {
            ((RonWeasley) character).talkAboutFood();
        }
    }

    
    private void showLocationScreen() {
        currentLocation.describe();
        System.out.println("\nWhat would you like to do?");
        System.out.println("1. Talk to students");
        System.out.println("2. Explore this area");
        System.out.println("3. Move to another location");
        System.out.println("4. Check your status");
        System.out.println("5. Practice magic");
        System.out.println("6. View game progress");
        System.out.println("7. Cast spells"); // NEW OPTION
    }
    
    private void processAction(String choice) {
        switch (choice) {
            case "1":
                talkToCharacters();
                break;
            case "2":
                explore();
                break;
            case "3":
                moveToNewLocation();
                break;
            case "4":
                player.displayStatus();
                waitForEnter();
                break;
            case "5":
                practiceMagic();
                break;
            case "6":
                gameState.displayGameState();
                waitForEnter();
                break;
            case "7": // NEW: Cast spells
                castSpells();
                break;
            default:
                System.out.println("You spend some time thinking...");
                waitForEnter();
        }
        clearTerminal();
    }
    
    private void talkToCharacters() {
        List<Character> charactersHere = generateRandomEncounter();
        
        if (charactersHere.isEmpty()) {
            System.out.println("There's no one around to talk to right now.");
            System.out.println("The " + currentLocation.getName() + " is quiet and empty.");
            waitForEnter();
            return;
        }
        
        System.out.println("You see some students here:");
        for (int i = 0; i < charactersHere.size(); i++) {
            System.out.println((i + 1) + ". " + charactersHere.get(i).getName());
        }
        
        String input = getInputWithClear("Who would you like to talk to? (Choose or 0 to cancel): ");
        
        if (input.equals("0")) {
            System.out.println("You decide not to talk to anyone right now.");
            waitForEnter();
            return;
        }
        
        try {
            int choice = Integer.parseInt(input) - 1;
            if (choice >= 0 && choice < charactersHere.size()) {
                Character character = charactersHere.get(choice);
                clearTerminal();
                
                // Location-specific introduction
                giveLocationSpecificIntroduction(character);
                
                // Track first meetings
                if (character instanceof HarryPotter && !gameState.getStoryFlag("met_harry")) {
                    gameState.setStoryFlag("met_harry", true);
                    System.out.println("\n*** You've met Harry Potter! ***");
                }
                if (character instanceof HermioneGranger && !gameState.getStoryFlag("met_hermione")) {
                    gameState.setStoryFlag("met_hermione", true);
                    System.out.println("\n*** You've met Hermione Granger! ***");
                }
                if (character instanceof RonWeasley && !gameState.getStoryFlag("met_ron")) {
                    gameState.setStoryFlag("met_ron", true);
                    System.out.println("\n*** You've met Ron Weasley! ***");
                }
                
                System.out.println("\nWhat would you like to say?");
                
                // Special dialogue options for main characters
                if (character instanceof HarryPotter) {
                    System.out.println("1. Offer help with the mystery");
                    System.out.println("2. Ask about the Chamber of Secrets");
                    System.out.println("3. Ask about his scar");
                    System.out.println("4. Insult Slytherin house");
                    System.out.println("5. Ask about Quidditch");
                    System.out.println("6. Ask about his family");      // NEW - risky!
                    System.out.println("7. Offer friendship");
                    System.out.println("8. Ask about Voldemort");   // NEW - risky!
                } else if (character instanceof HermioneGranger) {
                    System.out.println("1. Ask about homework");
                    System.out.println("2. Talk about the library");
                    System.out.println("3. Invite to break the rules");
                    System.out.println("4. Ask about spells");
                    System.out.println("5. Discuss books");
                    System.out.println("6. Ask about time turner");
                    System.out.println("7. Suggest to study together");
                    System.out.println("8. Ask about family");
                } else if (character instanceof RonWeasley) {
                    System.out.println("1. Ask about family");
                    System.out.println("2. Talk about food");
                    System.out.println("3. Ask about chess");
                    System.out.println("4. Mention Harry");
                    System.out.println("5. Insult Quidditch");
                    System.out.println("6. Ask about his scar");
                } else if (character instanceof LunaLovegood) {
                    System.out.println("1. Ask about nargles");
                    System.out.println("2. Talk about creatures");
                    System.out.println("3. Ask about Ravenclaw");
                    System.out.println("4. Insult intelligence");
                    System.out.println("5. Ask about glasses");
                    System.out.println("6. Offer friendship");
                } else if (character instanceof DracoMalfoy) {
                    System.out.println("1. Compliment Slytherin");
                    System.out.println("2. Ask about his father");
                    System.out.println("3. Insult Gryffindor");
                    System.out.println("4. Be friendly");
                    System.out.println("5. Ask about Quidditch");
                    System.out.println("6. Help with potions");
                }
                else {
                    // Generic student options
                    System.out.println("1. Offer help");
                    System.out.println("2. Ask about their house"); 
                    System.out.println("3. Ask about favorite subject");
                    System.out.println("4. Ask about hobbies");
                    System.out.println("5. Just say hello");
                }
                
                String actionChoice = getInputWithClear("\nChoose: ");
                String action = "";
                
                // Handle character-specific options with friendship changes
                if (character instanceof HarryPotter) {
                    switch (actionChoice) {
                        case "1": 
                            action = "help with mystery"; 
                            gameState.modifyRelationship(character.getName(), 25);
                            break;
                        case "2": 
                            action = "mention chamber"; 
                            gameState.modifyRelationship(character.getName(), 10);
                            gameState.setStoryFlag("heard_voices", true);
                            break;
                        case "3": 
                            action = "ask about scar"; 
                            gameState.modifyRelationship(character.getName(), 5);
                            break;
                        case "4": 
                            action = "insult slytherin"; 
                            gameState.modifyRelationship(character.getName(), -15);
                            break;
                        case "5": 
                            action = "ask about quidditch"; 
                            gameState.modifyRelationship(character.getName(), 8);
                            break;
                        case "6": 
                            action = "talk about family";  // NEGATIVE!
                            gameState.modifyRelationship(character.getName(), -10);
                            break;
                        case "7": 
                            action = "offer friendship"; 
                            gameState.modifyRelationship(character.getName(), 20);
                            break;
                        case "8":
                            action = "ask about voldemort";
                            gameState.modifyRelationship(character.getName(), -5);
                            break;
                        default: 
                            action = "hello"; 
                    }
                } else if (character instanceof HermioneGranger) {
                    switch (actionChoice) {
                        case "1": 
                            action = "ask about homework";
                            gameState.modifyRelationship(character.getName(), 20);
                            break;
                        case "2": 
                            action = "mention library";
                            gameState.modifyRelationship(character.getName(), 15);
                            break;
                        case "3": 
                            action = "break rules";
                            gameState.modifyRelationship(character.getName(), -25); 
                            break;
                        case "4": 
                            action = "ask about spells";
                            gameState.modifyRelationship(character.getName(), 12);
                            break;
                        case "5": 
                            action = "talk about books";
                            gameState.modifyRelationship(character.getName(), 18);  
                            break;
                        case "6":
                            action = "ask about time turner";
                            gameState.modifyRelationship(character.getName(), -30);
                            break;
                        case "7":
                            action = "study together";
                            gameState.modifyRelationship(character.getName(), 15);
                            break;
                        case "8":
                            action = "ask about family";
                            gameState.modifyRelationship(character.getName(), 5);
                            break;
                        default: 
                            action = "hello"; 
                    }
                } else if (character instanceof RonWeasley) {
                    switch (actionChoice) {
                        case "1": 
                            action = "ask about family";
                            gameState.modifyRelationship(character.getName(), 10); 
                            break;
                        case "2": 
                            action = "talk about food";
                            gameState.modifyRelationship(character.getName(), 15);
                            break;
                        case "3": 
                            action = "ask about chess";
                            gameState.modifyRelationship(character.getName(), 20); 
                            break;
                        case "4": 
                            action = "mention harry";
                            gameState.modifyRelationship(character.getName(), 12); 
                            break;
                        case "5": 
                            action = "insult quidditch";
                            gameState.modifyRelationship(character.getName(), -25); 
                            break;
                        case "6":
                            action = "ask about scar";
                            gameState.modifyRelationship(character.getName(), -10);
                            break;
                        default: 
                            action = "hello"; 
                    }
                } else if (character instanceof LunaLovegood) {
                    switch (actionChoice) {
                        case "1":
                            action = "ask about nargles";
                            gameState.modifyRelationship(character.getName(), 20);
                            break;
                        case "2":
                            action = "talk about creatures";
                            gameState.modifyRelationship(character.getName(), 15);
                            break;
                        case "3":
                            action = "ask about ravenclaw";
                            gameState.modifyRelationship(character.getName(), 10);
                            break;
                        case "4":
                            action = "be logical";
                            gameState.modifyRelationship(character.getName(), -8);
                            break;
                        case "5":
                            action = "ask about glasses";
                            gameState.modifyRelationship(character.getName(), 5);
                            break;
                        case "6":
                            action = "offer friendship";
                            gameState.modifyRelationship(character.getName(), 25);
                            break;
                        default:
                            action = "hello";
                    }
                } else if (character instanceof DracoMalfoy){
                    switch (actionChoice) {
                        case "1":
                            action = "compliment slytherin";
                            gameState.modifyRelationship(character.getName(), 15);
                            break;
                        case "2":
                            action = "ask about father";
                            gameState.modifyRelationship(character.getName(), 10);
                            break;
                        case "3":
                            action = "insult gryffindor";
                            gameState.modifyRelationship(character.getName(), 12);
                            break;
                        case "4":
                            action = "be friendly";
                            gameState.modifyRelationship(character.getName(), -5);
                            break;
                        case "5":
                            action = "ask about quidditch";
                            gameState.modifyRelationship(character.getName(), 8);
                            break;
                        case "6":
                            action = "help with potions";
                            gameState.modifyRelationship(character.getName(), 18);
                            break;
                        default:
                            action = "hello";
                    }
                }
                else {
                    // Generic student options
                    switch (actionChoice) {
                        case "1": 
                            action = "help"; 
                            break;
                        case "2": 
                            action = "ask about house"; 
                            break;
                        case "3": 
                            action = "ask about subject"; 
                            break;
                        case "4": 
                            action = "ask about hobbies"; 
                            break;
                        case "5": 
                            action = "hello"; 
                            break;
                        default: 
                            action = "hello"; 
                    }
                }
                
                
                String response = character.reactToAction(action);
                System.out.println(character.getName() + ": \"" + response + "\"");
                
                // Show friendship change
                
                // Location-specific special interactions
                triggerLocationSpecialInteraction(character);
                
                // Character-specific special interactions
                if (character instanceof HarryPotter && action.equals("mention chamber")) {
                    ((HarryPotter) character).talkAboutChamber();
                }
                if (character instanceof HermioneGranger && action.equals("talk about books")) {
                    ((HermioneGranger) character).suggestResearch();
                }
                
                // Check friendship milestones
                int currentFriendship = gameState.getRelationship(character.getName());
                if (currentFriendship >= 70) {
                    System.out.println("\n*** " + character.getName() + " now considers you a close friend! ***\n");
                } else if (currentFriendship > 50) {
                    System.out.println("\n*** " + character.getName() + " is starting to like you! ***\n");
                } else if (currentFriendship < 50) {
                    System.out.println("\n*** " + character.getName() + " is starting to hate you! ***\n");
                }
                
                waitForEnter();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice!");
            waitForEnter();
        }
    }
    
    private void explore() {
        System.out.println("You explore the area...");
        
        String[] discoveries = {
            "You find a hidden passage behind a tapestry!",
            "A ghost tells you about Hogwarts history!",
            "You discover a secret room full of books!",
            "You find a magical artifact glowing with power!",
            "You hear mysterious voices from the walls..."
        };
        
        Random random = new Random();
        String discovery = discoveries[random.nextInt(discoveries.length)];
        System.out.println(discovery);
        
        player.increaseKnowledge(10);
        
        // Update game state based on discovery
        if (discovery.contains("hidden passage")) {
            gameState.setStoryFlag("found_passage", true);
        }
        if (discovery.contains("mysterious voices")) {
            gameState.setStoryFlag("heard_voices", true);
        }
        if (discovery.contains("magical artifact")) {
            player.increaseMagic(15);
            System.out.println("You feel your magical power growing!");
        }
        
        waitForEnter();
    }
    
    private void practiceMagic() {
        System.out.println("You practice your spells...");
        player.increaseMagic(15);
        System.out.println("Your magical ability improves!");
        
        // Learn spells when reaching certain magic levels
        if (player.getMagicPower() >= 40 && !player.knowsSpell("Lumos")) {
            player.learnSpell(new Lumos());
        }
        
        if (player.getMagicPower() >= 70 && !player.knowsSpell("Wingardium Leviosa")) {
            player.learnSpell(new WingardiumLeviosa());
        }
        
        if (player.canCastComplexSpell() && !gameState.getStoryFlag("mastered_magic")) {
            gameState.setStoryFlag("mastered_magic", true);
            System.out.println("*** You can now cast complex spells! ***");
        }
        
        waitForEnter();
    }
    
    private void moveToNewLocation() {
        System.out.println("Where would you like to go?");
        for (int i = 0; i < allLocations.size(); i++) {
            System.out.println((i + 1) + ". " + allLocations.get(i).getName());
        }
        
        String input = getInputWithClear("\nChoose: ");
        
        try {
            int choice = Integer.parseInt(input) - 1;
            if (choice >= 0 && choice < allLocations.size()) {
                Location newLocation = allLocations.get(choice);
                
                // CHECK IF PLAYER IS VISITING LIBRARY DURING QUEST 2
                if (newLocation.getName().equals("Library") && gameState.isQuest2InvestigationStarted() && !gameState.isQuest2Completed()) {
                    
                    // This is the first library visit during the investigation
                    if (!gameState.isQuest2LibraryVisited()) {
                        investigateLibraryForLuna();
                        completeQuest2();
                    }
                }
                
                currentLocation = newLocation;
                System.out.println("You go to the " + currentLocation.getName() + ".");
                waitForEnter();
            }
        } catch (NumberFormatException e) {
            System.out.println("You decide to stay where you are.");
            waitForEnter();
        }
    }

    private void castSpells() {
        if (!player.knowsSpell("Lumos") && !player.knowsSpell("Wingardium Leviosa") && !player.knowsSpell("Finite Incantatem")) {
            System.out.println("You don't know any spells yet!");
            System.out.println("Practice magic to learn spells!");
            waitForEnter();
            return;
        }
        
        System.out.println("Which spell would you like to cast?");
        int option = 1;
        
        if (player.knowsSpell("Lumos")) {
            System.out.println(option + ". Lumos - Creates light");
            option++;
        }
        
        if (player.knowsSpell("Wingardium Leviosa")) {
            System.out.println(option + ". Wingardium Leviosa - Levitates objects");
            option++;
        }

        if (player.knowsSpell("Finite Incantatem")) {
            System.out.println(option + ". Finite Incantatem - Does nothing at the moment");
            option++;
        }
        
        System.out.println(option + ". Cancel");
        
        String input = getInputWithClear("\nChoose: ");
        
        try {
            int choice = Integer.parseInt(input);
            int spellIndex = 1;
            
            if (player.knowsSpell("Lumos")) {
                if (choice == spellIndex) {
                    player.castSpell("Lumos");
                    waitForEnter();
                    return;
                }
                spellIndex++;
            }
            
            if (player.knowsSpell("Wingardium Leviosa")) {
                if (choice == spellIndex) {
                    player.castSpell("Wingardium Leviosa");
                    waitForEnter();
                    return;
                }
                spellIndex++;
            }

            if (player.knowsSpell("Finite Incantatem")) {
                if (choice == spellIndex) {
                    player.castSpell("Finite Incantatem");
                    waitForEnter();
                    return;
                }
                spellIndex++;
            }
            
            if (choice == spellIndex) {
                System.out.println("You decide not to cast a spell.");
            } else {
                System.out.println("Invalid choice!");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
        }
        
        waitForEnter();
    }
    
    // quest line
    private void triggerQuest1() {
        clearTerminal();
        System.out.println("══════════════════════════════════════════════════════════════════════════════");
        System.out.println("                              NIGHT OF DAY 2");
        System.out.println("══════════════════════════════════════════════════════════════════════════════");
        System.out.println("Night falls at Hogwarts...");
        System.out.println("The castle halls are quiet... eerily quiet.");
        waitForEnter();
        
        System.out.println("As you walk back toward the " + player.getHouse() + " Dormitory.");
        System.out.println("you suddenly hear a whisper echo in the hallway:");
        System.out.println("\"...come closer...\"");
        System.out.println("\nThe voice is soft, haunting, and not like anyone you know.");
        System.out.println("Your chest tightens a little. Something is wrong.");
        waitForEnter();

        System.out.println("****************************** QUEST 1 UNLOCKED *****************************\n");
        System.out.println("QUEST: INVESTIGATE THE STRANGE WHISPER IN THE HALLWAY\n");
        
        System.out.println("What will you do?");
        System.out.println("1. Follow the whisper further down the hallway");
        System.out.println("2. Ignore it and go straight back to dorm");
        if (player.knowsSpell("Lumos")) {
            System.out.println("3. Cast Lumos first before doing anything");
        }
        
        String choice = getInputWithClear("\nChoose: ");
        
        switch (choice) {
            case "1":
                followTheWhisper();
                break;
            case "2":
                ignoreTheWhisper();
                break;
            case "3":
                if (player.knowsSpell("Lumos")) {
                    castLumosInvestigation();
                } else {
                    ignoreTheWhisper(); // Default if they don't know Lumos
                }
                break;
            default:
                ignoreTheWhisper();
        }
        
        completeQuest1();
    }

    private void followTheWhisper() {
        System.out.println("\nYou take cautious steps forward.");
        System.out.println("The whisper gets louder... but suddenly it stops.");
        System.out.println("You find scratch marks on the stone wall...");
        System.out.println("These weren't here earlier.");
        player.increaseKnowledge(15);
        gameState.setStoryFlag("found_scratch_marks", true);
        System.out.println("\n[Hint Unlocked: Something dark is active inside Hogwarts...]");
        waitForEnter();
    }

    private void ignoreTheWhisper() {
        System.out.println("\nYou try to forget it.");
        System.out.println("But the whisper suddenly echoes again behind you...");
        System.out.println("\"...he will rise…\"");
        System.out.println("You freeze for a moment but choose not to investigate.");
        player.increaseKnowledge(10);
        waitForEnter();
    }

    private void castLumosInvestigation() {
        System.out.println("\nYou lift your wand.");
        System.out.println("\"LUMOS!\"");
        System.out.println("Your wand glows — revealing faint black residue on the wall.");
        System.out.println("It looks like cursed magic traces...");
        player.increaseKnowledge(18);
        gameState.setStoryFlag("found_cursed_residue", true);
        System.out.println("\n[Hint Unlocked: This magic is NOT normal...]");
        waitForEnter();
    }

    private void completeQuest1() {
        System.out.println("****************************** QUEST 1 COMPLETED *****************************\n");
        System.out.println("You hurry back to your dorm.");
        System.out.println("Tonight, you sleep — but uneasily.");
        System.out.println("Something dark... has begun.");
        gameState.setQuest1Completed(true);
        gameState.setMainQuestProgress(1);
        waitForEnter();
        clearTerminal();
    }

    private void triggerQuest2() {
        clearTerminal();
        System.out.println("══════════════════════════════════════════════════════════════════════════════");
        System.out.println("                              DAY 3 - MORNING");
        System.out.println("══════════════════════════════════════════════════════════════════════════════");
        System.out.println("As you enter the Great Hall, something feels different today.");
        System.out.println("Students are whispering. The atmosphere feels heavy.");
        System.out.println("Professor McGonagall suddenly walks to the center of the hall.");
        waitForEnter();
        
        System.out.println("McGonagall: \"Students, I need your attention.\"");
        System.out.println("The entire room goes silent.");
        waitForEnter();
        
        System.out.println("McGonagall: \"Luna Lovegood has not returned since last night.\"");
        System.out.println("\"She has mysteriously disappeared.\"");
        System.out.println("Gasps echo around the Great Hall.");
        waitForEnter();
        
        System.out.println("You notice:");
        System.out.println("- Ginny Weasley looks pale and anxious");
        System.out.println("- Hermione looks deeply concerned"); 
        System.out.println("- Even Draco Malfoy falls unusually quiet");
        waitForEnter();
        
        System.out.println("McGonagall: \"Anyone who knows anything must report immediately.\"");
        System.out.println("\"Classes will continue -- but all students must be vigilant.\"");
        waitForEnter();
        
        System.out.println("****************************** QUEST 2 UNLOCKED *****************************\n");
        System.out.println("QUEST: FIND YOUR FIRST CLUE ABOUT LUNA'S DISAPPEARANCE");
        gameState.setCurrentQuest("Find clues about Luna's disappearance");
        gameState.setQuest2InvestigationStarted(true);
        waitForEnter();
        
        startLunaInvestigation();
    }

    private void startLunaInvestigation() {
        clearTerminal();
        System.out.println("══════════════════════════════════════════════════════════════════════════════");
        System.out.println("                     LUNA'S DISAPPEARANCE - INVESTIGATION");
        System.out.println("══════════════════════════════════════════════════════════════════════════════");
        System.out.println("Where will you begin your investigation?");
        System.out.println("1. Go talk to Ginny Weasley (she looks most affected)");
        System.out.println("2. Inspect the Library (maybe Luna left something behind)");
        System.out.println("3. Approach Harry & Ron (maybe they have info)");
        
        String choice = getInputWithClear("\nChoose: ");
        
        switch (choice) {
            case "1":
                talkToGinnyAboutLuna();
                break;
            case "2":
                investigateLibraryForLuna();
                completeQuest2();
                break;
            case "3":
                talkToHarryRonAboutLuna();
                break;
            default:
                talkToHarryRonAboutLuna(); // Default
        }
        
        // quest 2 is not completed here
    }

    private void talkToGinnyAboutLuna() {
        System.out.println("\nGinny hesitates but seems scared to talk.");
        System.out.println("She says she remembers seeing Luna heading to the Library last night.");
        player.increaseKnowledge(5);
        System.out.println("Next step hint: Investigate the Library");
        waitForEnter();
    }

    private void investigateLibraryForLuna() {
        System.out.println("\nIn the Library, you notice one shelf looks oddly rearranged.");
        System.out.println("Something was removed recently.");
        player.increaseKnowledge(8);
        System.out.println("Next step hint: Forbidden Books section is suspicious");
        gameState.setQuest2LibraryVisited(true);
        waitForEnter();
    }

    private void talkToHarryRonAboutLuna() {
        System.out.println("\nHarry & Ron believe Luna wasn't the type to wander alone at night.");
        System.out.println("They suggest trying to find where she last studied → likely Library.");
        player.increaseKnowledge(3);
        System.out.println("Next step hint: Check the Library");
        waitForEnter();
    }

    private void completeQuest2() {
        gameState.setQuest2Completed(true);
        gameState.setMainQuestProgress(2);
        gameState.setCurrentQuest("Investigate the Library for Luna's clues");
        System.out.println("****************************** QUEST 2 COMPLETED *****************************\n");
        System.out.println("You have confirmed Luna was last seen heading toward the Library");
        System.out.println("You found evidence that something was disturbed in the library...");
        waitForEnter();
        clearTerminal();
    }

    private void triggerQuest3() {
        clearTerminal();
        System.out.println("══════════════════════════════════════════════════════════════════════════════");
        System.out.println("                             DAY 3 - NIGHT");
        System.out.println("══════════════════════════════════════════════════════════════════════════════");
        System.out.println("You have confirmed that Luna was last seen in the library, you sneak out at"); 
        System.out.println("night and quietly scour the library for more clues...");
        System.out.println("As you continue researching Luna's disappearance, you discover a sealed dusty"); 
        System.out.println("drawer inside the Restricted Section.");
        System.out.println("The lock suddenly glows a strange dark energy. Something very old... very cursed.");
        waitForEnter();

        System.out.println("You find an old parchment with a riddle written in elegant ink:");
        System.out.println("\n\"I keep secrets that no one sees,");
        System.out.println("I can trap thoughts and memories.");
        System.out.println("I'm written on, but I can control you quietly.");
        System.out.println("What am I?\"");
        waitForEnter();

        System.out.println("****************************** QUEST 3 UNLOCKED *****************************\n");
        System.out.println("QUEST: ANSWER THE RIDDLE CORRECTLY.\n");
        System.out.println("You think of 3 possible answers:");
        System.out.println("1. A mirror");
        System.out.println("2. A diary");
        System.err.println("3. A wand");
        
        String choice = getInputWithClear("\nChoose: ");

        switch (choice) {
            case "1":
                wrongChoice();
                break;
            case "2":
                correctChoice();
                break;
            case "3":
                wrongChoice();
                break;
            default:
                wrongChoice();
                break;
        }

        completeQuest3();
    }

    private void correctChoice() {
        System.out.println("\nCorrect.");
        System.out.println("This knowledge may help you enter...");
        player.increaseKnowledge(10);
        System.out.println("You feel like this riddle connects to something bigger -- but you're not sure what yet.");
    }

    private void wrongChoice() {
        System.out.println("\nIncorrect.");
        System.out.println("The parchment curls and burns into ash immediately");
        player.decreaseKnowledge(5);
        System.out.println("You feel like this riddle connects to something bigger -- but you're not sure what yet.");
    }

    private void completeQuest3() {
        gameState.setQuest3Completed(true);
        gameState.setMainQuestProgress(3);
        System.out.println("****************************** QUEST 3 COMPLETED *****************************\n");
        System.out.println("You hurry back to your dorm.");
        waitForEnter();
        clearTerminal();

    }

    private void triggerQuest4Intro() {
        gameState.setCurrentQuest("Investigate the Restricted Section");
        clearTerminal();
        System.out.println("As you walk through the corridor heading back to your Common Room,");
        System.out.println("you see Ron sitting alone on a bench, looking extremely worried.");
        
        waitForEnter();
        System.out.println("Ron: \"Hey.. I need to tell you something. It's about Ginny.\"");
        System.out.println("\"She's been acting... really strange lately.\"");
        System.out.println("\nRon leans closer, voice low.");
        
        waitForEnter();
        System.out.println("Ron: \"She sneaks out at night. She's always irritated.\"");
        System.out.println("\"And yesterday she had ink all over her fingers... but she wasn't even writing anything.\"");
        
        waitForEnter();
        clearTerminal();
        System.out.println("Ron gives you a torn paper he secretly found inside Ginny's bag");
        
        waitForEnter();
        System.out.println("On the paper is scratched writing:");

        waitForEnter();
        System.out.println("\"The Chamber whispers to those who listen.\"");
        
        waitForEnter();
        clearTerminal();
        System.out.println("Ron: \"I think Ginny is being controlled by dark magic...\"");
        System.out.println("\"And I think this might be connected to Luna's disappearance too...\"");
        System.out.println("\"We need answers. And the only place those answers can exist...");
        
        waitForEnter();
        System.out.println("Ron: \"...is in the Restricted Section.\"");
        System.out.println("\nYou both quietly agree to continue investigating tomorrow.");

        waitForEnter();
        clearTerminal();
    }

    private void triggerQuest4Main() {
        clearTerminal();
        System.out.println("══════════════════════════════════════════════════════════════════════════════");
        System.out.println("                               DAY 4 - NIGHT");
        System.out.println("══════════════════════════════════════════════════════════════════════════════");

        System.out.println("\nNight falls...");
        System.out.println("You can't sleep.");

        waitForEnter();
        System.out.println("Every word Ron said kept echoing in your mind.");
        System.out.println("If Ginny is really being manipulated by dark magic...");
        System.out.println("then you must stop it before it is too late.");

        waitForEnter();
        System.out.println("You think about your agreement to Ron about investigating tomorrow...");
        System.out.println("But you have to act now.");
        System.out.println("\nThere is only one place where forbidden knowledge exists...");

        waitForEnter();
        System.out.println("THE RESTRICTED SECTION.");

        waitForEnter();
        clearTerminal();
        System.out.println("****************************** QUEST 4 UNLOCKED *****************************\n");
        System.out.println("You snuck into the Library once again.");
        System.out.println("You quietly snuck into the RESTRICTED SECTION.");
        System.out.println("Candle flames flicker softly as rows of ancient leather-bound");
        System.out.println("books stare back at you.");

        waitForEnter();
        System.out.println("Suddenly, three black books begin to glow faintly:");
        
        waitForEnter();
        clearTerminal();
        while(true){
            System.out.println("1. Dark Magic and Ancient Possessions");
            System.out.println("2. Secrets of Pure-Blood Legacies");
            System.out.println("3. Forbidden Transformations: Living to Object Binding");
            System.out.print("\nWhich book will you open? (1, 2, or 3)?: ");
            String choice = scanner.nextLine();
            clearTerminal();

            if(choice.equals("1")){
                System.out.println("You open the book and read...");
                System.out.println("\n\"Possession through objects is an old and dangerous magic.\"");
                System.out.println("\"The soul fragment does not need a willing host -- only emotional vulnerability.\"");
                System.out.println("\"Diaries are the most common vessel.\"");
                waitForEnter();
                correctBook();
                break;
            }
            else if(choice.equals("2")){
                System.out.println("You flip through the pages...");
                System.out.println("\nPure-blood magic rituals require lineage...");
                neutralBook();
                System.out.println("Try again...");
                waitForEnter();
                clearTerminal();
            }
            else if (choice.equals("3")){
                System.out.println("This book is about animagus and creature shifts...");
                wrongBook();
                System.out.println("Try again...");
                waitForEnter();
                clearTerminal();
            }
            else{
                noBook();
                System.out.println("Try again...");
                waitForEnter();
                clearTerminal();
            }
        }

        completeQuest4();
    }

    private void correctBook() {
        System.out.println("You FOUND a direct connection!");
        player.increaseKnowledge(50);
    }

    private void neutralBook() {
        System.out.println("...This does not help your investigation.");
        player.increaseKnowledge(2);
    }

    private void wrongBook() {
        System.out.println("Interesting but irrelevant.");
        player.increaseKnowledge(1);
    }

    private void noBook() {
        System.out.println("You couldn't choose out of the three.");
        player.decreaseBravery(10);
    }

    private void completeQuest4() {
        gameState.setQuest4Completed(true);
        gameState.setMainQuestProgress(4);
        System.out.println("\nThis confirms your suspicion...");
        System.out.println("\nThe DARK MAGICAL OBJECT is the source.");
        System.out.println("Someone in Hogwarts is being used as a vessel.");
        System.out.println("You move closer to the truth...");
        waitForEnter();

        System.out.println("****************************** QUEST 4 COMPLETED *****************************\n");
        System.out.println("You read about a new spell: [FINITE INCANTATEM]");
        player.increaseMagic(20);

        player.learnSpell(new FiniteIncantatem());
        waitForEnter();
        clearTerminal();
    }

    private void triggerQuest5() {
        clearTerminal();
        System.out.println("══════════════════════════════════════════════════════════════════════════════");
        System.out.println("                              DAY 5 - NIGHT");
        System.out.println("══════════════════════════════════════════════════════════════════════════════");
        System.out.println("You follow the faint magical signature deep into the castle...");
        System.out.println("The air becomes colder... the walls shift... torches flicker.");
        
        waitForEnter();
        System.out.println("You discover a hidden door behind the second shelf.");
        
        waitForEnter();
        System.out.println("The floor opens beneath you--");

        waitForEnter();
        System.out.println("You fall into a dark chamber...");

        waitForEnter();
        System.out.println("══════════════════════════════════════════════════════════════════════════════");
        System.out.println("                   CHAMBER OF SECRETS - FINAL CONFRONTATION");
        System.out.println("══════════════════════════════════════════════════════════════════════════════");

        waitForEnter();
        clearTerminal();

        System.out.println("Ginny Weasley is lying unconscious on the floor.");
        System.out.println("Beside her, was Luna who looked like to be in some sort of trance.");
        System.out.println("You see her holding a small black diary that pulses with dark magic.");

        waitForEnter();
        System.out.println("A dark mist rises and fills the entire room in seconds.");
        System.out.println("\nVoldemort (through Luna):");
        System.out.println("\"So... " + player.getName() + ". You came alone...\"");

        waitForEnter();
        clearTerminal();

        System.out.println("\"These two ladies opened the chamber for me every night... and they didn't even know it.\"");

        waitForEnter();
        System.out.println("Your heart races.");
        System.out.println("You tighten your grip on your wand.");

        waitForEnter();
        System.out.println("At that moment... a spell flashes in your mind.");
        System.out.println("It was something you read about in the Restricted Section.");

        waitForEnter();
        System.out.println(" !! [FINITE INCANTATEM] !! ");

        waitForEnter();
        System.out.println("\"This spell is one among rare counter-curses. It has been said to be able to");
        System.out.println("cancel the influence of dark magic and break possession...\"");

        waitForEnter();
        clearTerminal();
        System.out.println("The diary floats in midair, Luna along with it.");
        System.out.println("Dark magic swirls violently, you have to act now!");

        waitForEnter();
        System.out.println("What will you do?");
        System.out.println("1. Cast [FINITE INCANTATEM]");
        System.out.println("2. Try to grab and rip the diary physically");

        String choice = getInputWithClear("\nChoose: ");

        switch(choice) {
            case "1":
                FiniteIncantatem.specialCast();
                System.out.println("\nLuna drops softly to the floor, Ginny looks like she's about to wake up.");
                System.out.println("They're both physically weak, but alive.");
                completeQuest5();
                chooseEnding("good");
                break;
            case "2":
                System.out.println("You try to grab the diary with your bare hands...");
                completeQuest5();
                chooseEnding("bad");
                break;
            default:
                break;
        }
    }

    private void completeQuest5() {
        gameState.setQuest5Completed(true);
        gameState.setMainQuestProgress(5);

        waitForEnter();
        System.out.println("**************************** FINAL QUEST COMPLETED ***************************\n");
    }

    private void badEnding() {
        waitForEnter();
        System.out.println("Dark magic explodes and surges violently into your body!");
        
        waitForEnter();
        System.out.println("Your vision blurs. You collapse instantly.");

        waitForEnter();
        System.out.println("The dark chamber closes ever so slowly.");

        waitForEnter();
        System.out.println("As you slowly lose consciousness, you look at Ginny and Luna, still under the influence of this dark magic.");

        waitForEnter();
        System.out.println("You hear a whisper...");
        System.out.println("\"We... will do a lot of beautiful things... [together]...\"");

        waitForEnter();
        clearTerminal();
        System.out.println("══════════════════════════════════════════════════════════════════════════════");
        System.out.println("               GAME OVER - Voldemort's darkness spreads further");
        System.out.println("══════════════════════════════════════════════════════════════════════════════");

        waitForEnter();
        showEnding();
    }

    private void goodEnding() {
        waitForEnter();
        clearTerminal();

        System.out.println("Light slowly returns to the Chamber of Secrets.");
        System.out.println("Ginny weakly opens her eyes... confused and scared, but alive.");
        System.out.println("She sees you first.");

        waitForEnter();
        System.out.println("Ginny: \"You.. you saved me...\"");
        
        waitForEnter();
        System.out.println("The basilisk's presence is gone.");
        System.out.println("The curse is gone.");
        System.out.println("The diary lies powerless on the stone floor.");
        
        waitForEnter();
        System.out.println("You guide Ginny up the long stairs back as you carry the still-unconscious Luna on your back.");

        waitForEnter();
        clearTerminal();

        System.out.println("When you reach the Entrance Hall -- the professors rush to you.");
        System.out.println("Madam Pomfrey immediately escorts Ginny and takes Luna to the Hospital Wing.");
        System.out.println("McGonagall looks at you -- stunned, speechless.");

        waitForEnter();
        System.out.println("Even Dumbledore smiles.");
        System.out.println("\"Not all heroes wear grand titles. Some simple choose to do what must be done.\"");

        waitForEnter();
        System.out.println("The castle feels safe again..");
        System.out.println("warm again...");
        System.out.println("\nHogwarts breathes.");

        waitForEnter();
        System.out.println("You feel your magic grow stronger -- not just from spells...");
        System.out.println("but from courage.");

        waitForEnter();
        clearTerminal();
        System.out.println("══════════════════════════════════════════════════════════════════════════════");
        System.out.println("                 THE END - Thank you for protecting Hogwarts!");
        System.out.println("══════════════════════════════════════════════════════════════════════════════");

        waitForEnter();
        showEnding();
    }

    private void chooseEnding(String end){
        if (end.equals("good")){
            goodEnding();
        }
        else if(end.equals("bad")){
            badEnding();
        }
    }

    private void showEnding() {
        clearTerminal();
        System.out.println("══════════════════════════════════════════════════════════════════════════════");
        System.out.println("                              END OF GAME RESULTS");
        System.out.println("══════════════════════════════════════════════════════════════════════════════");
        
        player.displayStatus();
        System.out.println();
        gameState.displayGameState();
        
        System.out.println("\n--- Remarks ---");
        if (gameState.getStoryFlag("met_harry") && gameState.isCharacterFriend("Harry Potter")) {
            System.out.println("SUCCESS! You've become friends with Harry Potter!");
            System.out.println("Together, you're ready to face the Chamber's secrets!");
        } else if (gameState.getStoryFlag("mastered_magic")) {
            System.out.println("IMPRESSIVE! You've become a powerful wizard!");
            System.out.println("Your magical talents grew tremendously in just 5 days!");
        } else if (gameState.getStoryFlag("met_harry")) {
            System.out.println("GOOD PROGRESS! You met Harry Potter!");
            System.out.println("With more time, you could have become great friends.");
        } else {
            System.out.println("SOLID START! It's been a good first week at Hogwarts!");
            System.out.println("You've learned the basics and are ready for more adventures.");
        }
        
        System.out.println("\nYour first week in Hogwarts concludes... but more adventures await your return!\n");
    }
}
