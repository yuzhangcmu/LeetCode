package Algorithms.divide2;

public class SearchRange {
    public int[] searchRange(int[] A, int target) {
        int[] ret = {-1, -1};
        if (A == null || A.length == 0) {
            return ret;
        }
        
        int l = 0;
        int r = A.length - 1;
        
        // Find the left bound.
        while (l < r - 1) {
            int mid = l + (r - l) / 2;
            if (A[mid] == target) {
                r = mid;
            } else if (A[mid] > target) {
                r = mid;
            } else {
                l = mid;
            }
        }
        
        if (A[l] == target) {
            ret[0] = l;
        } else if (A[r] == target) {
            ret[0] = r;
        } else {
            return ret;
        }
        
        l = 0;
        r = A.length - 1;
        // Find the right bound.
        while (l < r - 1) {
            int mid = l + (r - l) / 2;
            if (A[mid] == target) {
                l = mid;
            } else if (A[mid] > target) {
                r = mid;
            } else {
                l = mid;
            }
        }
        
        if (A[r] == target) {
            ret[1] = r;
        } else {
            ret[1] = l;
        }
        
        return ret;
    }
}