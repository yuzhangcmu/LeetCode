package Algorithms.divide2;

public class SearchInsert {
    public int searchInsert1(int[] A, int target) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int left = 0;
        int right = A.length - 1;
        
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            int num = A[mid];
            
            if (num == target) {
                return mid;
            } else if (num < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        // bug 1: should use <=
        if (target <= A[left]) {
            return left;
        // bug 2: should use <= . consider that may the result exit in left or right.    
        } else if (target <= A[right]) {
            return right;
        }
        
        return right + 1;
    }
    
    // sol 2:
    public int searchInsert(int[] A, int target) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int left = 0;
        int right = A.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int num = A[mid];
            
            if (num == target) {
                return mid;
            } else if (num < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return left;
    }
}