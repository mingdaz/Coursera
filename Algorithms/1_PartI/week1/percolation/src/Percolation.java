/**
 * Created by mingdzhang on 12/2/16.
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n < 1) throw new java.lang.IllegalArgumentException();
        int i;
        range = n;
        // Create UF private menber
        UF = new WeightedQuickUnionUF(n * n);
        // create block array
        _open = new boolean[n * n];
        _down = new boolean[n * n];
        _up = new boolean[n * n];
        // 0    0    0
        // up   down open
        //Initial block
        for (i = 0; i < n * n; i++) {
            _open[i] = false;
            _down[i] = false;
            _up[i] = false;
        }
        isPercolates = false;

    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        if (notValid(row, col)) throw new java.lang.IndexOutOfBoundsException();
        if (!isOpen(row, col)) {
            _open[(row - 1) * range + (col - 1)] = true;
            joinNeighbour(row, col);
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (notValid(row, col)) throw new java.lang.IndexOutOfBoundsException();
        return _open[(row - 1) * range + col - 1];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        if (notValid(row, col)) throw new java.lang.IndexOutOfBoundsException();
        if (!isOpen(row, col)) return false;
        int p = UF.find((row - 1) * range + col - 1);
        return _up[p];
    }

    // does the system percolate?
    public boolean percolates() {
        return isPercolates;
    }

    private boolean notValid(int row, int col) {
        return (row < 1 || row > range || col < 1 || col > range);
    }

    private void joinNeighbour(int row, int col) {
        int leftParent = joinLeft(row, col);
        int rightParent = joinRight(row, col);
        int upParent = joinUp(row, col);
        int downParent = joinDown(row, col);
        int curParent = UF.find((row-1)*range +col-1);
        setStatus(curParent,leftParent,rightParent,upParent,downParent);
        if(!isPercolates&&_down[curParent]&&_up[curParent]) isPercolates = true;
    }

    private int joinLeft(int row, int col) {
        if (notValid(row, col - 1) || !isOpen(row, col - 1)) return 0;
        int cur = (row - 1) * range + col-1;
        int p = UF.find(cur-1);
        UF.union(cur, cur - 1);
        return getStatus(p);
    }

    private int joinRight(int row, int col) {
        if (notValid(row, col + 1) || !isOpen(row, col + 1)) return 0;
        int cur = (row - 1) * range + col-1;
        int p = UF.find(cur+1);
        UF.union(cur, cur + 1);
        return getStatus(p);
    }

    private int joinUp(int row, int col) {
        int cur = (row - 1) * range + col - 1;
        if (row == 1){
            _up[cur] = true;
            return 5;
        }
        if (notValid(row - 1, col) || !isOpen(row - 1, col)) return 0;
        int p = UF.find(cur-range);
        UF.union(cur, cur - range);
        return getStatus(p);
    }

    private int joinDown(int row, int col) {
        int cur = (row - 1) * range + col-1;
        if( row == range ){
            _down[cur] = true;
            return 3;
        }
        if (notValid(row + 1, col) || !isOpen(row + 1, col)) return 0;
        int p = UF.find(cur+range);
        UF.union(cur, cur + range);
        return getStatus(p);
    }

    private int getStatus(int p){
        return (_open[p]?1:0) | (_down[p]?1:0)<<1 | (_up[p]?1:0)<<2;
    }

    private void setStatus(int p,int a,int b,int c,int d) {
        int status = getStatus(p)|a|b|c|d;
        _up[p] = (status&4)==4;
        _down[p] = (status&2)==2;
    }
    private WeightedQuickUnionUF UF;
    private boolean [] _open;
    private boolean [] _up;
    private boolean [] _down;
    private int range;
    private boolean isPercolates;

    // test client (optional)
    public static void main(String[] args) {
        In in = new In("/Users/mingdzhang/Documents/coursera/Algorithms/1_PartI/week1/percolation/resource/input8.txt");      // input file
        int n = in.readInt();         // n-by-n percolation system


        // repeatedly read in sites to open and draw resulting system
        Percolation perc = new Percolation(n);
        int open = 0;
        while (!in.isEmpty()) {
            if(++open==23) {
                StdOut.println("23");
            }
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
        }
    }
}