<h1 align = "center">HOGWARTS ADVENTURE: THE CHAMBER'S LEGACY </h1>
<p align = "center">
<b>CS 2103 </b> <br/>
Anyayahan, Jerlyn P. <br/>
Maeda, Josefu S. <br/>
Pasia, Rheman E.
</p>

## ┊ Overview

The Hogwarts Adventure Game is a Java-based console adventure game where the player becomes a new student in a magical academy similar to Hogwarts. The game allows players to explore locations, interact with characters, cast spells, and progress through a simple storyline.

This project demonstrates the use of Object-Oriented Programming (OOP) concepts in Java, showing how encapsulation, inheritance, polymorphism, and abstraction work together to form a structured interactive game.

## ┊ Object-oriented Principles
### Encapsulation
The integrity of the game state is protected by restricting access to critical data. For example, character data like ``socialLink`` is defined as `protected`, ensuring it can only be modified internally or via controlled public methods such as ``increaseFriendship()`` / ``decreaseFriendship()``. This prevents external, unvalidated changes.

### Abstraction
Abstract classes like ``Character``, ``Location``, and ``Spell`` establish the foundation of the system. These define the necessary contract for all entities (e.g., requiring all characters to implement ``reactToAction()``) without providing implementation details, thus simplifying the ``GameEngine``.

### Inheritance
This principle is applied consistently across three major entity hierarchies: ``Character``, ``Location``, and ``Spell``. Specific entities, such as the ``HarryPotter`` NPC, extend the ``Student`` class, which in turn extends the abstract ``Character`` class. This allows them to inherit core properties and behaviors while adding their own specializations.

### Polymorphism
Polymorphism is demonstrated by the ``reactToAction(action)`` method. The ``GameEngine`` can call this single method on any NPC object (e.g., ``DracoMalfoy`` or ``LunaLovegood``), and each concrete class executes its own unique, house- and personality-specific reaction, making interactions dynamic.

## ┊ Project Structure
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

