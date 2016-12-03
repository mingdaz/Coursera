/**
 * Created by mingdzhang on 12/2/16.
 */
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if(n<1) throw new java.lang.IllegalArgumentException();
        int i;
        range = n;
        bottom = n*n+1;
        // Create UF private menber
        UF = new WeightedQuickUnionUF(n*n+2);
        // create block array
        block = new boolean[n*n];
        linkBottom = new boolean[n+1];
        //Initial block
        for(i=0;i<n*n;i++){
            block[i]=false;
        }
        for(i=0;i<=n;i++){
            linkBottom[i]=false;
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
        if(!isOpen(row,col)) return false;
        return UF.connected(0,(row-1)*range+col);
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
        checkbottom(row,col);
    }

    private boolean joinLeft(int row,int col){
        if(notValid(row,col-1) || !isOpen(row,col-1)) return false;
        int cur = (row-1)*range+col;
        UF.union(cur,cur-1);
        return true;
    }

    private boolean joinRight(int row,int col){
        if(notValid(row,col+1) || !isOpen(row,col+1)) return false;
        int cur = (row-1)*range+col;
        UF.union(cur,cur+1);
        return true;
    }

    private boolean joinUp(int row,int col){
        int cur = (row-1)*range+col;
        if(row==1) UF.union(0,cur);
        if(notValid(row-1,col) || !isOpen(row-1,col)) return false;
        UF.union(cur,cur-range);
        return true;
    }

    private boolean joinDown(int row,int col){
        int cur = (row-1)*range+col;
        if(notValid(row+1,col) || !isOpen(row+1,col)) return false;
        UF.union(cur,cur+range);
        return true;
    }

    private void checkbottom(int row,int col){
        if(!isFull(row,col)) return;
        int base = range*(range-1);
        for(int i=1;i<=range;i++){
            if(isFull(range,i)&&!linkBottom[i]){
                UF.union(bottom,base+i);
                linkBottom[i]=true;
            }
        }
    }

    private WeightedQuickUnionUF UF;
    private boolean [] block;
    private boolean [] linkBottom;
    private int range;
    private int bottom;
    // test client (optional)
    public static void main(String[] args){

    }
}