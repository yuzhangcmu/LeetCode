package Algorithms.array;

public class SearchInRotatedSortedArray {
    public int search(int[] A, int target) {
        if (A == null || A.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = A.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (A[mid] == target) {
                return mid;
            }
            
            // the left side is sorted.
            if (A[mid] >= A[left]) {
                if (target <= A[mid] && target >= A[left]) {
                    // target is in the left side.
                    right = mid - 1;
                } else {
                    // target is in the right side.
                    left = mid + 1;
                }
            } else { // the right side is sorted.
                if (target >= A[mid] && target <= A[right]) {
                    // target is in the right side.
                    left = mid + 1;
                } else {
                    // target is in the right side.
                    right = mid - 1;
                }
            }
        }
        
        return -1;
    }
    
    // 2015.1.1 redo:
    public int search1(int[] A, int target) {
        if (A == null || A.length == 0) {
            return -1;
        }
        
        int l = 0;
        int r = A.length - 1;
        
        while (l < r - 1) {
            int mid = l + (r - l) / 2;
            
            if (A[mid] == target) {
                return mid;
            }
            
            // left side is sorted.
            // BUG 1: if don't use >= , and use L < r in while loop, than there is some problem.
            if (A[mid] > A[l]) {
                if (target > A[mid] || target < A[l]) {
                    // move to right;
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            } else {
                if (target < A[mid] || target > A[r]) {
                    // move to left;
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }
        
        if (A[l] == target) {
            return l;
        } else if (A[r] == target) {
            return r;
        }
        
        return -1;
    }
    
    public int search2(int[] A, int target) {
        if (A == null || A.length == 0) {
            return -1;
        }
        
        int l = 0;
        int r = A.length - 1;
        
        while (l <= r) {
            int mid = l + (r - l) / 2;
            
            if (A[mid] == target) {
                return mid;
            }
            
            // left side is sorted.
            // BUG 1: if don't use >= , and use L < r in while loop, than there is some problem.
            if (A[mid] >= A[l]) {
                if (target > A[mid] || target < A[l]) {
                    // move to right;
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            } else {
                if (target < A[mid] || target > A[r]) {
                    // move to left;
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }
        
        return -1;
    }
}
