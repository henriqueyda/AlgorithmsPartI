import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class copia {
    private WeightedQuickUnionUF weightedQuickUnion;
    private WeightedQuickUnionUF weightedQuickUnionPercolates;
    private String[][] grid;
    private int numOpen = 0;
    // creates n-by-n grid, with all sites initially blocked
    public copia(int n){
        if(n <= 0) {
            throw new IllegalArgumentException();
        }
        weightedQuickUnion = new WeightedQuickUnionUF(n*n + 2);
        weightedQuickUnionPercolates = new WeightedQuickUnionUF(n*n + 2);
        grid = new String[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                grid[i][j] = "blocked";
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        int n = this.grid.length;
        if(row < 1 || col < 1 || row > n || col > n){
            throw new IllegalArgumentException();
        }
        if (this.grid[row-1][col-1].equals("blocked")) {
            this.numOpen++;
            this.grid[row - 1][col - 1] = "open";
            if (row == 1){
                weightedQuickUnion.union((row - 1) * n + (col - 1) + 1, 0);
                weightedQuickUnionPercolates.union((row - 1) * n + (col - 1) + 1, 0);
            }
            if(row == n){
                weightedQuickUnionPercolates.union((row - 1) * n + (col - 1) + 1, n * n + 1);
            }
            if (row > 1 && this.isOpen(row - 1, col)) {
                weightedQuickUnion.union((row - 2) * n + (col - 1) + 1, (row - 1) * n + (col - 1) + 1);
                weightedQuickUnionPercolates.union((row - 2) * n + (col - 1) + 1, (row - 1) * n + (col - 1) + 1);
            }
            if (row < n && this.isOpen(row + 1, col)) {
                weightedQuickUnion.union(row * n + (col - 1) + 1, (row - 1) * n + (col - 1) + 1);
                weightedQuickUnionPercolates.union(row * n + (col - 1) + 1, (row - 1) * n + (col - 1) + 1);
            }
            if (col > 1 && this.isOpen(row, col - 1)) {
                weightedQuickUnion.union((row - 1)* n + (col - 2) + 1, (row - 1) * n + (col - 1) + 1);
                weightedQuickUnionPercolates.union((row - 1)* n + (col - 2) + 1, (row - 1) * n + (col - 1) + 1);
            }
            if (col < n && this.isOpen(row, col + 1)) {
                weightedQuickUnion.union((row - 1) * n + (col) + 1, (row - 1) * n + (col - 1) + 1);
                weightedQuickUnionPercolates.union((row - 1) * n + (col) + 1, (row - 1) * n + (col - 1) + 1);
            }
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        int n = this.grid.length;
        if(row < 1 || col < 1 || row > n || col > n){
            throw new IllegalArgumentException();
        }
        return this.grid[row-1][col-1].equals("open");
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        int n = this.grid.length;
        if(row < 1 || col < 1 || row > n || col > n){
            throw new IllegalArgumentException();
        }
        return weightedQuickUnion.find(0) == weightedQuickUnion.find((row-1)*n + (col - 1) + 1);
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return this.numOpen;
    }

    // does the system percolate?
    public boolean percolates(){
        int n = this.grid.length;
        return weightedQuickUnionPercolates.find(0) == weightedQuickUnionPercolates.find(n*n + 1);
    }

    // test client (optional)
    public static void main(String[] args){
        Percolation percolation = new Percolation(3);
        percolation.open(1,3);
        percolation.open(2,3);
        percolation.open(3,3);
        percolation.open(3,1);
        percolation.open(2,1);
        System.out.println(percolation.isOpen(2,1));
        System.out.println(percolation.isFull(2,1));
        System.out.println(percolation.percolates());
        System.out.println(percolation.numberOfOpenSites());
    }
}

