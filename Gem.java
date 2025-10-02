public class Gem extends Item {
    private int value;

    public Gem(String name, String description, int value) {
        super(name, description);
        this.value = value;
    }

    @Override
    public void use(Player player) {
        player.increaseScore(value);
        System.out.println(player.getName() + " uses the " + name + " and gains " + value + " score!");
    }
}