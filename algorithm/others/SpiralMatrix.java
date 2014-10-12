package Algorithms.algorithm.others;
public class SpiralMatrix {
    public int[][] generateMatrix(int n) {
        int[][] rst = new int[n][n];
        generateHelp(0, 1, rst, n);
        return rst;
    }
    
    private void generateHelp(int level, int beginNumber, int[][] rst, int n) {
        if (n % 2 == 0) {
            if (level == n/2) {
                return;
            }
        } else {
            if (level > n/2) {
                return;
            }
        }
        
        int right = n - 1 - level;
        
        int num = beginNumber;
        int i = level, j = level;
        
        // go through the first line.
        while (j <= right) {
            rst[i][j] = num;
            num++;
            j++;
        }
        
        while (i <= right) {
            rst[i][j] = num;
            num++;
            i++;
        }
        
        while (j > level) {
            rst[i][j] = num;
            num++;
            j--;
        }
        
        // go to the next level;
        generateHelp(level + 1, num, rst, n);
        return;
    }
}