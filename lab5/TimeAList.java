import java.util.ArrayList;
import java.util.List;

/**
 * Class that collects timing information about AList construction.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        List<Integer> Ns = new ArrayList<Integer>();
        List<Double> times = new ArrayList<Double>();
        List<Integer> opCounts = new ArrayList<Integer>();

        AList<Integer> test = new AList<Integer>();

        Stopwatch sw = new Stopwatch();
        for (int i = 0; i < 128000; i++) {
            test.addLast(1);

            if (i == 1000 - 1 | i == 2000 - 1 | i == 4000 - 1 | i == 8000 - 1 | i == 16000 - 1 | i == 32000 - 1 | i == 64000 - 1 | i == 128000 - 1) {
                Ns.add(i + 1);
                times.add(sw.elapsedTime());
                opCounts.add(i + 1);
            }
        }

        printTimingTable(Ns, times, opCounts);
    }


}
