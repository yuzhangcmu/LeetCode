package Algorithms.dp;

public class UniquePathsWithObstacles {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 
          || obstacleGrid[0].length == 0) {
            return 0;      
        }
        
        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;
        
        int[][] D = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (obstacleGrid[i][j] == 1) {
                    D[i][j] = 0;
                    continue;
                }
                    
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
        
        return D[rows - 1][cols - 1];
    }
}