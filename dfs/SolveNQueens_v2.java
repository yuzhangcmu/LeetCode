package Algorithms.dfs;

import java.util.ArrayList;
import java.util.List;

/*
 * N-Queens Total Accepted: 16418 Total Submissions: 63309 My Submissions
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

For example,
There exist two distinct solutions to the 4-queens puzzle:

[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
 * */
public class SolveNQueens_v2 {
    public static List<String[]> solveNQueens(int n) {
        List<String[]> ret = new ArrayList<String[]>();
        if (n == 0) {
            return ret;
        }
        
        ArrayList<Integer> cols = new ArrayList<Integer>();
        
        solveNQueensHelp(n, cols, ret);
        
        return ret;
    }
    
    // 根据列值把解集合算出来
    public static String[] createSol(int n, ArrayList<Integer> cols) {
        String[] ret = new String[n];
        
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append('.');
            }
            sb.setCharAt(cols.get(i), 'Q');
            ret[i] = sb.toString();
        }
        
        return ret;
    }
    
    public static boolean isValid(ArrayList<Integer> cols, int c) {
        for (int i = 0; i < cols.size(); i++) {
            // 与某皇后在同一列
            if (c == cols.get(i)) {
                return false;
            }
            
            // 在交叉2个方向 
            // cols.size() 是新插入的皇后的行数
            if (cols.size() - i == Math.abs(c - cols.get(i))) {
                return false;
            }
        }
        
        return true;
    }
    
    /*
     * n:    表示需要放多少个皇后
     * cols: 存放每一行皇后的列值。 我们在每一行放一个皇后，所以只需要算出第一行皇后的列值即可
     * ret:  返回值
     * */
    public static void solveNQueensHelp(int n, ArrayList<Integer> cols, List<String[]> ret) {
        // 每一行的皇后的坐标都确定了。
        if (cols.size() == n) {
            String[] sol = createSol(n, cols);
            ret.add(sol);
            return;
        }
        
        // DFS 某一行中所有的位置，看是否可以放置一个皇后.
        // 如果这一行根本没有找到解，会直接返回
        for (int i = 0; i < n; i++) {
            // 判断这个位置是否与已经放好的皇后冲突
            if (!isValid(cols, i)) {
                continue;
            }
            
            cols.add(i);
            solveNQueensHelp(n, cols, ret);
            cols.remove(cols.size() - 1);
        }
    }
}