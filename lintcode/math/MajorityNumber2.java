package Algorithms.lintcode.math;

import java.util.ArrayList;

public class MajorityNumber2 {
    /**
     * @param nums: A list of integers
     * @return: The majority number that occurs more than 1/3
     */
    public int majorityNumber(ArrayList<Integer> nums) {
        // write your code
        // When there are only 1 or 2 elements in the array,
        // there is no solution.
        if (nums == null || nums.size() <= 2) {
            return -1;
        }
        
        int n1 = 0;
        int n2 = 0;
        
        int cnt1 = 0;
        int cnt2 = 0;
        
        int size = nums.size();
        for (int i = 0; i < size; i++) {
            int num = nums.get(i);
            if (cnt1 != 0 && num == n1) {
                cnt1++;
            } else if (cnt2 != 0 && num == n2) {
                cnt2++;
            } else if (cnt1 == 0) {
                cnt1 = 1;
                n1 = num;
            } else if (cnt2 == 0) {
                cnt2 = 1;
                n2 = num;
            } else {
                cnt1--;
                cnt2--;
            }
        }
        
        // count the two candiates.
        cnt1 = 0;
        cnt2 = 0;
        for (int num: nums) {
            if (num == n1) {
                cnt1++;
            } else if (num == n2) {
                cnt2++;
            }
        }
        
        if (cnt1 < cnt2) {
            return n2;
        }        
        
        return n1;
    }
}



