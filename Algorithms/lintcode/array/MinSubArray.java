package Algorithms.lintcode.array;

import java.util.ArrayList;

public class MinSubArray {
    /**
     * @param nums: a list of integers
     * @return: A integer indicate the sum of minimum subarray
     */
    public int minSubArray(ArrayList<Integer> nums) {
        // write your code
        
        int len = nums.size();
        
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            if (sum < 0) {
                sum = -nums.get(i);
            } else {
                sum += -nums.get(i);
            }
            
            max = Math.max(max, sum);
        }
        
        return -max;
    }
}

