public class Torch extends Item {
    public Torch(String name, String description) {
        super(name, description);
    }

    @Override
    public void use(Player player) {
        System.out.println(player.getName() + " lights up the cave with the " + name + ".");
    }
}