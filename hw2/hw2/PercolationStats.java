package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {

    private int[] xt;
    private Percolation per;
    private int num;
    private int times;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 | T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }

        xt = new int[T];
        num = N;
        times = T;

        for (int i = 0; i < T; i++) {
            per = pf.make(N);
            while (!per.percolates()) {
                per.open(StdRandom.uniform(N),StdRandom.uniform(N));
            }
            xt[i] = per.numberOfOpenSites();
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(xt);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(xt);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - 1.96*stddev() / Math.sqrt(times);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96*stddev() / Math.sqrt(times);
    }



    public static void main(String[] args) {
        int N = 500;
        PercolationFactory pf = new PercolationFactory();
        PercolationStats p = new PercolationStats(N,500,pf);
        System.out.println(p.mean()/Math.pow(N,2));
    }
}
