import java.util.Scanner;

public class Game {

    private final Scanner input = new Scanner(System.in);

    public void start() {
        System.out.println("Macera Oyununa Hoşgeldiniz!");
        System.out.print("Lütfen bir isim giriniz : ");
        String  playerName = input.nextLine();
        Player player = new Player(playerName);
        System.out.println(player.getName() + " bu karanlık ve sisli adaya hoşgeldin.. Attığın her adıma dikkat etmelisin !");
        System.out.println("Lütfen bir karakter seç !");
        System.out.println("----------------------------------------------------------------------------------");
        player.selectChar();


        Location location = null;
        while (true) {
            player.printInfo();
            System.out.println();
            System.out.println("*********** Bölgeler ***********");
            System.out.println();
            System.out.println("1 - Güvenli Ev --> Burası güvenli bölge, hiç düşman yok!");
            System.out.println("2 - Cephanelik --> Silah veya zırh satın alabilirsin");
            System.out.println("3 - Mağara     --> Ödül <Yemek>, dikkatli ol mağarada Zombi olabilir !");
            System.out.println("4 - Orman      --> Ödül <Odun>, dikkatli ol karşına Vampir çıkabilir !");
            System.out.println("5 - Nehir     --> Ödül <Su>, dikkatli ol nehirde Ayılar var !");
            System.out.println("6 - Maden     --> Ödül <Silah, Zırh, Para>, dikkatli bu maden yılanlarla dolu !");
            System.out.println("0 - Çıkış Yap  --> Oyunu Sonlandır!");
            System.out.println();

            System.out.print("Lütfen gitmek istediğiniz bölgeyi seçiniz :");

            int selectLoc = input.nextInt();
            switch (selectLoc) {
                case 0 -> location = null;
                case 1 -> location = new SafeHouse(player);
                case 2 -> location = new ToolStore(player);
                case 3 -> location = new Cave(player);
                case 4 -> location = new Forest(player);
                case 5 -> location = new River(player);
                case 6 -> location = new Mine(player);
                default -> System.out.println("Lütfen geçerli bir bölge giriniz !");
            }
            if (location.getClass().getName().equals("SafeHouse")) {
                if (player.getInventory().isYemek() && player.getInventory().isOdun() && player.getInventory().isSu()) {
                    System.out.println("Tebrikler Oyunu Kazandınız !");
                    break;
                }
            }
            if (location == null){
                System.out.println("Bu karanlık ve sisli adadan çabuk vazgeçtin!");
                break;
            }
            if (!location.onLocation()) {
                System.out.println("OYUN BİTTİ!");
                break;
            }
        }
    }

}
