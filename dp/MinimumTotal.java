package Algorithms.dp;

import java.util.List;

public class MinimumTotal {
    /*
    REC, SOL 1:
    */
    public int minimumTotal1(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        
        int rows = triangle.size();
        int[][] mem = new int[rows][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                mem[i][j] = Integer.MAX_VALUE;
            }
        }
        
        return dfs(triangle, 0, 0, mem);
    }
    
    public int dfs(List<List<Integer>> triangle, int row, int col, int[][] mem) {
        if (mem[row][col] != Integer.MAX_VALUE) {
            return mem[row][col];
        }
        
        if (row == triangle.size() - 1) {
            mem[row][col] = triangle.get(row).get(col);
        } else {
            int left = dfs(triangle, row + 1, col, mem);
            int right = dfs(triangle, row + 1, col + 1, mem);    
            mem[row][col] = triangle.get(row).get(col) + Math.min(left, right);
        }
        
        return mem[row][col];
    }
    
    /*
    DP, SOL 2:
    */
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        
        int rows = triangle.size();
        int[] D = new int[rows];
        
        for (int i = rows - 1; i >= 0; i--) {
            // 注意：边界条件是 j <= i
            for (int j = 0; j <= i; j++) {
                if (i == rows - 1) {
                    D[j] = triangle.get(i).get(j);
                } else {
                    D[j] = triangle.get(i).get(j) + Math.min(D[j], D[j + 1]);
                }    
            }
        }
        
        return D[0];
    }
}