package rpg;

public class RpgDemo {
    public static void main(String[] args) {
        System.out.println("===== Task 6: RPG Weapon System =====");
        Knight swordKnight = new Knight(new Sword());
        swordKnight.attack();

        Knight wandKnight = new Knight(new MagicWand());
        wandKnight.attack();
    }
}
