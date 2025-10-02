import java.util.*;

public class Game {
    private Scanner scanner;
    private Player player;
    private List<Room> rooms;
    private int currentRoomIndex;
    private boolean gameRunning;

    public Game() {
        scanner = new Scanner(System.in);
        rooms = new ArrayList<>();
        currentRoomIndex = 0;
        gameRunning = true;
        initializeGame();
    }

    private void initializeGame() {
        // Get player name
        System.out.println(Colors.room("╔═══════════════════════════════════════╗"));
        System.out.println(Colors.room("║     🏰 DUNGEON ADVENTURE 🗡️           ║"));
        System.out.println(Colors.room("╚═══════════════════════════════════════╝"));
        System.out.print(Colors.action("Enter your character's name: "));
        String playerName = scanner.nextLine();
        player = new Player(playerName);

        // Create initial items
        Weapon sword = new Weapon("Iron Sword", "A basic sword", 15);
        Torch torch = new Torch("Wooden Torch", "Lights up dark areas");
        HealthPotion potion = new HealthPotion("Health Potion", "Restores 30 health", 30);
        player.getInventory().addItem(sword);
        player.getInventory().addItem(torch);
        player.getInventory().addItem(potion);

        // Create rooms
        createRooms();

        System.out.println(Colors.success("\n🌟 Welcome, " + playerName + "! Your adventure begins... 🌟\n"));
    }

    private void createRooms() {
        // Room 1: Cave Entrance
        Room caveEntrance = new Room("Cave Entrance", "A dimly lit cave entrance with moss-covered walls.");
        caveEntrance.addEnemy(new Rat("Small Rat", 15, 3));
        Gem emerald = new Gem("Emerald", "A green gemstone", 50);
        caveEntrance.addItem(emerald);

        // Room 2: Dark Tunnel
        Room darkTunnel = new Room("Dark Tunnel", "A narrow, dark tunnel that echoes with mysterious sounds.");
        darkTunnel.addEnemy(new Slime("Blue Slime", 25, 6));
        darkTunnel.addEnemy(new Rat("Giant Rat", 20, 5));
        CraftingMaterial herb = new CraftingMaterial("Magic Herb", "A mysterious herb with healing properties");
        HealthPotion strongPotion = new HealthPotion("Strong Health Potion", "Restores 50 health", 50);
        darkTunnel.addItem(herb);
        darkTunnel.addItem(strongPotion);

        // Room 3: Goblin Throne Room
        Room throneRoom = new Room("Goblin Throne Room", "A grand chamber with a massive throne. The air feels heavy with danger.");
        throneRoom.addEnemy(new GoblinKing("Goblin King", 100, 20));
        Weapon legendarySword = new Weapon("Legendary Sword", "A powerful ancient weapon", 25);
        throneRoom.addItem(legendarySword);

        rooms.add(caveEntrance);
        rooms.add(darkTunnel);
        rooms.add(throneRoom);
    }

    public void start() {
        while (gameRunning && player.getHealth() > 0) {
            Room currentRoom = rooms.get(currentRoomIndex);
            currentRoom.describe();
            
            if (!currentRoom.getEnemies().isEmpty()) {
                handleCombat(currentRoom);
            } else {
                handleExploration(currentRoom);
            }

            if (player.getHealth() <= 0) {
                gameOver();
                break;
            }

            if (currentRoomIndex >= rooms.size() - 1 && currentRoom.getEnemies().isEmpty()) {
                victory();
                break;
            }
        }
    }

    private void handleCombat(Room room) {
        System.out.println(Colors.damage("\n⚔️  *** COMBAT MODE *** ⚔️"));
        
        while (!room.getEnemies().isEmpty() && player.getHealth() > 0) {
            // status
            System.out.println(Colors.action("\n┌─── Your Status ───┐"));
            String healthDisplay = Colors.healthBar(player.getHealth(), 100) + " " + 
                                 Colors.getHealthColor(player.getHealth(), 100) + player.getHealth() + "/100" + Colors.RESET;
            System.out.println("Health: " + healthDisplay);
            System.out.println("Score: " + Colors.score(String.valueOf(player.getScore())));
            System.out.println(Colors.action("└─────────────────────┘"));
            
            System.out.println(Colors.enemy("\n👹 ─── Enemies in Room ───"));
            for (int i = 0; i < room.getEnemies().size(); i++) {
                Enemy enemy = room.getEnemies().get(i);
                String enemyColor = enemy.getName().contains("King") ? Colors.bossEnemy(enemy.getName()) : Colors.enemy(enemy.getName());
                String healthColor = Colors.getHealthColor(enemy.getHealth(), getMaxEnemyHealth(enemy));
                System.out.println((i + 1) + ". " + enemyColor + " (" + healthColor + "Health: " + enemy.getHealth() + Colors.RESET + ")");
            }

            System.out.println(Colors.menu("\n⚡ Choose your action:"));
            System.out.println(Colors.menu("1. ⚔️  Attack enemy"));
            System.out.println(Colors.menu("2. 🎒 Use item"));
            System.out.println(Colors.menu("3. 🏃 Try to flee"));
            System.out.println(Colors.menu("4. 📋 Show inventory"));
            System.out.print(Colors.action("Enter choice (1-4): "));

            int choice = getValidChoice(1, 4);

            switch (choice) {
                case 1:
                    handleAttack(room);
                    break;
                case 2:
                    handleUseItem();
                    break;
                case 3:
                    if (tryFlee()) {
                        return;
                    }
                    break;
                case 4:
                    player.getInventory().showInventory();
                    continue; // Don't end turn
            }

            // Enemies attack 
            for (Enemy enemy : new ArrayList<>(room.getEnemies())) {
                if (enemy.getHealth() > 0) {
                    enemy.attack(player);
                }
            }

            // Remove defeated enemies
            room.getEnemies().removeIf(enemy -> enemy.getHealth() <= 0);
        }

        if (room.getEnemies().isEmpty()) {
            System.out.println(Colors.success("\n🎉 ═══ ROOM CLEARED! ═══ 🎉"));
            System.out.println(Colors.success("All enemies defeated!"));
            player.increaseScore(50);
        }
    }

    private void handleAttack(Room room) {
        if (room.getEnemies().isEmpty()) {
            System.out.println("No enemies to attack!");
            return;
        }

        System.out.println("Choose enemy to attack:");
        for (int i = 0; i < room.getEnemies().size(); i++) {
            Enemy enemy = room.getEnemies().get(i);
            System.out.println((i + 1) + ". " + enemy.getName() + " (Health: " + enemy.getHealth() + ")");
        }

        int choice = getValidChoice(1, room.getEnemies().size());
        Enemy targetEnemy = room.getEnemies().get(choice - 1);
        
        player.attackEnemy(targetEnemy);
        
        if (targetEnemy.getHealth() <= 0) {
            player.increaseScore(25);
        }
    }

    private void handleUseItem() {
        List<Item> items = player.getInventory().getItems();
        if (items.isEmpty()) {
            System.out.println("No items to use!");
            return;
        }

        System.out.println("Choose item to use:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getName());
        }

        int choice = getValidChoice(1, items.size());
        player.useItem(choice - 1);
    }

    private boolean tryFlee() {
        Random rand = new Random();
        if (rand.nextBoolean()) { 
            System.out.println("You successfully fled from the room!");
            if (currentRoomIndex > 0) {
                currentRoomIndex--;
                System.out.println("You retreated to the previous room.");
            } else {
                System.out.println("You can't retreat any further!");
                return false;
            }
            return true;
        } else {
            System.out.println("Failed to flee! The enemies block your escape!");
            return false;
        }
    }

    private void handleExploration(Room room) {
        System.out.println(Colors.room("\n🔍 *** EXPLORATION MODE *** 🔍"));
        System.out.println(Colors.success("✅ The room is clear of enemies."));
        
        boolean exploring = true;
        while (exploring) {
            System.out.println(Colors.menu("\n🌟 Choose your action:"));
            System.out.println(Colors.menu("1. 🔎 Search for items"));
            System.out.println(Colors.menu("2. 🚪 Move to next room"));
            System.out.println(Colors.menu("3. 🎒 Show inventory"));
            System.out.println(Colors.menu("4. 📊 Show status"));
            System.out.println(Colors.menu("5. 😴 Rest (restore some health)"));
            System.out.print(Colors.action("Enter choice (1-5): "));

            int choice = getValidChoice(1, 5);

            switch (choice) {
                case 1:
                    searchRoom(room);
                    break;
                case 2:
                    if (currentRoomIndex < rooms.size() - 1) {
                        currentRoomIndex++;
                        System.out.println("Moving to the next room...");
                        exploring = false;
                    } else {
                        System.out.println("This is the final room!");
                    }
                    break;
                case 3:
                    player.getInventory().showInventory();
                    break;
                case 4:
                    player.displayStatus();
                    break;
                case 5:
                    rest();
                    break;
            }
        }
    }

    private void searchRoom(Room room) {
        if (!room.getItems().isEmpty()) {
            System.out.println(Colors.success("🎁 You found items in the room:"));
            for (Item item : room.getItems()) {
                String itemColor = getItemColor(item);
                System.out.println("- " + itemColor + item.getName() + Colors.RESET + ": " + item.getDescription());
                player.getInventory().addItem(item);
            }
            room.getItems().clear();
        } else {
            System.out.println(Colors.warning("🔍 You searched thoroughly but found nothing useful."));
        }
    }

    private String getItemColor(Item item) {
        if (item instanceof Weapon) return Colors.weapon(item.getName());
        if (item instanceof Gem) return Colors.gem(item.getName());
        if (item instanceof HealthPotion) return Colors.healing(item.getName());
        return Colors.item(item.getName());
    }

    private void rest() {
        int healthRestored = Math.min(20, 100 - player.getHealth());
        player.setHealth(player.getHealth() + healthRestored);
        System.out.println(Colors.healing("😴 You rest and restore " + healthRestored + " health."));
        String healthDisplay = Colors.healthBar(player.getHealth(), 100) + " " + 
                             Colors.getHealthColor(player.getHealth(), 100) + player.getHealth() + "/100" + Colors.RESET;
        System.out.println("Current health: " + healthDisplay);
    }

    private int getValidChoice(int min, int max) {
        while (true) {
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); 
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.print("Invalid choice. Please enter a number between " + min + " and " + max + ": ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.nextLine(); 
            }
        }
    }

    private void gameOver() {
        System.out.println(Colors.defeat("\n💀 ═══ GAME OVER ═══ 💀"));
        System.out.println(Colors.defeat("You have been defeated!"));
        System.out.println("Final Score: " + Colors.score(String.valueOf(player.getScore())));
        System.out.println(Colors.action("Thanks for playing! Try again to improve your score! 🎮"));
    }

    private int getMaxEnemyHealth(Enemy enemy) {
        if (enemy.getName().contains("King")) return 100;
        if (enemy.getName().contains("Slime")) return 30;
        if (enemy.getName().contains("Rat")) return 20;
        return 30; 
    }

    private void victory() {
        System.out.println(Colors.victory("\n🏆 ═══ VICTORY! ═══ 🏆"));
        System.out.println(Colors.success("Congratulations! You have completed the dungeon!"));
        System.out.println("Final Score: " + Colors.score(String.valueOf(player.getScore())));
        System.out.println("Health remaining: " + Colors.health(String.valueOf(player.getHealth())));
        System.out.println(Colors.victory("🎖️  You are a true dungeon master! 🎖️"));
    }
}