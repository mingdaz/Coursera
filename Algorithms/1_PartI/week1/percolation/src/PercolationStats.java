/**
 * Created by mingdzhang on 12/2/16.
 */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

import java.lang.*;

public class PercolationStats {
    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1) throw new IllegalArgumentException();
        double[] x = new double[trials];
        double tot = n * n;
        double open_cnt;

        for (int i = 0; i < trials; i++) {
            Percolation Temp = new Percolation(n);
            open_cnt = 0.0;
            while (!Temp.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                if (!Temp.isOpen(row, col)) {
                    open_cnt++;
                    Temp.open(row, col);
                }
            }
            x[i] = open_cnt / tot;
        }

        _mean = StdStats.mean(x);
        _stddev = StdStats.stddev(x);
        double bar = 1.96 * _stddev / Math.sqrt(trials);
        _confidenceLo = _mean - bar;
        _confidenceHi = _mean + bar;
    }

    // sample mean of percolation threshold
    public double mean() {
        return _mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return _stddev;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return _confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return _confidenceHi;
    }

    private double _mean;
    private double _stddev;
    private double _confidenceLo;
    private double _confidenceHi;

    // test client (described below)
    public static void main(String[] args) {
        PercolationStats A = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
//        PercolationStats A = new PercolationStats(2,100);
        StdOut.printf("mean                    = %f\n", A.mean());
        StdOut.printf("stddev                  = %f\n", A.stddev());
        StdOut.printf("95%% confidence interval = %f, %f\n", A.confidenceLo(), A.confidenceHi());
    }
}
