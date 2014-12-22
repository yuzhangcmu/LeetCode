package Algorithms.dp;

public class MinPathSum_1222_2014 {
    // Solution 1: DP
    public int minPathSum1(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] D = new int[rows][cols];
        
        // This is a simple DP.
        // 表达式：  D[i][j]: 从左下到本点的最小值
        // 递推公式: D[i][j] = Math.mn(D[i - 1][j], D[i][j - 1]) + grid[i][j]
        // 初始化：  D[i][j] = grid[i][j].
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                D[i][j] = grid[i][j];
                
                if (i == 0 && j != 0) {
                    D[i][j] += D[i][j - 1];
                } else if (j == 0 && i != 0) {
                    D[i][j] += D[i - 1][j];
                } else if (i != 0 && j != 0) {
                    D[i][j] += Math.min(D[i][j - 1], D[i - 1][j]);
                }
            }
        }
        
        return D[rows - 1][cols - 1];
    }
    
    // Solution 2: DFS + memory.
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int[][] memory = new int[grid.length][grid[0].length];
        
        // Bug 1: forget to initilize
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                memory[i][j] = -1;
            }
        }
        
        return dfs(grid, 0, 0, memory);
    }
    
    public int dfs (int[][] grid, int i, int j, int[][] memory) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        if (i >= rows || j >= cols) {
            // 表示不可达
            return Integer.MAX_VALUE;
        }
        
        // The base case: arrive the destination.
        if (i == rows - 1 && j == cols - 1) {
            return grid[i][j];
        }
        
        // 已经搜索过的点不需要重复搜索        
        if (memory[i][j] != -1) {
            return memory[i][j];
        }
        
        int sum = grid[i][j];
        
        // 开始dfs 可能的路径,目前我们只有2种可能
        sum += Math.min(dfs(grid, i + 1, j, memory), dfs(grid, i, j + 1, memory));
        
        // Record the memory
        memory[i][j] = sum;
        return sum;        
    }
}