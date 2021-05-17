import java.util.ArrayList;
import java.util.List;

/**
 * Class that collects timing information about SLList getLast method.
 */
public class TimeSLList {
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

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        List<Integer> Ns = new ArrayList<Integer>();
        List<Double> times = new ArrayList<Double>();
        List<Integer> opCounts = new ArrayList<Integer>();

        int[] a = new int[]{1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000};

        for (int k : a) {
            SLList<Integer> test = new SLList<Integer>();
            for (int i = 0; i < k; i++) {
                test.addLast(1);
            }
            Stopwatch sw = new Stopwatch();

            for (int i = 0; i < 10000; i++) {
                test.getLast();
            }

            Ns.add(k);
            times.add(sw.elapsedTime());
            opCounts.add(10000);
        }


        printTimingTable(Ns, times, opCounts);
    }

}
