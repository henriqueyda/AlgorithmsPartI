import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] fraction;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        if(n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        fraction = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int rand1 = StdRandom.uniform(1, n + 1);
                int rand2 = StdRandom.uniform(1, n + 1);
                percolation.open(rand1, rand2);
            }
            fraction[i] = ((double) percolation.numberOfOpenSites()) / (n * n);
        }

    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(this.fraction);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(this.fraction);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return this.mean() - 1.96*this.stddev()/Math.sqrt(this.fraction.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return this.mean() + 1.96*this.stddev()/Math.sqrt(this.fraction.length);
    }

    // test client (see below)
    public static void main(String[] args){
        PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("mean                    = " + percolationStats.mean());
        System.out.println("stddev                  = " + percolationStats.stddev());
        System.out.println("95% confidence interval = ["+ percolationStats.confidenceLo() +", "+percolationStats.confidenceHi()+"]");
    }

}