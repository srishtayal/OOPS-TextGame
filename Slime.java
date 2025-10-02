public class Slime extends Enemy {
    public Slime(String name, int health, int attackPower) {
        super(name, health, attackPower);
    }

    @Override
    public void attack(Player player) {
        System.out.println(Colors.enemy("🟢 " + name) + " slimes " + Colors.player(player.getName()) + "!");
        player.setHealth(player.getHealth() - attackPower);
    }
}
