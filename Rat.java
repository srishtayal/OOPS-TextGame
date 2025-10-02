public class Rat extends Enemy {
    public Rat(String name, int health, int attackPower) {
        super(name, health, attackPower);
    }

    @Override
    public void attack(Player player) {
        System.out.println(Colors.enemy("🐀 " + name) + " bites " + Colors.player(player.getName()) + "!");
        player.setHealth(player.getHealth() - attackPower);
    }
}
