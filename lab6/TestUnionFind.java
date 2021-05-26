import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnionFind {

    @Test
    public void test() {
        UnionFind sets = new UnionFind(8);
        sets.connect(1,2);
        sets.connect(3,4);
        sets.connect(4,3);
        sets.connect(5,7);
        sets.connect(4,5);

        assertTrue(sets.isConnected(3,7));
        assertTrue(sets.isConnected(3,5));
        assertFalse(sets.isConnected(3,2));
        assertEquals(7,sets.find(3));
        assertEquals(0,sets.find(0));
        assertEquals(7,sets.parent(3));
    }


}
