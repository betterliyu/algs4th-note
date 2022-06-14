/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    int total = 2;
    int size = 0;
    boolean[] opened = null;
    int openedCount = 0;
    WeightedQuickUnionUF grid = null;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        size = n;
        total = n * n + 2;
        opened = new boolean[n * n + 1];
        grid = new WeightedQuickUnionUF(total);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size) {
            throw new IllegalArgumentException();
        }
        openedCount++;
        int currentIndex = (row - 1) * size + col;
        opened[currentIndex] = true;
        if (col != 1 && opened[currentIndex - 1]) {
            // union left
            grid.union(currentIndex, currentIndex - 1);
        }
        if (col != size && opened[currentIndex + 1]) {
            // union left
            grid.union(currentIndex, currentIndex + 1);
        }

        if (row == 1) {
            // union above
            grid.union(currentIndex, 0);
        }
        else {
            // union above
            grid.union(currentIndex, currentIndex - size);
        }
        if (row == size) {
            // union below
            grid.union(currentIndex, total);
        }
        else {
            // union below
            grid.union(currentIndex, currentIndex + size);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size) {
            throw new IllegalArgumentException();
        }
        int currentIndex = (row - 1) * size + col;
        return opened[currentIndex];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size) {
            throw new IllegalArgumentException();
        }
        int currentIndex = (row - 1) * size + col;
        return  grid.find(currentIndex) == grid.find(0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openedCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return grid.find(0) == grid.find(total);
    }

    // test client (optional)
    public static void main(String[] args) {
    }

}