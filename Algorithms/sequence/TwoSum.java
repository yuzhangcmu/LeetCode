package Algorithms.sequence;

import java.util.HashMap;

/*
 * Two Sum Total Accepted: 36938 Total Submissions: 200732 My Submissions Question Solution 
Given an array of integers, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
 * */

public class TwoSum {
    public int[] twoSum(int[] numbers, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[] ret = new int[2];
        
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])) {
                
                // As the index is not ZERO based, we should add one to the result.
                ret[0] = map.get(target - numbers[i]) + 1;
                ret[1] = i + 1;
                return ret;
            }
            map.put(numbers[i], i);
        }
        
        return ret;
    }
}
