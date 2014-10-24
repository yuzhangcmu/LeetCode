package Algorithms.binarySearch;

public class FindMin {
    // Solution 1:
    public int findMin1(int[] num) {
        if (num == null || num.length == 0) {
            return 0;
        }
        
        if (num.length == 1) {
            return num[0];
        }
        
        
        // 至少有2个元素，才有讨论的价值 
        int l = 0;
        int r = num.length - 1;
        
        while (l < r) {
            int mid = l + (r - l)/2;
            // Means that there is no rotate.
            if (num[mid] >= num[l] && num[mid] <= num[r]) {
                return num[0];
            }
            
            // rotate > 0的情况 
            if (l == r - 1) {
                // 当只余下2个元素的时候,这里是断点，右边的是小值
                return num[r];
            }
            
            if (num[mid] >= num[l]) {
                // The left side is sorted. Discard left.
                l = mid;
            } else {
                // The right side is sorted.
                r = mid;
            }
        }
        
        return 0;
    }
    
    // solution 2:
    public int findMin(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        if (A.length == 1) {
            return A[0];
        } else if (A.length == 2) {
            return Math.min(A[0], A[1]);
        }
        
        // 至少有3个元素，才有讨论的价值 
        int l = 0;
        int r = A.length - 1;
        
        while (l < r - 1) {
            int m = l + (r - l) / 2;
            
            // means that there is no rotate.
            if (A[r] > A[l]) {
                return A[0];
            }
            
            // left side is sorted.
            if (A[m] > A[l]) {
                l = m;
            } else {
                r = m;
            }
        }
        
        return A[r];
    }
}