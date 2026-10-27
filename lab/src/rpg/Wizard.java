package rpg;

public class Wizard extends Hero {
    public Wizard(Weapon weapon) {
        super(weapon);
    }

    public void attack() {
        weapon.use();
    }
}
