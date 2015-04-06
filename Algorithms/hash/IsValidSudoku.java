package Algorithms.hash;

import java.util.HashSet;

public class IsValidSudoku {
    public static void main(String[] strs) {
        
    }
    
    public static boolean isValidSudoku(char[][] board) {
        if (board == null || board.length != 9 ||
            board[0].length != 9) {
            return false;
        }
        
        HashSet<Character> set = new HashSet<Character>();
        
        // check the rows.
        for (int i = 0; i < 9; i++) {
            // clear the set at every row.
            set.clear();
            for (int j = 0; j < 9; j++) {
                if (!isValidChar(board[i][j], set)) {
                    return false;
                }
            }
        }
        
        // check the columns.
        for (int i = 0; i < 9; i++) {
            // clear the set at every column.
            set.clear();
            for (int j = 0; j < 9; j++) {
                if (!isValidChar(board[j][i], set)) {
                    return false;
                }
            }
        }
        
        // check the blocks.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // clear the set at every block.
                set.clear();
                for (int k = 0; k < 9; k++) {
                    if (!isValidChar(board[i*3 + k / 3][j*3 + k % 3], set)) { // here is a bug
                        return false;
                    }    
                }
            }
        }
        
        return true;
    }
    
    public static boolean isValidChar(char c, HashSet<Character> set) {
        if (c == '.') {
            return true;
        }
        
        if (c < '0' || c > '9') {
            return false;
        }
        
        // Check if the character exit in the hashset.
        if (set.contains(c)) {
            return false;
        }
        
        set.add(c);
        
        return true;
    }
}
