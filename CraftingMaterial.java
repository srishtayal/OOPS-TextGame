public class CraftingMaterial extends Item {
    public CraftingMaterial(String name, String description) {
        super(name, description);
    }

    @Override
    public void use(Player player) {
        System.out.println(player.getName() + " examines the " + name + ". It can be used for crafting.");
    }
}