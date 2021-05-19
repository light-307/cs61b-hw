package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(5);

        assertTrue(arb.isEmpty());
        assertFalse(arb.isFull());
        assertEquals(5,arb.capacity());
        assertEquals(0,arb.fillCount());

        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        arb.enqueue(5);

        assertFalse(arb.isEmpty());
        assertTrue(arb.isFull());
        assertEquals(5,arb.capacity());
        assertEquals(1,arb.dequeue());
        assertEquals(4,arb.fillCount());
        assertEquals(2,arb.peek());
    }
}
