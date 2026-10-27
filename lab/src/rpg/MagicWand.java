package rpg;

public class MagicWand implements Weapon {
    @Override
    public void use() {
        System.out.println("Zap! Dealing 35 magic damage.");
    }
}
