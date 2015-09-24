package Algorithms.dp;

public class MinPathSum {
    public int minPathSum2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }    
        
        // This is a simple DP.
        // 表达式：  D[i][j]: 从左下到本点的最小值
        // 递推公式: D[i][j] = Math.mn(D[i - 1][j], D[i][j - 1]) + grid[i][j]
        // 初始化：  D[i][j] = grid[i][j].
        
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] D = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                D[i][j] = grid[i][j];
                if (i == 0 && j == 0) {
                    continue;
                } else if (i == 0) {
                    D[i][j] += D[i][j - 1]; 
                } else if (j == 0) {
                    D[i][j] += D[i - 1][j];
                } else {
                    D[i][j] += Math.min(D[i][j - 1], D[i - 1][j]);
                }
            }
        }
        
        return D[rows - 1][cols - 1];
    }
    
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int[][] visit = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                visit[i][j] = -1;
            }
        }
        
        return dfs(grid, 0, 0, visit);
    }
    
    public int dfs(int[][] grid, int i, int j, int[][] visit) {
        int row = grid.length;
        int col = grid[0].length;

        // 符合递归的终止条件，因为我已经到达了终点
        if (i == row - 1 && j == col - 1) {
            return grid[i][j];
        }

        if (i >= row || j >= col || i < 0 || j < 0) {
            // 表示不可达
            return Integer.MAX_VALUE;
        }

        // 已经搜索过的点不需要重复搜索
        if (visit[i][j] != -1) {
            return visit[i][j];
        }

        // 开始dfs 可能的路径,目前我们只有2种可能
        int right = dfs(grid, i, j + 1, visit);
        int down = dfs(grid, i + 1, j, visit);

        visit[i][j] = Math.min(down, right) + grid[i][j];
        return visit[i][j];
    }
}