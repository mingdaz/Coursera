/**
 * Created by mingdzhang on 12/2/16.
 */
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if(n<1) throw new java.lang.IllegalArgumentException();
        int i,j,k;
        range = n;
        bottom = n*n+1;
        // Create UF private menber
        UF = new WeightedQuickUnionUF(n*n+2);
        // create block array
        block = new boolean[n*n];
        //Initial block
        for(i=0;i<n*n;i++){
            block[i]=false;
        }

    }

    // open site (row, col) if it is not open already
    public void open(int row, int col){
        if(notValid(row,col)) throw new java.lang.IndexOutOfBoundsException();
        if(!isOpen(row,col)){
            block[ (row-1) * range + (col-1) ]=true;
            joinNeighbour(row,col);
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        if(notValid(row,col)) throw new java.lang.IndexOutOfBoundsException();
        return block[(row-1)*range+col-1];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        if(notValid(row,col)) throw new java.lang.IndexOutOfBoundsException();
        return UF.connected(0,(row-1)*range+col-1);
    }

    // does the system percolate?
    public boolean percolates(){
        return UF.connected(0,bottom);
    }

    private boolean notValid(int row,int col){
        return (row<1||row>range||col<1||col>range);
    }

    private void joinNeighbour(int row,int col){
        joinLeft(row,col);
        joinRight(row,col);
        joinUp(row,col);
        joinDown(row,col);
    }

    private void joinLeft(int row,int col){
        if(notValid(row,col-1) || !isOpen(row,col-1)) return;
        int cur = (row-1)*range+col-1;
        UF.union(cur,cur-1);
    }

    private void joinRight(int row,int col){
        if(notValid(row,col+1) || !isOpen(row,col+1)) return;
        int cur = (row-1)*range+col-1;
        UF.union(cur,cur+1);
    }

    private void joinUp(int row,int col){
        int cur = (row-1)*range+col-1;
        if(row==1) UF.union(0,cur);
        if(notValid(row-1,col) || !isOpen(row-1,col)) return;
        UF.union(cur,cur-range);
    }

    private void joinDown(int row,int col){
        int cur = (row-1)*range+col-1;
        if(row==range) UF.union(bottom,cur);
        if(notValid(row+1,col) || !isOpen(row+1,col)) return;
        UF.union(cur,cur+range);
    }

    private WeightedQuickUnionUF UF;
    private boolean [] block;
    private int range;
    private int bottom;
    // test client (optional)
    public static void main(String[] args){

    }
}