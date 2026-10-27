package rpg;

public abstract class Hero {
    protected Weapon weapon; // the bridge

    public Hero(Weapon weapon) {
        this.weapon = weapon;
    }
}
