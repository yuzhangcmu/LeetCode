package Algorithms.lintcode.math;

import java.util.ArrayList;

public class MajorityNumber {
    /**
     * @param nums: a list of integers
     * @return: find a  majority number
     */
    public int majorityNumber(ArrayList<Integer> nums) {
        // write your code
        if (nums == null || nums.size() == 0) {
            // No majority number.
            return -1;
        }
        
        int candidate = nums.get(0);
        
        // The phase 1: Voting.
        int cnt = 1;
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) == candidate) {
                cnt++;
            } else {
                cnt--;
                if (cnt == 0) {
                    candidate = nums.get(i);
                    cnt = 1;
                }
            }
        }
        
        // The phase 2: Examing.
        cnt = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) == candidate) {
                cnt++;
            }
        }
        
        // No majory number.
        if (cnt <= nums.size() / 2) {
            return -1;
        }
        
        return candidate;
    }
}

