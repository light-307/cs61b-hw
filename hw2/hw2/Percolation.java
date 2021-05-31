package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] sites;
    private WeightedQuickUnionUF Disjoint1;
    private WeightedQuickUnionUF Disjoint2;
    private int open_cont;
    private int num;
    private boolean flag_percolates;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }

        sites = new boolean[N][N];
        Disjoint1 = new WeightedQuickUnionUF(N*N + 1);
        Disjoint2 = new WeightedQuickUnionUF(N*N + 1);
        open_cont = 0;
        num = N;
        flag_percolates = false;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sites[i][j] = false;
            }
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!exist(row,col)) {
            throw new java.lang.IndexOutOfBoundsException();
        }

        if (!isOpen(row,col)) {
            sites[row][col] = true;
            open_cont++;

            if (row == 0) {
                Disjoint1.union(xyTo1D(row,col), num*num);
            }
            if (row == num - 1) {
                Disjoint2.union(xyTo1D(row,col), num*num);
            }

            if (exist(row, col-1) && isOpen(row, col-1)) {
                Disjoint1.union(xyTo1D(row,col), xyTo1D(row,col-1));
                Disjoint2.union(xyTo1D(row,col), xyTo1D(row,col-1));
            }
            if (exist(row, col+1) && isOpen(row, col+1)) {
                Disjoint1.union(xyTo1D(row,col), xyTo1D(row,col+1));
                Disjoint2.union(xyTo1D(row,col), xyTo1D(row,col+1));
            }
            if (exist(row-1, col) && isOpen(row-1, col)) {
                Disjoint1.union(xyTo1D(row,col), xyTo1D(row-1,col));
                Disjoint2.union(xyTo1D(row,col), xyTo1D(row-1,col));
            }
            if (exist(row+1, col) && isOpen(row+1, col)) {
                Disjoint1.union(xyTo1D(row,col), xyTo1D(row+1,col));
                Disjoint2.union(xyTo1D(row,col), xyTo1D(row+1,col));
            }

            if (Disjoint1.connected(xyTo1D(row,col), num*num) & Disjoint2.connected(xyTo1D(row,col), num*num)) {
                flag_percolates = true;
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!exist(row,col)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return sites[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!exist(row,col)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return Disjoint1.connected(xyTo1D(row,col), num*num);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return open_cont;
    }

    // does the system percolate?
    public boolean percolates() {
        return flag_percolates;
    }

    private int xyTo1D(int x, int y) {
        return x*num + y;
    }

    private boolean exist(int row, int col) {
        return row >= 0 & row < num & col >= 0 & col < num;
    }



    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args) {
        //TODO
        int agd = 2;
        double sdf;

        sdf = Math.sqrt(agd);
    }
}
