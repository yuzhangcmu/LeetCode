package Algorithms.lintcode.array;

/**
 * public class VersionControl {
 *     public static boolean isBadVersion(int k);
 * }
 * you can use VersionControl.isBadVersion(k) to judge wether 
 * the kth code version is bad or not.
*/
class Solution {
    /**
     * @param n: An integers.
     * @return: An integer which is the first bad version.
     */
    public int findFirstBadVersion(int n) {
        // write your code here
        if (n == 1) {
            return 1;
        }
        
        int left = 1;
        int right = n;
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (VersionControl.isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        
        if (VersionControl.isBadVersion(left)) {
            return left;
        }
        
        return right;
    }
    
    // solution 2.
    public int findFirstBadVersion2(int n) {
        // write your code here
        if (n == 1) {
            return 1;
        }
        
        int left = 1;
        int right = n;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (VersionControl.isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return right;
    }
}