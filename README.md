# Dungeon Adventure - Game Guide

## About the Project
This is a simple text-based dungeon crawler built using Object-Oriented Programming (OOP) in Java.  
The player explores rooms, collects items, battles enemies, and increases their score.  
It demonstrates key OOP concepts like classes, inheritance, abstraction, and composition.

## Objective
Explore the dungeon, defeat all enemies, and reach the final room to win.

## Combat System
- Turn-based combat: You attack, then enemies counter-attack.
- Choose your actions wisely: attack specific enemies or use items.
- Different enemies have different health and attack power.
- Defeat enemies to earn score points.

## Inventory Management
- Start with basic equipment (sword, torch, health potion).
- Find items by searching cleared rooms.
- Use health potions to restore health during combat.
- Weapons can be used in combat for extra damage.

## Room Exploration
- 3 rooms total: Cave Entrance → Dark Tunnel → Goblin Throne Room.
- Each room has enemies to defeat and items to find.
- Clear all enemies before you can explore safely.
- Search rooms for valuable items after combat.
- Rest to recover health between battles.

## Strategic Tips
1. Use health potions when your health gets low (below 30).
2. Focus on weaker enemies first in multi-enemy rooms.
3. Don't forget to search rooms for powerful items.
4. If overwhelmed, try to flee and return stronger.
5. Save strong healing items for the final boss fight.

## Scoring
- Defeat enemy: +25 points.
- Clear room: +50 points.
- Survive and win: Bonus based on remaining health.

## Controls
- Follow the numbered menu options.
- Enter the number of your choice and press Enter.
- Type carefully - invalid input will prompt you to try again.

## How to Run
1. Make sure you have **Java JDK installed** (version 17+ recommended).  
   Check installation with:
   ```bash
   java -version
   javac -version
2. Compile all Java files:
    ```bash
    javac *.java
3. Run the game:
    ```bash
    java Main