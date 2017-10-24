/******************************************************************************
 *  Name:    Jiayi 
 *  NetID:   jiayi
 *  Precept: P01
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 * 
 *  Description:  Model an n-by-n percolation system using the union-find
 *                data structure.
 ******************************************************************************/
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final int[] x;
    private final int n;
    private final int trials;
    public PercolationStats(int n, int trials) {
        this.n = n;
        this.trials = trials;
        x = new int[trials];
        for (int t = 0; t < trials; t++) {
            Percolation system = new Percolation(n);
            for (int i = 0; i < n * n; i++) {

                int p1 = StdRandom.uniform(1, n + 1); // open a node at a random place
                int p2 = StdRandom.uniform(1, n + 1);
                system.open(p1, p2);
                x[t] = system.numberOfOpenSites();
                if (system.percolates()) {
                    // x[t] = system.numberOfOpenSites();
                    // System.out.println(x[t]);
                    break;
                }
                // System.out.println(x[t]);
            }
        }


    }   // perform trials independent experiments on an n-by-n grid
    public double mean() {
        return StdStats.mean(x)/(n * n);
    }                          // sample mean of percolation threshold
    public double stddev() {
        return StdStats.stddev(x)/(n * n);
    }                        // sample standard deviation of percolation threshold
    public double confidenceLo() {
        double mean = mean();
        return (mean - 1.96 / Math.sqrt(trials));
    }                  // low  endpoint of 95% confidence interval
    public double confidenceHi() {
        double mean = mean();
        return (mean + 1.96 / Math.sqrt(trials));
    }             // high endpoint of 95% confidence interval

    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(2, 100);
        for (int i = 0; i < ps.trials; i++) {
            System.out.println(ps.x[i]);
        }
        System.out.println("mean" + ps.mean());
        System.out.println("stddev" + ps.stddev());
        System.out.println("95% confidence interval= [" + 
                ps.confidenceLo() + "," +ps.confidenceHi() + "]");
    }        // test client (described below)
}