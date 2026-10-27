package rpg;

public class Knight extends Hero {
    public Knight(Weapon weapon) {
        super(weapon);
    }

    public void attack() {
        weapon.use();
    }
}
