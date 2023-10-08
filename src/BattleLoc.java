import java.util.Objects;
import java.util.Random;

public abstract class BattleLoc extends Location{
    private Monster monster;
    private String award;
    private int maxMonster;

    public BattleLoc(Player player, String name, Monster monster, String award, int maxMonster) {
        super(player, name);
        this.monster = monster;
        this.award = award;
        this.maxMonster = maxMonster;
    }

    @Override
    public boolean onLocation() {
        int monsNumber = this.randomMonsterNumber();

        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("Şu an buradasınız : " + this.getName());
        System.out.println("Dikkatli ol!! Burada " + monsNumber + " tane " + this.getMonster().getName() + " yaşıyor!");
        System.out.print("<S>avaş veya <K>aç :");
        String selectCase = input.nextLine().toUpperCase();
        if (selectCase.equals("S")){
            if (combat(monsNumber)){
                System.out.println(this.getName() + " bölgesindeki tüm düşmanları yendiniz !");
                if (this.award.equals("Yemek") && !this.getPlayer().getInventory().isYemek()) {
                    System.out.println(this.getAward() + " ödülü kazandınız !");
                    getPlayer().getInventory().setYemek(true);
                }
                if (this.award.equals("Odun") && !this.getPlayer().getInventory().isOdun()) {
                    System.out.println(this.getAward() + " ödülü kazandınız !");
                    getPlayer().getInventory().setOdun(true);
                }
                if (this.award.equals("Su") && !this.getPlayer().getInventory().isSu()) {
                    System.out.println(this.getAward() + " ödülü kazandınız !");
                    getPlayer().getInventory().setSu(true);
                }
                return true;

            }
            if (this.getPlayer().getHealth() <= 0) {
                System.out.println("Öldünüz !");
                return false;
            }
        }

        return true;
    }

    public boolean combat(int monsNumber){
        for (int i = 1; i <= monsNumber; i++) {
            this.getMonster().setHealth(this.getMonster().getOriginalHealth());
            playerStats();
            monsterStats(i);
            while (this.getPlayer().getHealth() > 0 && this.getMonster().getHealth() > 0) {
                System.out.print("<V>ur veya <K>aç : ");
                String selectCombat = input.nextLine().toUpperCase();
                int firstAttack = this.randomFirstAttack();
                if (selectCombat.equals("V") && firstAttack >= 50) {
                    System.out.println("Siz vurdunuz !");
                    this.getMonster().setHealth(this.getMonster().getHealth() - this.getPlayer().getTotalDamage());
                    afterHit();
                    if (this.getMonster().getHealth() > 0) {
                        System.out.println();
                        System.out.println("Canavar size vurdu !");
                        int monsterDamage = this.getMonster().getDamage() - this.getPlayer().getInventory().getArmor().getBlockage();
                        if (monsterDamage < 0) {
                            monsterDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - monsterDamage);
                        afterHit();
                    }
                }
                else if (selectCombat.equals("V")){
                    System.out.println("Canavar size vurdu !");
                    int monsterDamage = this.getMonster().getDamage() - this.getPlayer().getInventory().getArmor().getBlockage();
                    if (monsterDamage < 0) {
                        monsterDamage = 0;
                    }
                    this.getPlayer().setHealth(this.getPlayer().getHealth() - monsterDamage);
                    afterHit();
                    if (this.getMonster().getHealth() > 0) {
                        System.out.println();
                        System.out.println("Siz vurdunuz !");
                        this.getMonster().setHealth(this.getMonster().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                    }

                } else {
                    return false;
                }

            }

            if (this.getMonster().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("************ Düşmanı yendiniz ! *************");
                if (Objects.equals(getMonster().getName(), "Yılan")) {
                    winAward();
                } else {
                    System.out.println(this.getMonster().getReward() + " para kazandınız !");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getMonster().getReward());
                    System.out.println("Güncel paranız : " + this.getPlayer().getMoney());
                }

            }
            else {
                return false;
            }
        }
        return true;
    }

    public void afterHit() {
        System.out.println("Sizin canınız : " + this.getPlayer().getHealth());
        System.out.println(this.getMonster().getName() + "'in canı : " + this.getMonster().getHealth());
    }

    public void playerStats() {
        System.out.println("Oyuncu Değerleri:");
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("Sağlık : " + this.getPlayer().getHealth());
        System.out.println("Silah : " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Zırh : " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama : " + this.getPlayer().getInventory().getArmor().getBlockage());
        System.out.println("Hasar : " + this.getPlayer().getTotalDamage());
        System.out.println("Para : " + this.getPlayer().getMoney());

    }

    public void monsterStats(int i){
        System.out.println(i + "." +this.getMonster().getName() + " Değerleri :");
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("Sağlık :" + this.getMonster().getHealth());
        System.out.println("Hasar :" + this.getMonster().getDamage());
        System.out.println("Mükafat :" + this.getMonster().getReward());
    }

    public int randomMonsterNumber(){
        Random r = new Random();
        return r.nextInt(this.getMaxMonster())+1;
    }

    public int randomFirstAttack(){
        Random assault = new Random();
        return assault.nextInt(100);
    }

    public void winAward() {
        Random rAward = new Random();
        int randomAward = rAward.nextInt(100);
        if (randomAward <=15) {
            Random rWeapon = new Random();
            int randomWeapon = rWeapon.nextInt(100);
            if (randomWeapon <=20) {
                System.out.println("Tüfek kazandın !");
                getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(3));
            } else if (randomWeapon <= 50) {
                System.out.println("Kılıç kazandın !");
                getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(2));

            } else {
                System.out.println("Tabanca kazandın !");
                getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(1));
            }

        } else if (randomAward <= 30) {
            Random rArmor = new Random();
            int randomArmor = rArmor.nextInt(100);
            if (randomArmor <=20) {
                System.out.println("Ağır zırh kazandın !");
                getPlayer().getInventory().setArmor(Armor.getArmorObjByID(3));
            } else if (randomArmor <= 50) {
                System.out.println("Ortaa zırh kazandın !");
                getPlayer().getInventory().setArmor(Armor.getArmorObjByID(2));

            } else {
                System.out.println("Hafif zırh kazandın !");
                getPlayer().getInventory().setArmor(Armor.getArmorObjByID(1));
            }
        } else if (randomAward <= 55) {
            Random rMoney = new Random();
            int randomMoney = rMoney.nextInt(100);
            if (randomMoney <= 20) {
                System.out.println(" 10 adet para kazandın !");
                getPlayer().setMoney(getPlayer().getMoney() + 10);
            } else if (randomMoney <= 50) {
                System.out.println(" 5 adet para kazandın !");
                getPlayer().setMoney(getPlayer().getMoney() + 5);
            } else {
                System.out.println(" 1 adet para kazanabildin !");
                getPlayer().setMoney(getPlayer().getMoney() + 1);
            }
            System.out.println("Yeni bakiyen :" + getPlayer().getMoney());
        } else {
            System.out.println("Hiçbir şey kazanamadın !!");
        }
    }


    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxMonster() {
        return maxMonster;
    }

    public void setMaxMonster(int maxMonster) {
        this.maxMonster = maxMonster;
    }
}
