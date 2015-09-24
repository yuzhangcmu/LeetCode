package Algorithms.bfs;

import java.util.LinkedList;
import java.util.Queue;

//https://oj.leetcode.com/problems/surrounded-regions/
public class Solve {
    public static void main(String[] strs) {
        char[][] board = {
                {'X','O','X','O','X','O'},
                {'O','X','O','X','O','X'},
                {'X','O','X','O','X','O'},
                {'O','X','O','X','O','X'}
        };
        
        //solve(board);
    }
    
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        
        int rows = board.length;
        int cols = board[0].length;
        
        // the first line and the last line.
        for (int j = 0; j < cols; j++) {
            bfs(board, 0, j);
            bfs(board, rows - 1, j);
        }
        
        // the left and right column
        for (int i = 0; i < rows; i++) {
            bfs(board, i, 0);
            bfs(board, i, cols - 1);
        }
        
        // capture all the nodes.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'B') {
                    board[i][j] = 'O';
                }
            }
        }
        
        return;
    }
    
    public void bfs1(char[][] board, int i, int j) {
        int rows = board.length;
        int cols = board[0].length;
        
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(i * cols + j);
        
        while (!q.isEmpty()) {
            int index = q.poll();
            
            // Index is out of bound.
            if (index < 0 || index >= rows * cols) {
                continue;
            }
            
            int x = index / cols;
            int y = index % cols;
            
            if (board[x][y] != 'O') {
                continue;
            }
            
            board[x][y] = 'B';
            q.offer(index + 1);
            q.offer(index - 1);
            q.offer(index + cols);
            q.offer(index - cols);
        }
    }
    
    public void bfs(char[][] board, int i, int j) {
        int rows = board.length;
        int cols = board[0].length;
        
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(i * cols + j);
        
        while (!q.isEmpty()) {
            int index = q.poll();
            
            int x = index / cols;
            int y = index % cols;
            
            if (board[x][y] != 'O') {
                continue;
            }
            
            board[x][y] = 'B';
            if (y < cols - 1) {
                q.offer(index + 1);    
            }
            
            if (y > 0) {
                q.offer(index - 1);    
            }
            
            if (x > 0) {
                q.offer(index - cols);    
            }
            
            if (x < rows - 1) {
                q.offer(index + cols);    
            }
        }
    }
}