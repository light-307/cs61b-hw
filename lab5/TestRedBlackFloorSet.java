import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by hug.
 */
public class TestRedBlackFloorSet {
    @Test
    public void randomizedTest() {
        AListFloorSet a = new AListFloorSet();
        RedBlackFloorSet b = new RedBlackFloorSet();
        double k = 0;

        for (int i = 0; i < 1000000; i++) {
            k = StdRandom.uniform(-5000, 5000);
            a.add(k);
            b.add(k);
        }
        for (int i = 0; i < 100000; i++) {
            k = StdRandom.uniform(-5000, 5000);
            assertEquals(a.floor(k), b.floor(k), 0.000001);
        }

    }
}
