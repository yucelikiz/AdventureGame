public class Armor {
    private int id;
    private String name;
    private int blockage;
    private int price;

    public Armor(int id, String name, int blockage, int price) {
        this.id = id;
        this.name = name;
        this.blockage = blockage;
        this.price = price;
    }

    public static Armor[] armors(){
        Armor[] armorList = new Armor[3];
        armorList[0] = new Armor(1, "Hafif", 1,15);
        armorList[1] = new Armor(2, "Orta", 3,25);
        armorList[2] = new Armor(3, "Ağır", 5,40);

        return armorList;
    }

    public static Armor getArmorObjByID(int id){
        for(Armor a : Armor.armors()){
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBlockage() {
        return blockage;
    }

    public void setBlockage(int blockage) {
        this.blockage = blockage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
