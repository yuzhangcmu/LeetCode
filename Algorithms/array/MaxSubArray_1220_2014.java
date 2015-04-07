package Algorithms.array;

public class MaxSubArray_1220_2014 {
    public int maxSubArray(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int max = Integer.MIN_VALUE;
        int sum = 0;
        
        int len = A.length;
        for (int i = 0; i < len; i++) {
            if (sum < 0) {
                sum = 0;
            }
            
            sum += A[i];
            max = Math.max(max, sum);
        }
        
        return max;
    }
}