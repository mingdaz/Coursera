import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by mingdzhang on 12/24/16.
 */
public class Solver {
    public Solver(Board initial){
        Node GameTree = new Node(initial);
        Node TwinTree = new Node(initial.twin());

        MinPQ<Node> Q = new MinPQ<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.getPriority() - o2.getPriority();
            }
        });

        MinPQ<Node> twinQ = new MinPQ<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.getPriority() - o2.getPriority();
            }
        });

        Q.insert(GameTree);
        twinQ.insert(TwinTree);

        boolean Sol = false;
        boolean twinSol = false;

        Node temp=null;

        while( !Sol && !twinSol ){
            // deque first element
            temp = Q.delMin();
            // if it is already the goal return
            if(temp.data.isGoal()){
                Sol = true;
                break;
            }

            for(Board b:temp.data.neighbors()){
                // if neighbor in Game tree do not add it to Queue
                if(temp.parent!=null&&temp.parent.equals(b)) continue;
                // otherwise create a new node for it
                Node child = new Node(b);
                // set parent pointer
                child.setParent(temp);
                // add this node to game tree
                Q.insert(child);
            }


            // do the same for twin Game
            temp = twinQ.delMin();
            if(temp.data.isGoal()){
                twinSol = true;
                break;
            }

            for(Board b:temp.data.neighbors()){
                if(temp.parent!= null&&temp.parent.equals(b)) continue;
                Node child = new Node(b);
                child.setParent(temp);
                twinQ.insert(child);
            }

        }

        if(Sol){
            _isSolvable = true;
            _moves = temp.move;
            _solution = new Stack<Board>();
            while(temp!=null){
                _solution.push(temp.data);
                temp = temp.parent;
            }
        }
        else{
            _isSolvable = false;
            _moves = -1;
        }

    }           // find a solution to the initial board (using the A* algorithm)

    public boolean isSolvable(){
        return _isSolvable;
    }            // is the initial board solvable?

    public int moves(){
        return _moves;
    }                     // min number of moves to solve initial board; -1 if unsolvable

    public Iterable<Board> solution(){
        return _solution;
    }      // sequence of boards in a shortest solution; null if unsolvable


    private class Node {
        private Node(Board item){
            data = item;
            parent = null;
            priority = data.manhattan();
            move = 0;
        }
        private Board data;
        private Node parent;
        private int move;
        private int priority;

        private void setParent(Node parent){
            this.parent = parent;
            setMove(parent.getMove()+1);
        }
        private void setMove(int move){
            this.move = move;
            setPriority();
        }
        private int getMove(){
            return move;
        }
        private void setPriority(){
            priority = move + data.manhattan();
        }
        private int getPriority(){
            return priority;
        }
    }



    private boolean _isSolvable;
    private int _moves;
    private Stack<Board> _solution;


    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }// solve a slider puzzle (given below)
}