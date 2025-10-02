import java.util.*;

public class Room {
    private String name;
    private String description;
    private List<Enemy> enemies;
    private List<Item> items;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.enemies = new ArrayList<>();
        this.items = new ArrayList<>();
    }

    public String getName() { return name; }
    public List<Enemy> getEnemies() { return enemies; }
    public List<Item> getItems() { return items; }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void describe() {
        System.out.println(Colors.room("\n╔═══ " + name + " ═══╗"));
        System.out.println(Colors.CYAN + description + Colors.RESET);
        
        if (enemies.isEmpty()) {
            System.out.println(Colors.success("✅ No enemies here."));
        } else {
            System.out.println(Colors.enemy("⚔️ Enemies in the room:"));
            for (Enemy e : enemies) {
                String enemyName = e.getName().contains("King") ? Colors.bossEnemy(e.getName()) : Colors.enemy(e.getName());
                System.out.println("  - " + enemyName + " (" + Colors.getHealthColor(e.getHealth(), 100) + "Health: " + e.getHealth() + Colors.RESET + ")");
            }
        }
        
        if (!items.isEmpty()) {
            System.out.println(Colors.item("💎 Items visible:"));
            for (Item item : items) {
                String itemName = getItemColorForRoom(item);
                System.out.println("  - " + itemName);
            }
        }
        System.out.println(Colors.room("╚" + "═".repeat(name.length() + 8) + "╝"));
    }

    private String getItemColorForRoom(Item item) {
        if (item.getName().contains("Sword") || item.getName().contains("Weapon")) {
            return Colors.weapon(item.getName());
        } else if (item.getName().contains("Gem") || item.getName().contains("Ruby") || item.getName().contains("Emerald")) {
            return Colors.gem(item.getName());
        } else if (item.getName().contains("Potion") || item.getName().contains("Health")) {
            return Colors.healing(item.getName());
        }
        return Colors.item(item.getName());
    }
}