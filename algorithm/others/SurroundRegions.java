package Algorithms.algorithm.others;

import java.util.LinkedList;
import java.util.Queue;

public class SurroundRegions {
    public static void main(String[] args) {
        char[][] board = {
                {'O', 'X', 'X'},
                {'X', 'O', 'X'},
                {'X', 'X', 'X'},
        };
        
        solve(board);
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
    
    public static void solve(char[][] board) {
        if (board == null) {
            return;
        }
        
        int row = board.length;
        int col = board[0].length;
        
        // BFS the first line and the last line.
        for (int i = 0; i < col; i++) {
            bfs(board, 0, i);
            bfs(board, row - 1, i);
        }
        
        // BFS the left line and the right line.
        for (int i = 1; i < row - 1; i++) {
            bfs(board, i, 0);
            bfs(board, i, col - 1);
        }
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'B') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
        
        return;
    }
    
    public static void bfs(char[][] board, int x, int y) {
        Queue<Integer> q = new LinkedList<Integer>();
        
        // ???x, y??????queue????????????????????????????????????
        visit(board, x, y, q);
        while (!q.isEmpty()) {
            int num = q.poll();
            int r = num / board[0].length;
            int c = num % board[0].length;
            
            // visit the surrounded points.
            visit(board, r + 1, c, q);
            visit(board, r - 1, c, q);
            visit(board, r, c + 1, q);
            visit(board, r, c - 1, q);
        }
        
        return;
    }
    
    public static void visit(char[][] board, int x, int y, Queue<Integer> q) {
        int row = board.length;
        int col = board[0].length;
        if (x < 0 || x >= row || y < 0 || y >= col) {
            return;
        }
        
        if (board[x][y] != 'O') {
            return;
        }
        
        board[x][y] = 'B';
        
        q.offer(col * x + y);
        
        return;
    }
}
