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
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] a;
    private final WeightedQuickUnionUF uf;
    private final int n;
    private int num = 0;
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        a = new boolean[n + 1][n + 1]; // create a two-dimensional array to store the graph
        uf = new WeightedQuickUnionUF(n * n + 2); // create a WeightedQuickUnionUF whose size is more than the gragh
        for (int i = 1; i < n + 1; i++) {
            for (int j  = 1; j < n + 1; j++) {
                a[i][j] = false;
            }
            if (n > 1) {
                uf.union(i, 0); // assume there is a node on the top of the graph connected with first row of nodes
                uf.union((n - 1) * n + i, n * n + 1); // assume there is a node on the bottom of the graph connected with last row of nodes
            }

        }
    }               // create n-by-n grid, with all sites blocked
    private boolean isInGrid(int row, int col) {
        //        if ((row < 0 || row >= n )||( col < 0 || col >= n)) {
        //            return false;
        //        }
        //        else
        //            return true;
        return row >= 1 && row < n + 1 && col >= 1 && col < n + 1;
    }
    public void open(int row, int col) {
        if (isInGrid(row, col)) {
            if (!a[row][col]) {
                a[row][col] = true;
                num++;
            }
            if (isInGrid(row -1, col)) { // if the node upon this node is open, union two nodes
                if (a[row - 1][col]) {
                    uf.union((row - 1) * n + col, (row - 2) * n + col);
                }
            }
            if (isInGrid(row, col - 1)) { // if the node on this node's left is open, union two nodes 
                if (a[row][col - 1]) {
                    uf.union((row - 1) * n + col, (row - 1) * n+ col - 1);
                }
            }
            if (isInGrid(row + 1, col)) { // if the node below this node is open, union two nodes 
                if (a[row + 1][col]) {
                    uf.union(row * n + col, (row - 1) * n + col);
                }
            }
            if (isInGrid(row, col + 1)) { // if the node on this node's right is open, union two nodes 
                if (a[row][col + 1]) {
                    uf.union((row - 1) * n + col, (row - 1) * n + col + 1);
                }
            }
        }
        else {
            throw new IllegalArgumentException();
        }

    }   // open site (row, col) if it is not open already
    public boolean isOpen(int row, int col) {
        if (isInGrid(row, col)) {
            return a[row][col];
        }
        else {
            throw new IllegalArgumentException();
        }
    } // is site (row, col) open?
    public boolean isFull(int row, int col) {
        //        for (int i = 0; i < n; i++) {
        //            if (isOpen(row, col) && uf.connected(i, row * n + col)) {
        //                return true;
        //            }
        //        }
        //        return false;
        if (isInGrid(row, col)) {
            if (n == 1) {
                return a[1][1];
            }
            return isOpen(row, col) && uf.connected(0, (row - 1) * n + col); //use the definition
        }
        else {
            throw new IllegalArgumentException();
        }
    }  // is site (row, col) full?
    public int numberOfOpenSites() {
        return num;
    }      // number of open sites
    public boolean percolates() {
        //        for (int i = 0; i < n; i++) {
        //            for (int j = 0; j < n; j++) {
        //                if (uf.connected(i, (n - 1) * n + j)) {
        //                    return true;
        //                }
        //            }
        //        }
        //        return false;
        if (n == 1) {
            return a[1][1];
        }
        return uf.connected(0, n * n + 1);
    }             // does the system percolate?

    public static void main(String[] args) {
        Percolation p = new Percolation(2);

        p.open(2, 2);
        //        p.open(3, 1);
        //        p.open(6, 1);
        //        p.open(7, 1);
        //        p.open(7, 2);
        //        p.open(7, 4);
        //        p.open(1, 1);
        //        p.open(1, 5);
        //        p.open(2, 5);

        System.out.println(p.isFull(1, 1));
        //        System.out.println(p.isOpen(7, 3));
        //        System.out.println(p.isFull(7, 3));
        System.out.println(p.numberOfOpenSites());
        System.out.println(p.percolates());
    }  // test client (optional)
}