import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    @Test
    public void test1() {
        String message = "";

        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();

        message = message + "isEmpty()\n";
        assertEquals(message,ads1.isEmpty(),sad1.isEmpty());
        message = message + "size()\n";
        assertEquals(message,ads1.size(),sad1.size());


        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                sad1.addLast(i);
                ads1.addLast(i);
                message = message + "addLast(" + i + ")\n" + "size()\n";
                assertEquals(message,ads1.size(),sad1.size());
            } else {
                sad1.addFirst(i);
                ads1.addFirst(i);
                message = message + "addFirst(" + i + ")\n" + "size()\n";
                assertEquals(message,ads1.size(),sad1.size());
            }
        }

        for (int i = 0; i < 10; i += 1) {
            message = message + "get(" + i + ")\n";
            assertEquals(message,ads1.get(i),sad1.get(i));
        }

        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                message = message + "removeFirst()\n";
                assertEquals(message,ads1.removeFirst(),sad1.removeFirst());
                message = message + "size()\n";
                assertEquals(message,ads1.size(),sad1.size());
            } else {
                message = message + "removeLast()\n";
                assertEquals(message,ads1.removeLast(),sad1.removeLast());
                message = message + "size()\n";
                assertEquals(message,ads1.size(),sad1.size());
            }
        }


    }
}
