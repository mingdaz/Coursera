import java.util.Stack;

/**
 * Created by mingdzhang on 12/24/16.
 */
public class Board {
    public Board(int[][] blocks)  {        // construct a board from an n-by-n array of blocks
        _n = blocks.length;
        init_board = new char[_n][];
        int temp_h = 0;
        int temp_m = 0;
        int temp_i = 0;
        int temp_j = 0;
        int i,j,r,c;
        for(i = 0; i < _n ; i++){
            init_board[i] = new char[_n];
            for(j=0;j<_n;j++){
                init_board[i][j] = (char)blocks[i][j];
                if(blocks[i][j]==0){
                    temp_i = i;
                    temp_j = j;
                    continue;
                }
                if((i*_n+j+1)!=blocks[i][j]) temp_h++;
                r = (blocks[i][j]-1)/_n;
                c = (blocks[i][j]-1)%_n;
                temp_m = temp_m + Math.abs(r-i) + Math.abs(c-j);
            }
        }
        _hamming = temp_h;
        _manhattan = temp_m;
        blank_i = temp_i;
        blank_j = temp_j;
    }
    // (where blocks[i][j] = block in row i, column j)
    public int dimension() {                 // board dimension n
        return _n;
    }
    public int hamming() {                   // number of blocks out of place
        return _hamming;
    }
    public int manhattan(){ // sum of Manhattan distances between blocks and goal
        return _manhattan;
    }
    public boolean isGoal() {              // is this board the goal board?
        return _hamming == 0;
    }
    public Board twin() {                // a board that is obtained by exchanging any pair of blocks
        int[][] block = new int[_n][];
        for(int i= 0; i< _n;i++){
            block[i] = new int[_n];
            for(int j=0; j < _n; j++)
                block[i][j] = init_board[i][j];
        }
        if(block[0][0]==0) exchange(0,1,1,0,block);
        else if(block[0][1]==0) exchange(0,0,1,0,block);
        else exchange(0,0,0,1,block);
        return new Board(block);
    }

    private void exchange(int r1,int c1,int r2, int c2, int[][] block){
        int temp = block[r1][c1];
        block[r1][c1] = block[r2][c2];
        block[r2][c2] = temp;
    }

    public boolean equals(Object y){// does this board equal y?
        if ( y == this )
            return true;
        if ( y == null )
            return false;
        if ( y.getClass() != this.getClass())
            return false;
        Board that = (Board) y;
        if( this._n != that._n ) return false;
        for( int i=0; i < _n; i++)
            for(int j=0; j < _n; j++)
                if(this.init_board[i][j] != that.init_board[i][j])
                    return false;
        return true;
    }

    public Iterable<Board> neighbors(){  // all neighboring boards
        Stack<Board> S = new Stack<>();
        int[][] block = new int[_n][];
        for(int i= 0; i< _n;i++){
            block[i] = new int[_n];
            for(int j=0; j < _n; j++)
                block[i][j] = init_board[i][j];
        }
        if(blank_i>0){
            exchange(blank_i,blank_j,blank_i-1,blank_j,block);
            S.push(new Board(block));
            exchange(blank_i,blank_j,blank_i-1,blank_j,block);
        }
        if(blank_j>0){
            exchange(blank_i,blank_j,blank_i,blank_j-1,block);
            S.push(new Board(block));
            exchange(blank_i,blank_j,blank_i,blank_j-1,block);
        }
        if(blank_i<_n-1){
            exchange(blank_i,blank_j,blank_i+1,blank_j,block);
            S.push(new Board(block));
            exchange(blank_i,blank_j,blank_i+1,blank_j,block);
        }
        if(blank_j<_n-1){
            exchange(blank_i,blank_j,blank_i,blank_j+1,block);
            S.push(new Board(block));
            exchange(blank_i,blank_j,blank_i,blank_j+1,block);
        }
        return S;
    }


    public String toString() {// string representation of this board (in the output format specified below)
        StringBuilder s = new StringBuilder();
        s.append(_n + "\n");
        for (int i = 0; i < _n; i++) {
            for (int j = 0; j < _n; j++) {
                s.append(String.format("%2d ", (int)init_board[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    private final char[][] init_board;
    private final int _n;
    private final int _hamming;
    private final int _manhattan;
    private final int blank_i;
    private final int blank_j;

    public static void main(String[] args){


    } // unit tests (not graded)
}