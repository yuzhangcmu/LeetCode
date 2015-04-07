package Algorithms.hash;

public class SolveSudoku {
    public static void main(String[] args) {  
        char[][] board = {  
                {'.','.','9','7','4','8','.','.','.'},  
                {'7','.','.','.','.','.','.','.','.'},  
                {'.','2','.','1','.','9','.','.','.'},  
                {'.','.','7','.','.','.','2','4','.'},  
                {'.','6','4','.','1','.','5','9','.'},  
                {'.','9','8','.','.','.','3','.','.'},  
                {'.','.','.','8','.','3','.','2','.'},  
                {'.','.','.','.','.','.','.','.','6'},  
                {'.','.','.','2','7','5','9','.','.'},  
        };  
        solveSudoku(board);  
        for(int i=0; i<9; i++){  
            for(int j=0; j<9; j++){  
                //System.out.print(board[i][j]);  
            }  
            System.out.println();  
        }  
    }  
    
    public void solveSudoku1(char[][] board) {
        dfs1(board, 0, 0);
    }
    
    public boolean dfs1(char[][] board, int x, int y) {
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
            return dfs1(board, x, y + 1);
        }
        
        // Go throught all the possibilities.
        for (int k = 0; k < 9; k++) {
            board[x][y] = (char)('1' + k);    
            // SHOULD RETURN HERE IF INVALID.                
            if (isValid1(board, x, y) && dfs1(board, x, y  + 1)) {
                return true;
            }
            board[x][y] = '.';
        }                

        // because all the possibility is impossiable.                
        return false;
    }
    
    public boolean isValid1(char[][] board, int x, int y) {
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
    
    // solution 2:
    public static void solveSudoku(char[][] board) {
        // 3:01
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        
        dfs(board, 0, 0);
    }
    
    public static boolean dfs(char[][] board, int x, int y) {
        // 3:01
        // next row.
        if (y >= 9) {
            return dfs(board, x + 1, 0);
        }
        
        if (x >= 9) {
            for(int i=0; i<9; i++){  
                for(int j=0; j<9; j++){  
                    System.out.print(board[i][j]);  
                }  
                System.out.println();  
            }
            
            System.out.println(); 
            return true;
        }
        
        // skip the number.
        if (board[x][y] != '.') {
            return dfs(board, x, y + 1);
        }
        
        // solve the current node.
        for (char c = '1'; c <= '9'; c++) {
            board[x][y] = c;
//            if (isValid(board, x, y, c) && dfs(board, x, y + 1)) {
//                return true;
//            }
            
            if (!isValid(board, x, y, c)) {
                board[x][y] = '.';
                continue;
            }
            
            dfs(board, x, y + 1);
            board[x][y] = '.';
        }
        
        return false;
    }
    
    public static boolean isValid(char[][] board, int x, int y, char c) {
        // the current row.        
        for (int i = 0; i < 9; i++) {
            if (y != i && c == board[x][i]) {
                return false;
            }
        }
        
        // the current col.        
        for (int i = 0; i < 9; i++) {
            // BUG: should use board[i][y]
            if (x != i && c == board[i][y]) {
                return false;
            }
        }
        
        // the current block.
        int startX = x / 3 * 3;
        int startY = y / 3 * 3;
        for (int k = 0; k < 9; k++) {
            int indexX = startX + k / 3;
            int indexY = startY + k % 3;
            if (indexX == x && indexY == y) {
                continue;
            }
            
            if (board[indexX][indexY] == c) {
                return false;
            }
        }
        
        return true;
    }
}
