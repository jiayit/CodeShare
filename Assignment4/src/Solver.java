/******************************************************************************
 *  Name:    Jiayi 
 *  NetID:   jiayi
 *  Precept: P04
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 * 
 *  Description:  8-Puzzle Solution
 ******************************************************************************/
import java.util.Comparator;
import java.util.Iterator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private Board board;
    private MinPQ<Board> minPQ;
    private MinPQ<Board> minPQt;
    public Solver(Board initial) {
        board = initial; 
        minPQ = new MinPQ<Board>(new BoardComparator());
        minPQ.insert(board);
    }          // find a solution to the initial board (using the A* algorithm)
    public boolean isSolvable() {
        Board tBoard = board.twin();
        minPQt = new MinPQ<Board>(new BoardComparator());
        minPQt.insert(tBoard);
        Board t = tBoard;
        Board lastprocessed = tBoard;
        int priority = tBoard.manhattan() + tBoard.hamming();
        int count = 0;
        while (!t.isGoal()) {
            if (count > 50) {
                return true;
            }
            t = minPQt.delMin();
            for (Board next : t.neighbors()) {
                if (next.equals(lastprocessed)) {
                    continue;
                }
                next.move = next.move + 1;
                minPQt.insert(next);
            }

            lastprocessed = t;

            count++;
        }
        if (t.isGoal()) {
            return false;
        }

        return true;
    }           // is the initial board solvable?
    public int moves() {
        if (!isSolvable()) {
            return -1;
        }
        return board.hamming();
    }                    
    // min number of moves to solve initial board; -1 if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable()) {
            return null;
        }
        return new Solution();
    }     // sequence of boards in a shortest solution; null if unsolvable'

    private class Solution implements Iterable<Board> {

        @Override
        public Iterator<Board> iterator() {
            // TODO Auto-generated method stub
            return new BoardIterator();
        }
        private class BoardIterator implements Iterator<Board> {
            Board b = board;
            Board lastprocessed = board;
            int count = 0;
            @Override
            public boolean hasNext() {
                // TODO Auto-generated method stub
                if (b.isGoal()) {
                    if (board.isGoal() && count == 0) {
                        count++;
                        return true;
                    }
                    return false;
                }
                return true;
            }

            @Override
            public Board next() {
                // TODO Auto-generated method stub
                if (board.isGoal()) {
                    return board;
                }
                boolean f = false;
                for (Board del : lastprocessed.neighbors()) {
                    if (minPQ.min().equals(del)) {
                        f = true;
                    }
                }
                if (f || minPQ.size() == 1) {
                    b = minPQ.delMin();
                }
                else {
                    Board s = minPQ.delMin();
                    b = minPQ.delMin();
                    minPQ.insert(s);

                }


                for (Board next : b.neighbors()) {
                    if (next.equals(lastprocessed)) {
                        continue;
                    }
                    int move = next.move;
                    move++;
                    next.move = move;
                    minPQ.insert(next);
                }

                lastprocessed = b;
                return b;
            }

        }

    }
    private class BoardComparator implements Comparator<Board> {

        @Override
        public int compare(Board b1, Board b2) {
            // TODO Auto-generated method stub
            int priority1 = b1.manhattan() + b1.move;
            int priority2 = b2.manhattan() + b2.move;
            //            if (priority1 < priority2) {
            //                return -1;
            //            }
            //            if (priority1 == priority2) {                
            //                return 0;
            //            }
            //            return 1;
            return Integer.compare(priority1, priority2);
        }

    }
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
    } // solve a slider puzzle (given below)
}