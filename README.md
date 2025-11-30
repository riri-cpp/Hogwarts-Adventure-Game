<h1 align = "center">HOGWARTS ADVENTURE: THE CHAMBER'S LEGACY </h1>
<p align = "center">
<b>CS 2103 </b> <br/>
Anyayahan, Jerlyn P. <br/>
Maeda, Josefu S. <br/>
Pasia, Rheman E.
</p>

##  Overview

The Hogwarts Adventure Game is a Java-based console adventure game where the player becomes a new student in a magical academy similar to Hogwarts. The game allows players to explore locations, interact with characters, cast spells, and progress through a simple storyline.

This project demonstrates the use of Object-Oriented Programming (OOP) concepts in Java, showing how encapsulation, inheritance, polymorphism, and abstraction work together to form a structured interactive game.

## Object-oriented Principles
### Encapsulation
The integrity of the game state is protected by restricting access to critical data. For example, character data like ``socialLink`` is defined as `protected`, ensuring it can only be modified internally or via controlled public methods such as ``increaseFriendship()`` / ``decreaseFriendship()``. This prevents external, unvalidated changes.

### Abstraction
Abstract classes like ``Character``, ``Location``, and ``Spell`` establish the foundation of the system. These define the necessary contract for all entities (e.g., requiring all characters to implement ``reactToAction()``) without providing implementation details, thus simplifying the ``GameEngine``.

### Inheritance
This principle is applied consistently across three major entity hierarchies: ``Character``, ``Location``, and ``Spell``. Specific entities, such as the ``HarryPotter`` NPC, extend the ``Student`` class, which in turn extends the abstract ``Character`` class. This allows them to inherit core properties and behaviors while adding their own specializations.

### Polymorphism
Polymorphism is demonstrated by the ``reactToAction(action)`` method. The ``GameEngine`` can call this single method on any NPC object (e.g., ``DracoMalfoy`` or ``LunaLovegood``), and each concrete class executes its own unique, house- and personality-specific reaction, making interactions dynamic.

##  Project Structure
```

📂 HogwartsGame/
├── 📂 bin/
│  ├── 📂 game/
│  │   ├── ☕ GameEngine$1.class
│  │   ├── ☕ GameEngine$2.class
│  │   ├── ☕ GameEngine.class
│  │   ├── ☕ GameState.class
│  │   └── ☕ Player.class
│  ├── 📂 characters/
│  │   ├── ☕ Character.class
│  │   ├── ☕ DracoMalfoy.class
│  │   ├── ☕ HarryPotter.class
│  │   ├── ☕ HermioneGranger.class
│  │   ├── ☕ LunaLovegood.class
│  │   ├── ☕ RonWeasley.class
│  │   └── ☕ Student.class
│  ├── 📂 locations/
│  │   ├── ☕ Classroom.class
│  │   ├── ☕ GreatHall.class
│  │   ├── ☕ Library.class
│  │   └── ☕ Location.class
│  ├── 📂 spells/
│  │   ├── ☕ FiniteIncantatem.class
│  │   ├── ☕ Lumos.class
│  │   ├── ☕ WingardiumLeviosa.class
│  │   └── ☕ Spell.class                  
│  └── ☕ Main.class
│
├──📂 src/
│   ├── 📂 game/
│   │   ├── ☕ GameEngine.java
│   │   ├── ☕ Player.java   
│   │   └── ☕ GameState.java
│   ├── 📂 characters/
│   │   ├── ☕ Character.java  
│   │   ├── ☕ DracoMalfoy.java       
│   │   ├── ☕ HarryPotter.java   
│   │   ├── ☕ HermioneGranger.java   
│   │   ├── ☕ LunaLovegood.java  
│   │   ├── ☕ RonWeasley.java       
│   │   └── ☕ Student.java     
│   ├── 📂 locations/
│   │   ├── ☕ Classroom.java       
│   │   ├── ☕ GreatHall.java     
│   │   ├── ☕ Library.java       
│   │   └── ☕ Location.java                     
│   ├── 📂 spells/
│   │   ├── ☕ FiniteIncantatem.java       
│   │   ├── ☕ Lumos.java     
│   │   ├── ☕ WingardiumLeviosa.java       
│   │   └── ☕ Spell.java                          
│   └── ☕ Main.java      
│ 
├── ☕ .gitignore
│ 
└── ☕ README.md

```
- `📂 HogwartsGame/` – Root folder of the project
  - `📂 bin/` – Contains compiled `.class` files
    - `📂 game/` – Compiled files for game engine, player, and game state
    - `📂 characters/` – Compiled files for all characters and NPCs
    - `📂 locations/` – Compiled files for all locations
    - `📂 spells/` – Compiled files for all spells
  - `📂 src/` – Contains all source code (`.java` files)
    - `📂 game/` – Source code for game engine, player, and game state
    - `📂 characters/` – Source code for all characters and NPCs
    - `📂 locations/` – Source code for all locations
    - `📂 spells/` – Source code for all spells
  - `☕ .gitignore` – Specifies files and folders to ignore in Git
  - `☕ README.md` – Project documentation, includes instructions and overview

##  How to Run the Program

### Compiling the Program
1. Download the code from the repository as a ZIP file.
2. Extract the contents of the ZIP file into a folder. The folder should have the following structure:
   ```
   Hogwarts-Adventure-Game-main\Hogwarts-Adventure-Game-main\HogwartsGame
   ```
4. Open Visual Studio Code and select the "Open Folder" option.
5. Open the `HogwartsGame` Folder from the extracted folder.
6. Open the `Main.java ` file
7. Press Ctrl + ` to open the terminal, the prompt should look something like this:
   ```
   PS C:\Users\Rheman Pasia\Downloads\Hogwarts-Adventure-Game-main\Hogwarts-Adventure-Game-main\HogwartsGame>
   ```
8. Compile the program using this command in the terminal:
   ```
    javac -d bin src/*.java src/characters/*.java src/game/*.java src/locations/*.java src/spells/*.java
   ```
### Running the Program
For **VSCode**, run the program using this command on the terminal:
   ```
   java -cp bin Main
   ```
For the **Command Prompt**, first navigate to the src folder and right click on it, select "Open with Terminal" and you should be met with this line:
```
PS C:\Users\Rheman Pasia\Downloads\CS - 2103\OOP\Activities\HogwartsGame\HogwartsGame\src>
```
If the program has been compiled properly, run this command:
```
java Main.java
```

## Sample Outputs

### Start of the Game
```
╔══════════════════════════════════════╗
║         HOGWARTS ADVENTURE           ║
║        THE CHAMBER'S LEGACY          ║
╚══════════════════════════════════════╝

Press Enter to begin...
```

### Main Menu
```
══════════════════════════════════════
DAY 1 | Action 1/10
══════════════════════════════════════
You're in the Great Hall. The enormous main hall with four long house tables and enchanted ceiling
The ceiling shows a cloudy sky. Students are eating and chatting.

What would you like to do?
1. Talk to students
2. Explore this area
3. Move to another location
4. Check your status
5. Practice magic
6. View game progress
7. Cast spells

Choose:
```

### Player Stats Menu
```
Name: [Rheman] - House: [Gryffindor]
Magical Power: 110
Knowledge Possessed: 50
Bravery: 50
Cunning: 50

Known Spells:
Lumos
Wingardium Leviosa


[Press Enter to continue...]
```

##  Authors and Acknowledgement

### Authors

<table>
<tr>
    <th> Name </th>
    <th> Role </th>
</tr>
<tr>
    <td><strong>Anyayahan, Jerlyn P.</strong> <br/>
    <td>Narrative Designer</td>
</tr>
<tr>
    <td><strong>Maeda, Josefu S.</strong> <br/>
    </td>
    <td>Program Compiler</td>
</tr>
<tr>
    <td><strong>Pasia, Rheman E.</strong> <br/>
    </td>
    <td>Project Leader/Game Programmer</td>
</tr>
</table>

### Acknowledgements

We would like to express our deepest gratitude to Ma'am Fatima Marie P. Agdon, who guided us throughout the course and provided us with the knowledge and foundations of Object-Oriented Programming that made this project possible. We also extend our appreciation to our groupmates for their collaboration and effort. To Rheman, whose skills and creativity brought our ideas to life. Thank you for the work you poured into this project. Your talent and the way you helped shape our concepts into something better. To Jerlyn, for her masterfully crafted story and creative vision, and to AJ, thank you for handling the compilation of our work. Lastly, we acknowledge the resources, tutorials, and documentation that supported us and deepened our understanding during development.

## Future Enhancements

For future versions of this project, we aim to expand the storyline with new quests, branching choices, and additional characters inspired by the wizarding world. We also plan to improve the user interface, add more spells with unique mechanics, and enhance text-based interactions to create a deeper sense of immersion. Features such as a save-and-load system, inventory management, sound effects, and more dynamic combat sequences are also planned. These enhancements will help enrich the overall gameplay and provide a more engaging and polished experience.

## References

The narrative structure, quests, and character inspirations of this project were influenced by Harry Potter and the Chamber of Secrets. Several events and themes from the movie served as creative foundations for designing the tasks and story progression in our console-based game. Additional guidance came from various programming resources, including Java documentation, OOP tutorials, and class materials that supported our understanding and implementation of the project.
