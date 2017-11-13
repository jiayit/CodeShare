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
import java.util.Iterator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;


public class Board {
    public int move;
    private int[][] board;
    private final int dimension;
    private int row;
    private int col;
    private final int neighbornum;
    public Board(int[][] blocks) {
        dimension = blocks.length;
        board = new int[dimension][dimension];
        board = blocks;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;
                }
            }
        }
        int r, c;
        if ((row % (dimension - 1)) >= 1) {
            r = 1;
        }
        else {
            r = 0;
        }
        if ((col % (dimension - 1)) >= 1) {
            c = 1;
        }
        else {
            c = 0;
        }
        neighbornum = 2 + r + c;
    }          // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public int dimension() {
        return dimension;
    }                // board dimension n
    public int hamming() {
        int num = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (board[i][j] == (i * dimension + j + 1) || board[i][j] == 0) {
                    continue;
                }
                num++;
            }
        }
        return num;
    }                  // number of blocks out of place
    public int manhattan() {
        int step = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (board[i][j] == (i * dimension + j + 1) || board[i][j] == 0) {
                    continue;
                }
                int row1 = (board[i][j] - 1) / 3;
                int col1 = (board[i][j] - 1) % 3;
                step = step + Math.abs(row1 - i) + Math.abs(col1 - j);
            }
        }
        return step;
    }                // sum of Manhattan distances between blocks and goal
    public boolean isGoal() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (board[i][j] == (i * dimension + j + 1) 
                        || (i + j + 2 == dimension * 2)) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }               // is this board the goal board?
    public Board twin() {
        int[][] twinBoard = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                twinBoard[i][j] = board[i][j];
            }
        }
        if (row < dimension - 1) {

            int temp = twinBoard[dimension - 1][0];
            twinBoard[dimension - 1][0] = twinBoard[dimension - 1][1];
            twinBoard[dimension - 1][1] = temp;
            return new Board(twinBoard);
        }
        else {
            int temp = twinBoard[0][0];
            twinBoard[0][0] = twinBoard[0][1];
            twinBoard[0][1] = temp;
        }
        return new Board(twinBoard);
    }                   // a board that is obtained by exchanging any pair of blocks
    @Override
    public boolean equals(Object y) {
        if (!(y.getClass() == Board.class)) {
            return false;
        }
        Board b = (Board) y;
        if (this.toString().equals(b.toString())) {
            return true;
        }
        return false;
    }       // does this board equal y?
    @Override
    public String toString() {
        StringBuffer string = new StringBuffer();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                string.append(String.valueOf(board[i][j]));
                string.append(" ");
            }
            string.append("\n");
        }
        return string.toString();
    }              
    // string representation of this board (in the output format specified below)
    public Iterable<Board> neighbors() {
        return new NeighborsIterator();
    }    // all neighboring boards
    private class NeighborsIterator implements Iterable<Board> {

        @Override
        public Iterator<Board> iterator() {
            // TODO Auto-generated method stub
            return new InsideIterator();
        }
        private class InsideIterator implements Iterator<Board> {
            int flag = neighbornum;
            boolean[] visit = new boolean[4]; 
            @Override
            public boolean hasNext() {
                // TODO Auto-generated method stub
                if (flag != 0) {
                    flag--;
                    return true;
                }
                return false;
            }

            @Override
            public Board next() {
                // TODO Auto-generated method stub
                int temp;
                int[][] next = new int[dimension][dimension];
                for (int i = 0; i < dimension; i++) {
                    for (int j = 0; j < dimension; j++) {
                        next[i][j] = board[i][j];
                    }
                }
                if (row - 1 >= 0) {
                    if (!visit[0]) {
                        temp = board[row - 1][col];
                        next[row - 1][col] = 0;
                        next[row][col] = temp;
                        visit[0] = true;
                        return new Board(next);
                    }
                }
                if (col - 1 >= 0) {
                    if (!visit[1]) {
                        temp = board[row][col - 1];
                        next[row][col - 1] = 0;
                        next[row][col] = temp;
                        visit[1] = true;
                        return new Board(next);
                    }
                }
                if (col + 1 < dimension) {
                    if (!visit[2]) {                        
                        temp = board[row][col + 1];
                        next[row][col + 1] = 0;
                        next[row][col] = temp;
                        visit[2] = true;
                        return new Board(next);
                    }
                }
                if (row +1 < dimension) {
                    if (!visit[3]) {                        
                        temp = board[row + 1][col];
                        next[row + 1][col] = 0;
                        next[row][col] = temp;
                        visit[3] = true;

                    }

                }
                return new Board(next);
            }

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
        Board board = new Board(blocks);


        StdOut.println(initial.hamming());
        StdOut.println(initial.isGoal());
        StdOut.println(initial.manhattan());
        StdOut.println(initial.twin());
        for (Board b: initial.neighbors())
            StdOut.println(b);
    } // unit tests (not graded)
}