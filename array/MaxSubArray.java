package Algorithms.array;

public class MaxSubArray {
    public int maxSubArray(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int len = A.length;
        int sum = 0;
        
        // 记录下最大值 
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            // 加上当前值 
            sum += A[i];
            max = Math.max(max, sum);
            
            // 如果和小于0，则可以丢弃之，下一个值重新计算即可。
            // 因为对于每一个值来说，有2处选择：加上前面的一些数，或是不加。如果是负数，可以不加。
            sum = Math.max(0, sum);
        }
        
        return max;
    }
}
