package Algorithms.array;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrder_1201 {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ret = new ArrayList<Integer>();
        if (matrix == null ||matrix.length == 0) {
            // 注意在非法的时候，应该返回空解，而不是一个NULL值
            return ret;
        }
        
        // Record how many rows and cols we still have.
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        // The four coners.
        int top = 0;
        int left = 0;
        int bottom = rows - 1;
        int right = cols - 1;
        
        // every time we go through two rows and two cols.
        for (; rows > 0 && cols > 0; rows -= 2, cols -= 2, top++, left++, bottom--, right--) {
            // the first line.
            for (int i = left; i <= right; i++) {
                ret.add(matrix[top][i]);
            } 
            
            // the right column.
            for (int i = top + 1; i < bottom; i++) {
                ret.add(matrix[i][right]);
            }
            
            // the down line;
            if (rows > 1) {
                for (int j = right; j >= left; j--) {
                    ret.add(matrix[bottom][j]);
                }
            }
            
            // the left column.
            if (cols > 1) {
                for (int i = bottom - 1; i > top; i --) {
                    ret.add(matrix[i][left]);
                }
            }
        }
        
        return ret;
    }
}