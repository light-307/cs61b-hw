package bearmaps;

import org.junit.Test;
import static org.junit.Assert.*;

import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.introcs.StdRandom;


public class ArrayHeapMinPQTest {

    @Test
    public void Test_Constructor_add_print() {
        ArrayHeapMinPQ<Integer> heapPQ = new ArrayHeapMinPQ<Integer>();
        NaiveMinPQ<Integer> naivePQ = new NaiveMinPQ<Integer>();

        heapPQ.add(5,5);
        heapPQ.add(2,2);
        heapPQ.add(1,1);
        heapPQ.add(3,3);
        heapPQ.add(4,4);
        heapPQ.add(9,9);
        heapPQ.add(6,6);
        heapPQ.add(8,8);
        heapPQ.add(7,7);

        heapPQ.printSimpleHeapDrawing();
    }

    @Test
    public void Test_add_contain_getSmallest_size() {
        ArrayHeapMinPQ<Double> heapPQ = new ArrayHeapMinPQ<Double>();
        NaiveMinPQ<Double> naivePQ = new NaiveMinPQ<Double>();
        double add_num;

        for (int i = 0; i < 5000; i++) {
            assertFalse(heapPQ.contains(2000.0));

            add_num = StdRandom.uniform(-1000.0, 1000.0);
            if (!heapPQ.contains(add_num)) {
                heapPQ.add(add_num, add_num);
                naivePQ.add(add_num, add_num);
            }
            assertTrue(heapPQ.contains(add_num));
            assertEquals(naivePQ.size(), heapPQ.size());
            assertEquals(naivePQ.getSmallest(), heapPQ.getSmallest(), 0.00000001);
        }
    }

    @Test
    public void Test_removeSmallest() {
        ArrayHeapMinPQ<Double> heapPQ = new ArrayHeapMinPQ<Double>();
        NaiveMinPQ<Double> naivePQ = new NaiveMinPQ<Double>();
        double add_num;

        for (int i = 0; i < 5000; i++) {
            add_num = StdRandom.uniform(-1000.0, 1000.0);
            if (!heapPQ.contains(add_num)) {
                heapPQ.add(add_num, add_num);
                naivePQ.add(add_num, add_num);
            }
        }
        for (int i = 1; i < heapPQ.size(); i++) {
//            heapPQ.removeSmallest();
            assertEquals(naivePQ.removeSmallest(),heapPQ.removeSmallest(), 0.00000001);
        }
    }

    @Test
    public void Test_changePriority() {
        ArrayHeapMinPQ<Double> heapPQ = new ArrayHeapMinPQ<Double>();
        NaiveMinPQ<Double> naivePQ = new NaiveMinPQ<Double>();
        double add_num;
        double change_num;

        for (int i = 0; i < 500; i++) {
            add_num = StdRandom.uniform(-1000.0, 1000.0);
            if (!heapPQ.contains(add_num)) {
                heapPQ.add(add_num, add_num);
                naivePQ.add(add_num, add_num);
            }
        }
        for (int i = 0; i < 5000; i++) {
            add_num = StdRandom.uniform(-1000.0, 1000.0);
            change_num = StdRandom.uniform(-2000.0, 2000.0);
            if (!heapPQ.contains(add_num)) {
                heapPQ.add(add_num, add_num);
                naivePQ.add(add_num, add_num);

                heapPQ.changePriority(add_num, change_num);
                naivePQ.changePriority(add_num, change_num);

                assertEquals(naivePQ.removeSmallest(),heapPQ.removeSmallest(), 0.00000001);
            }
        }
    }

    @Test
    public void Test_time() {
        ArrayHeapMinPQ<Double> heapPQ = new ArrayHeapMinPQ<Double>();
        NaiveMinPQ<Double> naivePQ = new NaiveMinPQ<Double>();
        DoubleMapPQ<Double> doublePQ = new DoubleMapPQ<Double>();
        double add_num;
        double change_num;

        Stopwatch sw = new Stopwatch();
//        for (int i = 0; i < 1000000; i++) {
//            add_num = StdRandom.uniform(-1000.0, 1000.0);
//            if (!naivePQ.contains(add_num)) {
//                naivePQ.add(add_num, add_num);
//            }
//        }
//        for (int i = 0; i < 1000; i++) {
//            add_num = StdRandom.uniform(-1000.0, 1000.0);
//            change_num = StdRandom.uniform(-1000.0, 1000.0);
//            if (!naivePQ.contains(add_num)) {
//                naivePQ.add(add_num, add_num);
//                naivePQ.changePriority(add_num, change_num);
//                naivePQ.removeSmallest();
//                naivePQ.getSmallest();
//                naivePQ.size();
//            }
//        }
//        System.out.println("naivePQ total time elapsed: " + sw.elapsedTime() +  " seconds.");

        sw = new Stopwatch();
        for (int i = 0; i < 1000000; i++) {
            add_num = StdRandom.uniform(-1000.0, 1000.0);
            if (!heapPQ.contains(add_num)) {
                heapPQ.add(add_num, add_num);
            }
        }
        for (int i = 0; i < 1000; i++) {
            add_num = StdRandom.uniform(-1000.0, 1000.0);
            change_num = StdRandom.uniform(-1000.0, 1000.0);
            if (!heapPQ.contains(add_num)) {
                heapPQ.add(add_num, add_num);
                heapPQ.changePriority(add_num, change_num);
                heapPQ.removeSmallest();
                heapPQ.getSmallest();
                heapPQ.size();
            }
        }
        System.out.println("heapPQ total time elapsed: " + sw.elapsedTime() +  " seconds.");


        sw = new Stopwatch();
        for (int i = 0; i < 1000000; i++) {
            add_num = StdRandom.uniform(-1000.0, 1000.0);
            if (!doublePQ.contains(add_num)) {
                doublePQ.add(add_num, add_num);
            }
        }
        for (int i = 0; i < 1000; i++) {
            add_num = StdRandom.uniform(-1000.0, 1000.0);
            change_num = StdRandom.uniform(-1000.0, 1000.0);
            if (!doublePQ.contains(add_num)) {
                doublePQ.add(add_num, add_num);
                doublePQ.changePriority(add_num, change_num);
                doublePQ.removeSmallest();
                doublePQ.getSmallest();
                doublePQ.size();
            }
        }
        System.out.println("doublePQ total time elapsed: " + sw.elapsedTime() +  " seconds.");
    }
}
