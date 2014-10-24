package Algorithms.array;

public class SetZeroes {
    public static void main(String[] strs) {
        int[][] matrix = {
                {0,0,0,5},{4,3,1,4},{0,1,1,4},{1,2,1,3},{0,0,1,1} 
        };
        
        setZeroes(matrix);
    }
    
    public static void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 
            || matrix[0].length == 0) {
            return;        
        }
        
        boolean row1Zero = false;
        boolean col1Zero = false;
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        // Determine if the first column should be Zero.
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) {
                col1Zero = true;
                break;
            }
        }
        
        // Determine if the first row should be Zero.
        for (int i = 0; i < cols; i++) {
            if (matrix[0][i] == 0) {
                row1Zero = true;
                break;
            }
        }
        
        // we use the first row and the first col as the flag to record the 
        // cells whether or not set to 0.
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                // 注意了，这个矩阵是0和非0，并不是0和1.
                if (matrix[i][j] == 0) {
                    // set the flag in the first line and the first column                
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        
        // set the inner cells.
        // Be careful: i, j start from 1.
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][0] == 0
                   || matrix[0][j] == 0) {
                    matrix[i][j] = 0;   
                }
            }
        }
        
        // set the first row.
        if (row1Zero) {
            for (int i = 0; i < cols; i++) {
                matrix[0][i] = 0;
            }    
        }
        
        // set the first col.
        if (col1Zero) {
            for (int i = 0; i < rows; i++) {
                matrix[i][0] = 0;
            }    
        }
        
        return;
    }
}
