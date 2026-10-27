package rpg;

public class Sword implements Weapon {
    @Override
    public void use() {
        System.out.println("Slash! Dealing 20 physical damage.");
    }
}
