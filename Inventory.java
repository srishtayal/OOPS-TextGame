import java.util.*;

public class Inventory {
    private List<Item> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
        System.out.println(item.getName() + " added to inventory.");
    }

    public void removeItem(Item item) {
        items.remove(item);
        System.out.println(item.getName() + " removed from inventory.");
    }

    public List<Item> getItems() {
        return items;
    }

    public void useItem(int index, Player player) {
        if (index >= 0 && index < items.size()) {
            items.get(index).use(player);
        } else {
            System.out.println("Invalid item index.");
        }
    }

    public void showInventory() {
        System.out.println(Colors.action("\n🎒 ═══ Inventory ═══"));
        if (items.isEmpty()) {
            System.out.println(Colors.warning("📭 Inventory is empty."));
        } else {
            for (int i = 0; i < items.size(); i++) {
                String itemName = getColoredItemName(items.get(i));
                System.out.println((i + 1) + ". " + itemName + " - " + items.get(i).getDescription());
            }
        }
        System.out.println(Colors.action("═══════════════════"));
    }

    private String getColoredItemName(Item item) {
        if (item instanceof Weapon) {
            return Colors.weapon(item.getName());
        } else if (item.getName().contains("Gem") || item.getName().contains("Ruby") || item.getName().contains("Emerald")) {
            return Colors.gem(item.getName());
        } else if (item.getName().contains("Potion") || item.getName().contains("Health")) {
            return Colors.healing(item.getName());
        } else if (item instanceof Torch) {
            return Colors.YELLOW + item.getName() + Colors.RESET;
        }
        return Colors.item(item.getName());
    }
}