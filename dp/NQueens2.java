package Algorithms.dp;

import java.util.ArrayList;

public class NQueens2 {
    public static void main(String[] args) {
        NQueens2 nq = new NQueens2();
        nq.totalNQueens(1);
    }
    
    public int totalNQueens(int n) {
        ArrayList<Integer> cols = new ArrayList<Integer>();
        return totalNQueensHelp(n, cols, 0);
    }
    
    public boolean isValid(ArrayList<Integer> cols, int col) {
        for (int i = 0; i < cols.size(); i++) {
            if (col == cols.get(i) || (cols.size() - i == Math.abs(col - cols.get(i)))) {
                return false;
            }
        }
        
        return true;
    }
    
    public int totalNQueensHelp(int n, ArrayList<Integer> cols, int total) {
        if (cols.size() == n) {
            // get a new solution.
            return total + 1;
        }
        
        int newTotal = total;
        
        for (int i = 0; i < cols.size(); i ++) {
            if (!isValid(cols, i)) { // this is not a solution. 
                continue;
            }
            
            cols.add(i);
            newTotal = totalNQueensHelp(n, cols, newTotal);
            cols.remove(cols.size() - 1);
        }
        
        return newTotal;
    }

}
