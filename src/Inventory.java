public class Inventory {
    private Weapon weapon;
    private Armor armor;
    private boolean Yemek;
    private boolean Odun;
    private boolean Su;

    public Inventory() {
       this.weapon = new Weapon("Yumruk", -1, 0, 0);
       this.armor = new Armor(-1, "Paçavra", 0, 0 );
       this.Yemek = false;
       this.Odun = false;
       this.Su = false;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public boolean isYemek() {
        return Yemek;
    }

    public void setYemek(boolean yemek) {
        Yemek = yemek;
    }

    public boolean isOdun() {
        return Odun;
    }

    public void setOdun(boolean odun) {
        Odun = odun;
    }

    public boolean isSu() {
        return Su;
    }

    public void setSu(boolean su) {
        Su = su;
    }
}
