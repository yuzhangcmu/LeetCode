package Algorithms.hash;

import java.util.HashSet;

public class IsValidSudoku {
    public static void main(String[] strs) {
        char[][] board = new char[9][9];
        
        String[] strs1 = new String[9];
        
        strs1[0] = ".87654321";
        strs1[1] = "2........";
        strs1[2] = "3........";
        strs1[3] = "4........";
        strs1[4] = "5........";
        strs1[5] = "6........";
        strs1[6] = "7........";
        strs1[7] = "8........";
        strs1[8] = "9........";
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = strs1[i].charAt(j);
            }
        }
        
        System.out.println(isValidSudoku(board));
    }
    
    public static boolean isValidSudoku(char[][] board) {
        // 0346
        HashSet<Character> set = new HashSet<Character>();
        
        // Examine every rows.
        for (int i = 0; i < 9; i++) {
            set.clear();
            for (int j = 0; j < 9; j++) {
                if (!isValid(board, i, j, set)) {
                    return false;
                }
            }
        }
        
        // Examine every cols.
        for (int j = 0; j < 9; j++) {
            set.clear();
            for (int i = 0; i < 9; i++) {
                if (!isValid(board, i, j, set)) {
                    return false;
                }
            }
        }
        
        // Eamine every square.
        for (int i = 0; i < 9; i++) {
            set.clear();
            for (int j = i / 3 * 3; j < i / 3 * 3 + 3; j++) {
                for (int k = i % 3 * 3; k < i % 3 * 3 + 3; k++) {
                    if (!isValid(board, j, k, set)) {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
    
    public static boolean isValid(char[][] board, int i, int j, HashSet<Character> set) {
        if (board[i][j] == '.') {
            return true;
        }
        
        if (!set.contains(board[i][j])) {
            return false;                    
        }
                
        set.add(board[i][j]);
        return true;
    }
    
    public static boolean isValidSudoku1(char[][] board) {
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
