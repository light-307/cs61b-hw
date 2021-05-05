import org.junit.Test;
import static org.junit.Assert.*;

public class MYLinkedListDequeTest {



    @Test
    public void test_removeFirst() {
        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

        lld1.addFirst("front");
        lld1.addLast("middle");
        lld1.addLast("back");
        lld1.removeFirst();



        LinkedListDeque<String> lld2 = new LinkedListDeque<String>();

        lld2.addFirst("front");
        lld2.addLast("middle");
        lld2.addLast("back");
        lld2.removeLast();


        assertEquals("back", lld1.get(1));
        assertEquals("middle", lld2.getRecursive(1));


    }
}
