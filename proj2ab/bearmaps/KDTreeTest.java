package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {

    @Test
    public void TestConstructor() {
        Point pa = new Point(2, 3);
        Point pz = new Point(4, 2);
        Point pb = new Point(4, 2);
        Point pc = new Point(4, 5);
        Point pd = new Point(3, 3);
        Point pe = new Point(1, 5);
        Point pf = new Point(4, 4);

        KDTree nn = new KDTree(List.of(pa, pz, pb, pc, pd, pe, pf));
    }


    @Test
    public void TestRandomNearest() {
        List<Point> points = new ArrayList<Point>();
        double x = 0;
        double y = 0;

        for (int i = 0; i < 5000; i++) {
            Point pt = new Point(StdRandom.uniform(-1000.0, 1000.0), StdRandom.uniform(-1000.0, 1000.0));
            points.add(pt);
        }
        NaivePointSet nn = new NaivePointSet(points);
        KDTree kd = new KDTree(points);

        for (int i = 0; i < 500; i++) {
            x = StdRandom.uniform(-1000.0, 1000.0);
            y = StdRandom.uniform(-1000.0, 1000.0);
            Point xy = new Point(x,y);
            Point retnn = nn.nearest(x, y);
            Point retkd = kd.nearest(x, y);
            assertEquals(Point.distance(retnn, xy), Point.distance(retkd, xy), 0.00000001);
        }
    }






    private static void printTimingTable(List<Integer> Ns, List<Double> times, List<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    @Test
    public void timeKDTreeConstruction() {
        List<Integer> Ns = new ArrayList<Integer>();
        List<Double> times = new ArrayList<Double>();
        List<Integer> opCounts = new ArrayList<Integer>();

        List<Point> points = new ArrayList<Point>();


        for (int i = 0; i < 1000000; i++) {
            Point pt = new Point(StdRandom.uniform(-1000.0, 1000.0), StdRandom.uniform(-1000.0, 1000.0));
            points.add(pt);

            if (i == 31250 - 1 | i == 62500 - 1 | i == 125000 - 1 | i == 250000 - 1 | i == 500000 - 1 | i == 1000000 - 1) {
                Stopwatch sw = new Stopwatch();

                KDTree kd = new KDTree(points);

                Ns.add(i + 1);
                times.add(sw.elapsedTime());
                opCounts.add(i + 1);
            }
        }

        printTimingTable(Ns, times, opCounts);
    }

    @Test
    public void timeNaivePointSetNearest() {
        List<Integer> Ns = new ArrayList<Integer>();
        List<Double> times = new ArrayList<Double>();
        List<Integer> opCounts = new ArrayList<Integer>();

        List<Point> points = new ArrayList<Point>();
        double x = 0;
        double y = 0;

        for (int i = 0; i < 1000; i++) {
            Point pt = new Point(StdRandom.uniform(-1000.0, 1000.0), StdRandom.uniform(-1000.0, 1000.0));
            points.add(pt);

            if (i == 125 - 1 | i == 250 - 1 | i == 500 - 1 | i == 1000 - 1) {
                NaivePointSet nn = new NaivePointSet(points);

                Stopwatch sw = new Stopwatch();
                for (int j = 0; j < 1000000; j++) {
                    x = StdRandom.uniform(-1000.0, 1000.0);
                    y = StdRandom.uniform(-1000.0, 1000.0);
                    Point xy = new Point(x,y);
                    Point retnn = nn.nearest(x, y);
                }
                Ns.add(i + 1);
                times.add(sw.elapsedTime());
                opCounts.add(1000000);
            }
        }

        printTimingTable(Ns, times, opCounts);
    }


    @Test
    public void timeKDTreeNearest() {
        List<Integer> Ns = new ArrayList<Integer>();
        List<Double> times = new ArrayList<Double>();
        List<Integer> opCounts = new ArrayList<Integer>();

        List<Point> points = new ArrayList<Point>();
        double x = 0;
        double y = 0;

        for (int i = 0; i < 1000000; i++) {
            Point pt = new Point(StdRandom.uniform(-1000.0, 1000.0), StdRandom.uniform(-1000.0, 1000.0));
            points.add(pt);

            if (i == 31250 - 1 | i == 62500 - 1 | i == 125000 - 1 | i == 250000 - 1 | i == 500000 - 1 | i == 1000000 - 1) {
                KDTree kd = new KDTree(points);

                Stopwatch sw = new Stopwatch();
                for (int j = 0; j < 1000000; j++) {
                    x = StdRandom.uniform(-1000.0, 1000.0);
                    y = StdRandom.uniform(-1000.0, 1000.0);
                    Point xy = new Point(x,y);
                    Point retnn = kd.nearest(x, y);
                }
                Ns.add(i + 1);
                times.add(sw.elapsedTime());
                opCounts.add(1000000);
            }
        }

        printTimingTable(Ns, times, opCounts);
    }

}
