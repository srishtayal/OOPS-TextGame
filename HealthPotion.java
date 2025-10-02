public class HealthPotion extends Item {
    private int healingAmount;

    public HealthPotion(String name, String description, int healingAmount) {
        super(name, description);
        this.healingAmount = healingAmount;
    }

    public int getHealingAmount() {
        return healingAmount;
    }

    @Override
    public void use(Player player) {
        int oldHealth = player.getHealth();
        int newHealth = Math.min(100, oldHealth + healingAmount);
        player.setHealth(newHealth);
        int actualHealing = newHealth - oldHealth;
        
        System.out.println(player.getName() + " uses " + getName() + " and recovers " + actualHealing + " health!");
        System.out.println("Current health: " + newHealth);
        
        // Remove the potion from inventory after use
        player.getInventory().removeItem(this);
    }
}