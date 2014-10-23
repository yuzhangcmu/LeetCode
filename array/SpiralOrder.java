package Algorithms.array;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrder {
    public static void main(String[] strs) {
        int[][] matrix = {
                {1, 2},
                {3, 4}
        };
        spiralOrder(matrix);
    }
    
    public List<Integer> spiralOrder1(int[][] matrix) {
        List<Integer> ret = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 
            || matrix[0].length == 0) {
            return ret;   
        }
        
        rec(matrix, 0, 0, matrix.length, matrix[0].length, ret);
        
        return ret;
    }
    
    public static void rec(int[][] matrix, int x, int y, int rows, int cols, List<Integer> ret) {
        if (rows <= 0 || cols <= 0) {
            return;
        }
        
        // first line
        for (int i = 0; i < cols; i++) {
            ret.add(matrix[x][y + i]);
        }
        
        // right column
        for (int i = 1; i < rows - 1; i++) {
            ret.add(matrix[x + i][y + cols - 1]);
        }
        
        // down row
        if (rows > 1) {
            for (int i = cols - 1; i >= 0; i--) {
                ret.add(matrix[x + rows - 1][y + i]);
            }    
        }
        
        // left column. GO UP.
        if (cols > 1) {
            for (int i = rows - 2; i > 0; i--) {
                ret.add(matrix[x + i][y]);
            }    
        }
        
        rec (matrix, x + 1, y + 1, rows - 2, cols - 2, ret);
    }
    
    /*
    Solution 2:
        REF: http://blog.csdn.net/fightforyourdream/article/details/16876107?reload
        此算法比较不容易算错
    */
    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> ret = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 
            || matrix[0].length == 0) {
            return ret;   
        }
        
        int x1 = 0;
        int y1 = 0;
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        while (rows >= 1 && cols >= 1) {
            // Record the right down corner of the matrix.
            int x2 = x1 + rows - 1;
            int y2 = y1 + cols - 1;
            
            // go through the WHOLE first line.
            for (int i = y1; i <= y2; i++) {
                ret.add(matrix[x1][i]);
            }
            
            // go through the right column.
            for (int i = x1 + 1; i < x2; i++) {
                ret.add(matrix[i][y2]);
            }
            
            // go through the WHOLE last row.
            if (rows > 1) {
                for (int i = y2; i >= y1; i--) {
                    ret.add(matrix[x2][i]);
                }    
            }
            
            // the left column.
            if (cols > 1) {
                for (int i = x2 - 1; i > x1; i--) {
                    ret.add(matrix[i][y1]);
                }
            }    
            
            // in one loop we deal with 2 rows and 2 cols.
            rows -= 2;
            cols -= 2;
            x1++;
            y1++;
        }
        
        return ret;
    }
    
    /*
    Solution 3:
    使用方向矩阵来求解
    */
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ret = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 
            || matrix[0].length == 0) {
            return ret;   
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        int visitedRows = 0;
        int visitedCols = 0;

        // indicate the direction of x    
        
        // 1: means we are visiting the row by the right direction.
        // -1: means we are visiting the row by the left direction.
        int[] x = {1, 0, -1, 0};
        
        // 1: means we are visiting the colum by the down direction.
        // -1: means we are visiting the colum by the up direction.
        int[] y = {0, 1, 0, -1};
        
        // 0: right, 1: down, 2: left, 3: up.
        int direct = 0;
        
        int startx = 0;
        int starty = 0;
        
        int candidateNum = 0;
        int step = 0;
        while (true) {
            if (x[direct] == 0) {
                // visit Y axis.
                candidateNum = rows - visitedRows;
            } else {
                // visit X axis
                candidateNum = cols - visitedCols;
            }
            
            if (candidateNum <= 0) {
                break;
            }
            
            ret.add(matrix[startx][starty]);
            step++;
            
            if (step == candidateNum) {
                step = 0;
                visitedRows += x[direct] == 0 ? 0: 1;
                visitedCols += y[direct] == 0 ? 0: 1;
                
                // move forward the direction.
                direct ++;
                direct = direct%4;
            }
            
            // 根据方向来移动横坐标和纵坐标。
            startx += y[direct];
            starty += x[direct];
        }
        
        return ret;
    }
}
