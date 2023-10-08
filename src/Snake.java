import java.util.Random;

public class Snake extends Monster{
    private static Random rnd = new Random();
    public Snake() {
        super(4, "Yılan", rnd.nextInt(3,7), 12, 0);
    }
}
