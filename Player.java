import java.util.*;

public class Player {
    private String name;
    private int score;
    private int health;
    private Inventory inventory;

    // Constructor
    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.health = 100;
        this.inventory = new Inventory();
    }

    // Getters/Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getScore() { return score; }
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }
    public Inventory getInventory() { return inventory; }

    // Actions
    public void move() {
        System.out.println(name + " is moving.");
    }

    public void attack(Enemy enemy) {
        System.out.println(name + " attacks " + enemy.getName() + "!");
        enemy.takeDamage(10);
    }

    public void attackEnemy(Enemy enemy) {
        System.out.println(Colors.action("⚔️ " + Colors.player(name) + " attacks " + Colors.enemy(enemy.getName()) + "!"));
        enemy.takeDamage(10);
    }

    public void useItem(int index) {
        inventory.useItem(index, this);
    }

    public void useItem(Item item) {
        item.use(this);
    }

    public void craft() {
        System.out.println(name + " is crafting...");
    }

    // Score
    public void increaseScore(int points) {
        score += points;
        System.out.println(Colors.success("🎯 " + name + "'s score increased by " + Colors.score(String.valueOf(points)) + 
                          ". Total score: " + Colors.score(String.valueOf(score))));
    }

    public void decreaseScore(int points) {
        score = Math.max(0, score - points);
        System.out.println(Colors.warning("📉 " + name + "'s score decreased by " + points + ". Total score: " + Colors.score(String.valueOf(score))));
    }

    // Status
    public void displayStatus() {
        System.out.println("Player: " + name +
                ", Score: " + score +
                ", Health: " + health +
                ", Inventory items: " + inventory.getItems().size());
    }
}