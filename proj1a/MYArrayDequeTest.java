import org.junit.Test;
import static org.junit.Assert.*;

public class MYArrayDequeTest {

    @Test
    public void test_removeFirst() {
        ArrayDeque<String> lld1 = new ArrayDeque<String>();

        assertEquals(true, lld1.isEmpty());

        lld1.addFirst("front");
        lld1.addLast("middle");
        lld1.addLast("back");
        lld1.removeFirst();

        assertEquals(false, lld1.isEmpty());
        assertEquals(2, lld1.size());
        lld1.printDeque();

        ArrayDeque<String> lld2 = new ArrayDeque<String>();

        lld2.addFirst("1");
        lld2.addLast("2");
        lld2.addLast("3");
        lld2.addLast("4");
        lld2.addLast("5");
        lld2.addLast("6");
        lld2.addLast("7");
        lld2.addLast("8");
        lld2.addLast("9");
        lld2.addLast("10");
        lld2.addLast("11");
        lld2.addLast("12");
        lld2.addLast("13");
        lld2.addLast("14");
        lld2.addLast("15");
        lld2.addLast("16");
        lld2.addLast("17");
        lld2.removeFirst();
        lld2.removeLast();
        lld2.removeLast();
        lld2.removeLast();
        lld2.removeLast();
        lld2.removeLast();
        lld2.removeLast();
        lld2.removeLast();
        lld2.removeLast();
        lld2.removeLast();
        lld2.printDeque();

        assertEquals("5", lld2.get(3));
        assertEquals("6", lld2.get(4));
        assertEquals(7, lld2.size());


    }


}
