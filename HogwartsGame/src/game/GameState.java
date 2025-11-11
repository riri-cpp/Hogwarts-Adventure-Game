// src/game/GameState.java
package game;

import java.util.HashMap;
import java.util.Map;

public class GameState {
    private int currentDay;
    private int actionsTaken;
    private int mainQuestProgress;
    private String currentQuest;
    private boolean quest1Completed;
    private boolean quest2Completed;
    private boolean quest2InvestigationStarted;
    private boolean quest2LibraryVisited;
    private boolean quest3Completed;
    private boolean quest4Completed;
    private boolean quest5Completed;
    private boolean gameCompleted;
    
    // Simple relationship tracking
    private Map<String, Integer> friendships;
    
    // Simple story progress
    private Map<String, Boolean> storyFlags;
    
    public GameState() {
        this.currentDay = 1;
        this.actionsTaken = 0;
        this.gameCompleted = false;
        this.friendships = new HashMap<>();
        this.storyFlags = new HashMap<>();
        this.mainQuestProgress = 0;
        this.currentQuest = "Explore Hogwarts";
        this.quest1Completed = false;
        this.quest2Completed = false;
        this.quest2InvestigationStarted = false;
        this.quest2LibraryVisited = false;
        this.quest3Completed = false;
        this.quest4Completed = false;
        this.quest5Completed = false;
        
        // Initialize basic flags
        storyFlags.put("sorted", false);
        storyFlags.put("met_harry", false);
        storyFlags.put("met_hermione", false);
        storyFlags.put("met_ron", false);
        storyFlags.put("heard_voices", false);
        storyFlags.put("mastered_magic", false);
    }
    
    // Simple getters
    public int getCurrentDay() { 
        return currentDay; 
    }
    public int getActionsTaken() { 
        return actionsTaken; 
    }
    public boolean isGameCompleted() { 
        return gameCompleted; 
    }
    
    // Simple setters
    public void setGameCompleted(boolean completed) { 
        this.gameCompleted = completed; 
    }
    
    public void incrementActions() {
        actionsTaken++;
    }

    public void incrementDay() {
        if(actionsTaken == 10){
            currentDay++;
        }
    }
    
    // Friendship system
    public void setRelationship(String characterName, int level) {
        friendships.put(characterName, level);
    }
    
    public int getRelationship(String characterName) {
        return friendships.getOrDefault(characterName, 50);
    }
    
    public void modifyRelationship(String characterName, int change) {
        int current = getRelationship(characterName);
        int newLevel = Math.max(0, Math.min(100, current + change));
        friendships.put(characterName, newLevel);
    }
    
    public boolean isCharacterFriend(String characterName) {
        return getRelationship(characterName) >= 70;
    }
    
    // Story flags
    public void setStoryFlag(String flag, boolean value) {
        storyFlags.put(flag, value);
    }
    
    public boolean getStoryFlag(String flag) {
        return storyFlags.getOrDefault(flag, false);
    }

    // QUEST RELATED
    public int getMainQuestProgress() { 
        return mainQuestProgress; 
    }
    public void setMainQuestProgress(int progress) { 
        this.mainQuestProgress = progress; 
    }

    public String getCurrentQuest() { 
        return currentQuest; 
    }
    public void setCurrentQuest(String quest) { 
        this.currentQuest = quest; 
    }

    // QUEST 1
    public boolean isQuest1Completed() { 
        return quest1Completed; 
    }
    public void setQuest1Completed(boolean completed) { 
        this.quest1Completed = completed; 
    }

    // QUEST 2
    public boolean isQuest2Completed() { 
        return quest2Completed; 
    }
    public void setQuest2Completed(boolean completed) { 
        this.quest2Completed = completed; 
    }

    // QUEST 2 LIBRARY FLAG
    public boolean isQuest2InvestigationStarted() { 
        return quest2InvestigationStarted; 
    }
    public void setQuest2InvestigationStarted(boolean started) { 
        this.quest2InvestigationStarted = started; 
    }

    public boolean isQuest2LibraryVisited() { 
        return quest2LibraryVisited; 
    }
    public void setQuest2LibraryVisited(boolean visited) { 
        this.quest2LibraryVisited = visited; 
    }

    // QUEST 3
    public boolean isQuest3Completed() {
        return quest3Completed;
    }
    public void setQuest3Completed(boolean completed) {
        this.quest3Completed = completed;
    }

    // QUEST 4
    public boolean isQuest4Completed() {
        return quest4Completed;
    }
    public void setQuest4Completed(boolean completed) {
        this.quest4Completed = completed;
    }

    // QUEST 5
    public boolean isQuest5Completed() {
        return quest5Completed;
    }
    public void setQuest5Completed(boolean completed) {
        this.quest5Completed = completed;
    }
    
    // Simple victory condition
    public boolean canAdvanceToEndgame() {
        return getStoryFlag("met_harry") && 
               getStoryFlag("heard_voices") && 
               isCharacterFriend("Harry Potter");
    }
    
    // Simple display
    public void displayGameState() {
        System.out.println("--- Friendships ---");
        for (Map.Entry<String, Integer> entry : friendships.entrySet()) {
            String status = entry.getValue() >= 70 ? "Friend" : 
                           entry.getValue() >= 50 ? "Friendly" : "Enemy";
            System.out.println(entry.getKey() + ": " + entry.getValue() + " (" + status + ")");
        }
        
        System.out.println("\n--- Main Story ---");
        if (quest1Completed) System.out.println("Quest 1: The Whisper in the Hallway");
        if (quest2Completed) System.out.println("Quest 2: The Missing Ravenclaw");
        if (quest3Completed) System.out.println("Quest 3: Midnight Archives - The Forbidden Index");
        if (quest4Completed) System.out.println("Quest 4: The Restricted Section");
        if (quest5Completed) System.out.println("Quest 5: The Confrontation");

        System.out.println("\n--- Easter Eggs and NPCs ---");
        int completed = 0;
        int total = storyFlags.size();
        for (boolean flag : storyFlags.values()) {
            if (flag) completed++;
        }
        System.out.println("Completed: " + completed + "/" + total + " milestones");
        
        // Show key milestones
        if (getStoryFlag("met_harry")) System.out.println("Met Harry Potter");
        if (getStoryFlag("met_hermione")) System.out.println("Met Hermione Granger"); 
        if (getStoryFlag("met_ron")) System.out.println("Met Ron Weasley");
        if (getStoryFlag("heard_voices")) System.out.println("Heard mysterious voices");
        if (getStoryFlag("mastered_magic")) System.out.println("Mastered complex spells");
    }
}