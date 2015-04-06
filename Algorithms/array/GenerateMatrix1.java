package Algorithms.array;

public class GenerateMatrix1 {
    public int[][] generateMatrix1(int n) {
        int[][] ret = new int[n][n];

        if (n == 0) {
            // return a [] not a NULL.
            return ret;
        }
        
        int number = 0;
        int rows = n;
        
        int x1 = 0;
        int y1 = 0;
        
        while (rows > 0) {
            int x2 = x1 + rows - 1;
            int y2 = y1 + rows - 1;
            
            // the Whole first row.
            for (int i = y1; i <= y2; i++) {
                number++;
                ret[x1][i] = number;
            }
            
            // the right column except the first and last line.
            for (int i = x1 + 1; i < x2; i++) {
                number++;
                ret[i][y2] = number;
            }
            
            // This line is very important.
            if (rows <= 1) {
                break;
            }
            
            // the WHOLE last row.
            for (int i = y2; i >= y1; i--) {
                number++;
                ret[x2][i] = number;
            }
            
            // the left column. column keep stable
            // x: x2-1 --> x1 + 1
            for (int i = x2 - 1; i > x1; i--) {
                number++;
                ret[i][y1] = number;
            }
            
            // remember this.
            rows -= 2;
            x1++;
            y1++;
        }
        
        return ret;
    }
    
    /*
        Solution 2: use direction.
    */
    public int[][] generateMatrix2(int n) {
        int[][] ret = new int[n][n];
        if (n == 0) {
            return ret;
        }
        
        int[] x = {1, 0, -1, 0};
        int[] y = {0, 1, 0, -1};
        
        int num = 0;
        
        int step = 0;
        int candElements = 0;
        
        int visitedRows = 0;
        int visitedCols = 0;
        
        // 0: right, 1: down, 2: left, 3: up.
        int direct = 0;
        
        int startx = 0;
        int starty = 0;
        
        while (true) {
            if (x[direct] == 0) {
                // visit the Y axis
                candElements = n - visitedRows;
            } else {
                // visit the X axis
                candElements = n - visitedCols;
            }
            
            if (candElements <= 0) {
                break;
            }
            
            // set the cell.
            ret[startx][starty] = ++num;
            step++;
            
            // change the direction.
            if (step == candElements) {
                step = 0;
                visitedRows += x[direct] == 0 ? 0: 1;
                visitedCols += y[direct] == 0 ? 0: 1;
                
                // change the direction.
                direct = (direct + 1) % 4;
            }
            
            startx += y[direct];
            starty += x[direct];
        }
        
        return ret;
    }
    
    /*
        Solution 3: 使用四条bound来限制的方法.
    */
    public int[][] generateMatrix(int n) {
        int[][] ret = new int[n][n];
        if (n == 0) {
            return ret;
        }
        
        int top = 0, bottom = n - 1, left = 0, right = n - 1;
        int num = 1;
        while (top <= bottom) {
            if (top == bottom) {
                ret[top][top] = num++;
                break;
            }
            
            // first line.
            for (int i = left; i < right; i++) {
                ret[top][i] = num++;
            }
            
            // right line;
            for (int i = top; i < bottom; i++) {
                ret[i][right] = num++;
            }
            
            // bottom line;
            for (int i = right; i > left; i--) {
                ret[bottom][i] = num++;
            }
            
            // left line;
            for (int i = bottom; i > top; i--) {
                ret[i][left] = num++;
            }
            
            top++;
            bottom--;
            left++;
            right--;
        }
        
        return ret;
    }
}