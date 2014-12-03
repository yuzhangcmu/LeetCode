package Algorithms.dfs;

import java.util.ArrayList;
import java.util.List;

public class SolveNQueens {
    public static void main(String[] strs) {
        List<String[]> list = solveNQueens(9);
        
        for (String[] strss: list) {
            for (String s: strss) {
                //System.out.println(s);
            }
            
        }
        
        Long ret = Long.MAX_VALUE;
        int a = Integer.MAX_VALUE;
        if (ret == a) {
            System.out.println(true);
        }
        
        //System.out.println(solveNQueens(4).toString());
    }
    
    public static List<String[]> solveNQueens(int n) {
        List<String[]> ret = new ArrayList<String[]>();
        if (n == 0) {
            return ret;
        }
        
        StringBuilder[] path = new StringBuilder[n];
        for (int i = 0; i < n; i++) {
            path[i] = new StringBuilder();
            for (int j = 0; j < n; j++) {
                path[i].append('.');
            }
        }
        dfs(n, path, ret, 0);
        
        return ret;
    }
    
    // 递归定义为：棋盘上已经有一些棋子，还需要放n个皇后，求所有的可能的解.
    // 那么 我们可以先放1个，这里有多种可能，然后再用递归继续求解即可。
    public static void dfs(int n, StringBuilder[] path, List<String[]> ret, int index) {
        int rows = path.length;
        
        if (n == 0) {
            String[] strs = new String[rows];
            for (int i = 0; i < rows; i++) {
                strs[i] = path[i].toString();
            }
            ret.add(strs);
            return;
        }
        
        // index out of bound;
        if (index >= rows * rows) {
            return;
        }
        
        // 找到一个地方先放一个棋子
        for (int i = index; i < rows*rows; i++) {
            int row = i / rows;
            int col = i % rows;
            
            if (!canPut(path, row, col)) {
                // 这个位置必须是可放置的
                continue;
            }
            path[row].setCharAt(col, 'Q');
            
            // 向下一级递归
            dfs(n - 1, path, ret, i + 1);
            
            //回溯
            path[row].setCharAt(col, '.');
            
        }
        
        return;
    }
    
    // put a chessman, and disable some positions in the chessboard.
    public static boolean canPut(StringBuilder[] path, int row, int col) {
        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path.length; j++) {
                if (path[i].charAt(j) == 'Q' && 
                   (i == row || j == col || Math.abs(i - row) == Math.abs(j - col))
                ) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
