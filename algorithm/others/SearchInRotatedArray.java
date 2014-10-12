package Algorithms.algorithm.others;

public class SearchInRotatedArray {
    public static void main(String[] args) {
        SearchInRotatedArray sa = new SearchInRotatedArray();
        
        int A[] = {3,1,1};
        sa.search2(A, 3);
    }
    
    public int search(int[] A, int target) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int low = 0;
        int up = A.length - 1;
        int mid;
        
        while (true) {
            if (low > up) {
                return -1;
            }
            
            mid = low + (up - low) / 2;
            if (A[mid] == target) {
                return mid;
            }
            
            if (A[low] < A[mid]) {
                // the left side is sorted.
                if (A[mid] > target && A[low] < target) {
                    // target is in the sorted side.
                    up = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                // the right side is sorted.
                if (A[up] > target && A[mid] < target) {
                    // target is in the sorted side.
                    low = mid + 1;
                } else {
                    up = mid - 1;
                }
            }
        }
    }
    
    public boolean search2(int[] A, int target) {
        if (A == null || A.length == 0) {
            return false;
        }
        
        int l = 0;
        int r = A.length - 1;
        int mid;
        
        while (l <= r) {
            mid = l + (r - l)/2;    
            if (A[mid] == target) {
                return true;
            }
            
            if (A[mid] > A[l]) { // left side is sorted.
                if (target >= A[l] && target < A[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else if (A[mid] < A[r]) { // right side is sorted.
                if (target > A[mid] && target <= A[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            } else {
                l++;
            }
        }   
        
        return false;
    }
}
