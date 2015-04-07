package Algorithms.algorithm.dp;

import java.util.ArrayList;

public class MinAdjustmentCost_Class {
    public static void main(String[] strs) {
        ArrayList<Integer> A = new ArrayList<Integer>();
        A.add(1);
        A.add(4);
        A.add(2);
        A.add(3);
        
        System.out.println(MinAdjustmentCost1(A, 1));
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
        
        ArrayList<Integer> B = new ArrayList<Integer>();
        for (int i = 0; i < A.size(); i++) {
            B.add(0);
        }
        
        return rec(A, B, target, 0);
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
            //B.set(index, A.get(index));
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
    public static int MinAdjustmentCost2(ArrayList<Integer> A, int target) {
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
    
    /*
     * SOLUTION 3 递归2：
     * Rec + memory.
     * 改进的递归版本
     * */
    /**
     * @param A: An integer array.
     * @param target: An integer.
     */
    public static int MinAdjustmentCost3(ArrayList<Integer> A, int target) {
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
        
        // 首个数字可以取1-100
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= 100; i++) {
            min = Math.min(min, rec3(A, target, 0, i, M));
        }
        
        return min;
    }
    
    /*
     * 将当前值设置为x能求得的最小解 
     * */
    public static int rec3(ArrayList<Integer> A, int target, int index, int x, 
           int[][] M) {
        int len = A.size();
        if (index >= len) {
            // The index is out of range.
            return 0;
        }
        
        if (M[index][x - 1] != Integer.MAX_VALUE) {
            return M[index][x - 1];
        }
        
        int bas = Math.abs(x - A.get(index));
        int min = Integer.MAX_VALUE;
        
        // 对下一个值尝试取1-100
        for (int i = 1; i <= 100; i++) {
            // 下一个值的取值不可以超过abs
            if (index != len - 1 && Math.abs(i - x) > target) {
                continue;
            }
            
            // 计算dif 
            int dif = bas + rec3(A, target, index + 1, i, M);
            min = Math.min(min, dif);
        }
        
        // Record the result.
        M[index][x - 1] = min;
        return min;
    }


    /*
     * SOLUTION 4：
     * DP
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
        
        // D[i][v]: 把index = i的值修改为v，所需要的最小花费
        int[][] D = new int[A.size()][101];
        
        int size = A.size();
        
        for (int i = 0; i < size; i++) {
            for (int j = 1; j <= 100; j++) {
                D[i][j] = Integer.MAX_VALUE;
                if (i == 0) {
                    // The first element.
                    D[i][j] = Math.abs(j - A.get(i));
                } else {
                    for (int k = 1; k <= 100; k++) {
                        // 不符合条件 
                        if (Math.abs(j - k) > target) {
                            continue;
                        }
                        
                        int dif = Math.abs(j - A.get(i)) + D[i - 1][k];
                        D[i][j] = Math.min(D[i][j], dif);
                    }
                }
            }
        }
        
        int ret = Integer.MAX_VALUE;
        for (int i = 1; i <= 100; i++) {
            ret = Math.min(ret, D[size - 1][i]);
        }
        
        return ret;
    }
}
