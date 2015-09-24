package Algorithms.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class Solve_SurroundedRegions {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        
        int rows = board.length;
        int cols = board[0].length;
        
        // bfs the left and right side.
        for (int i = 0; i < rows; i++) {
            bfs(board, i, 0);
            bfs(board, i, cols - 1);
        }
        
        // bfs the first and the last line.
        for (int i = 0; i < cols; i++) {
            bfs(board, 0, i);
            bfs(board, rows - 1, i);
        }
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'B') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
        
        return;
    }
    
    public void bfs(char[][] board, int i, int j) {
        Queue<Integer> q = new LinkedList<Integer>();
        int cols = board[0].length;
        
        q.offer(i * cols + j);
        
        while(!q.isEmpty()) {
            int index = q.poll();
            visit(board, index, q);
        }
        
    }
    
    public void visit(char[][] board, int index, Queue<Integer> q) {
        int rows = board.length;
        int cols = board[0].length;
        
        int i = index/cols;
        int j = index%cols;
        
        // out of bound or visited.
        if (i < 0 || i >= rows || j < 0 || j >= cols) {
            return;
        }
        
        if (board[i][j] != 'O') {
            return;
        }
        
        board[i][j] = 'B';    
        
        // 注意index的计算
        q.offer(index - 1);
        q.offer(index + 1);
        q.offer(index - cols);
        q.offer(index + cols);
    }
    
    public void dfs(char[][] board, int i, int j) {
        int rows = board.length;
        int cols = board[0].length;
        
        // out of bound or visited.
        if (i < 0 || i >= rows || j < 0 || j >= cols) {
            return;
        }
        
        if (board[i][j] != 'O') {
            return;
        }
        
        board[i][j] = 'B';
        
        // dfs the sorrounded regions.
        dfs(board, i + 1, j);
        dfs(board, i - 1, j);
        dfs(board, i, j + 1);
        dfs(board, i, j - 1);
    }
}
