package Algorithms.algorithm.dp;

import java.util.ArrayList;

public class MinAdjustmentCost_Class {
    public static void main(String[] strs) {
        ArrayList<Integer> A = new ArrayList<Integer>();
        A.add(1);
        A.add(4);
        A.add(2);
        A.add(3);
        
        System.out.println(MinAdjustmentCost(A, 1));
    }
    
    /**
     * @param A: An integer array.
     * @param target: An integer.
     */
    public static int MinAdjustmentCost1(ArrayList<Integer> A, int target) {
        // write your code here
        if (A == null) {
            return 0;
        }
        
        return rec(A, new ArrayList<Integer>(A), target, 0);
    }
    
    /*
     * SOL 1:
     * 最普通的递归方法。
     * */
    public static int rec(ArrayList<Integer> A, ArrayList<Integer> B, int target, int index) {
        int len = A.size();
        if (index >= len) {
            // The index is out of range.
            return 0;
        }
        
        int dif = 0;
        
        int min = Integer.MAX_VALUE;
        
        // If this is the first element, it can be from 1 to 100;
        for (int i = 0; i <= 100; i++) {
            if (index != 0 && Math.abs(i - B.get(index - 1)) > target) {
                continue;
            }
            
            B.set(index, i);
            dif = Math.abs(i - A.get(index));
            dif += rec(A, B, target, index + 1);
            min = Math.min(min, dif);
            
            // 回溯
            B.set(index, A.get(index));
        }
        
        return min;
    }
    
    /*
     * 递归2：
     * Rec + memory.
     * */
    /**
     * @param A: An integer array.
     * @param target: An integer.
     */
    public static int MinAdjustmentCost(ArrayList<Integer> A, int target) {
        // write your code here
        if (A == null || A.size() == 0) {
            return 0;
        }
        
        int[][] M = new int[A.size()][100];
        for (int i = 0; i < A.size(); i++) {
            for (int j = 0; j < 100; j++) {
                M[i][j] = Integer.MAX_VALUE;
            }
        }
        
        return rec2(A, new ArrayList<Integer>(A), target, 0, M);
    }
    
    public static int rec2(ArrayList<Integer> A, ArrayList<Integer> B, int target, int index, 
           int[][] M) {
        int len = A.size();
        if (index >= len) {
            // The index is out of range.
            return 0;
        }
        
        int dif = 0;
        int min = Integer.MAX_VALUE;
        
        // If this is the first element, it can be from 1 to 100;
        for (int i = 1; i <= 100; i++) {
            if (index != 0 && Math.abs(i - B.get(index - 1)) > target) {
                continue;
            }
            
            if (M[index][i - 1] != Integer.MAX_VALUE) {
                dif = M[index][i - 1];
                min = Math.min(min, dif);
                continue;
            }
            
            B.set(index, i);
            dif = Math.abs(i - A.get(index));
            dif += rec2(A, B, target, index + 1, M);
            
            min = Math.min(min, dif);
            
            // Record the result.
            M[index][i - 1] = dif;
            
            // 回溯
            B.set(index, A.get(index));
        }
        
        return min;
    }

}
