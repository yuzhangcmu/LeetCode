package Algorithms.array;

import java.util.Stack;

public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        int[][] h = new int[rows][cols];
        
        int max = 0;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                
                h[i][j] = matrix[i][j] == '1' ? 1: 0;
                
                if (i != 0 && h[i][j] != 0) {
                    h[i][j] = h[i - 1][j] + 1;
                }
                
                if (j == cols - 1) {
                    max = Math.max(max, maxArea(h[i]));
                }
            }
        }
        
        return max;
    }
    
    public int maxArea(int[] h) {
        Stack<Integer> s = new Stack<Integer>();
        
        int max = 0;
        int i = 0;
        
        // 注意，这里使用<= 因为当i到达最后，需要计算一次。
        while (i <= h.length) {
            // 
            if (s.isEmpty() || i < h.length && h[i] >= h[s.peek()]) {
                s.push(i);
                i++;
            } else {
                int height = h[s.pop()];
                int width = s.isEmpty() ? i: i - s.peek() - 1;
                max = Math.max(max, height * width);
            }
        }
        
        return max;
    }
}