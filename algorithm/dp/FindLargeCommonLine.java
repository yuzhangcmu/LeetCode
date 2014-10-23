package Algorithms.algorithm.dp;

import java.util.ArrayList;

public class FindLargeCommonLine {
    public static void main(String[] str) {
        int[][] input = {
                {0, 1},
                {1, 0}
        };
        
        System.out.println(find(input));
    }
    
    public static class DpType {
        ArrayList<Boolean> set0;
        ArrayList<Boolean> set1;
        int max0;
        int max1;
        
        public DpType(int max0, int max1) {
            super();
            this.set0 = new ArrayList<Boolean>();
            this.set1 = new ArrayList<Boolean>();
            this.max0 = max0;
            this.max1 = max1;
        }        
    }
    
    public static int find(int[][] input) {
        if (input == null || input.length == 0 || input[0].length == 0) {
            return 0;
        }
        
        int rows = input.length;
        int cols = input[0].length;
        
        // create a DP
        int[] max1 = new int[cols];
        int[] max0 = new int[cols];
        
        // initate to be one, means the first line.
        int maxLine1 = 1;
        int maxLine0 = 1;
        
        for (int i = 0; i < cols; i++) {
            if (input[0][i] == 0) {
                // no change.
                max0[i] = 0;
                
                // should reverse.
                max1[i] = 1;
            } else {
                max0[i] = 1;
                max1[i] = 0;
            }
        }
        
        // Dp from the 2nd line to the last.
        for (int i = 1; i < rows; i++) {
            boolean is0 = true;
            for (int j = 0; j < cols; j++) {
                //if (input[])
            }
        }
        
        return 0;
    }

}
