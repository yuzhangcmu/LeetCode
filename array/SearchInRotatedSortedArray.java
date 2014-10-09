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
}
