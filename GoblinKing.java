public class GoblinKing extends Enemy {
    public GoblinKing() {
        super("Goblin King", 100);
    }

    public GoblinKing(String name, int health, int attackPower) {
        super(name, health, attackPower);
    }

    @Override
    public void attack(Player player) {
        System.out.println(Colors.bossEnemy("👑 " + name) + " swings his giant club at " + Colors.player(player.getName()) + "!");
        player.setHealth(player.getHealth() - attackPower);
    }
}