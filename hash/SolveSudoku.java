package Algorithms.hash;

public class SolveSudoku {
    public void solveSudoku(char[][] board) {
        dfs(board, 0, 0);
    }
    
    public boolean dfs(char[][] board, int x, int y) {
        // go to next row.
        if (y == 9) {
            y = 0;
            x++;
        }
        
        // done
        if (x >= 9) {
            return true;
        }
        
        // Skip the solved point.
        if (board[x][y] != '.') {
            return dfs(board, x, y + 1);
        }
        
        // Go throught all the possibilities.
        for (int k = 0; k < 9; k++) {
            board[x][y] = (char)('1' + k);    
            // SHOULD RETURN HERE IF INVALID.                
            if (isValid(board, x, y) && dfs(board, x, y  + 1)) {
                return true;
            }
            board[x][y] = '.';
        }                

        // because all the possibility is impossiable.                
        return false;
    }
    
    public boolean isValid(char[][] board, int x, int y) {
        // Judge the column.
        for (int i = 0; i < 9; i++) {
            if (i != x && board[i][y] == board[x][y]) {
                return false;
            }
        }
        
        // Judge the row.
        for (int i = 0; i < 9; i++) {
            if (i != y && board[x][i] == board[x][y]) {
                return false;
            }
        }
        
        // Judge the block.
        int i = x / 3 * 3;
        int j = y / 3 * 3;
        for (int k = 0; k < 9; k++) {
            int xIndex = i + k / 3;
            int yIndex = j + k % 3;
            if (xIndex == x && yIndex == y) {
                continue;
            }
            
            if (board[xIndex][yIndex] == board[x][y]) {
                return false;
            }
        }
        
        return true;
    }
}
