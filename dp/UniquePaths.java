package Algorithms.dp;

public class UniquePaths {
    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        
        // 经典DP题
        // 1. 状态表达式: D[i][j]: 代表从起点到这一点的所有的路径数目
        // 2. 递推公式:   D[i][j] = D[i - 1][j] + D[i][j - 1]
        // 3. 初始化: D[0][0] = 1 原点只有一种方法到达
        
        int[][] D = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    D[i][j] = 1;
                } else if (i == 0) {
                    D[i][j] = D[i][j - 1];
                } else if (j == 0) {
                    D[i][j] = D[i - 1][j];
                } else {
                    D[i][j] = D[i - 1][j] + D[i][j - 1];    
                }
            }
        }
        
        return D[m - 1][n - 1];
    }
    
    public int uniquePaths2(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        
        // 经典DP题
        // 1. 状态表达式: D[i][j]: 代表从起点到这一点的所有的路径数目
        // 2. 递推公式:   D[i][j] = D[i - 1][j] + D[i][j - 1]
        // 3. 初始化: D[0][0] = 1 原点只有一种方法到达
        
        int[][] D = new int[m][n];
        
        // initiate.
        D[0][0] = 1;
        
        // initiate the first line
        for (int j = 1; j < n; j++) {
            D[0][j] = D[0][j - 1];
        }
        
        // initiate the first column
        for (int i = 1; i < m; i++) {
            D[i][0] = D[i - 1][0];
        }
        
        // get the result.
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                D[i][j] = D[i - 1][j] + D[i][j - 1];    
            }
        }
        
        return D[m - 1][n - 1];
    }
}