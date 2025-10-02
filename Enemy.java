public abstract class Enemy {
    protected String name;
    protected int health;
    protected int attackPower;

    public Enemy(String name, int health) {
        this.name = name;
        this.health = health;
        this.attackPower = 10; 
    }

    public Enemy(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getAttackPower() { return attackPower; }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            System.out.println(Colors.success("💀 " + Colors.enemy(name) + " has been defeated!"));
        } else {
            String healthColor = Colors.getHealthColor(health, 100);
            System.out.println(Colors.enemy(name) + " took " + Colors.damage(String.valueOf(damage)) + 
                             " damage. Remaining health: " + healthColor + health + Colors.RESET);
        }
    }

    public abstract void attack(Player player);
}
