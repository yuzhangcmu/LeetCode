package Algorithms.array;

/*
 * https://oj.leetcode.com/problems/search-in-rotated-sorted-array-ii/
 * 
 * Search in Rotated Sorted Array II Total Accepted: 18427 Total Submissions: 59728 My Submissions
Follow up for "Search in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?

Write a function to determine if a given target is in the array.
 * */
public class SearchInRotatedSortedArray2 {
    /*
        In this function, the complex may go to O(n). It depents on how many duplicates exit in the array.
        if there are M duplicates, the complexity may be O(logN + M).
    */
    public boolean search(int[] A, int target) {  
        if (A == null || A.length == 0) {
            return false;
        }
        
        // setup two point to the left and right of the array.
        int l = 0, r = A.length - 1;
        while (l <= r) {
            // get the mid point.
            int mid = l + (r - l)/2;
            if (target == A[mid]) {
                return true;
            }
            
            if (A[mid] > A[l]) {
                // the left side is sorted.
                if (target <= A[mid] && target >= A[l]) {
                    // target is in the left side.
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else if (A[mid] < A[l]) {
                // the right side is sorted.
                if (target <= A[r] && target >= A[mid]) {
                    // target is in the right side.
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            } else {
                // when A[mid] == A[l], we can't determin, just move the 
                // left point one
                l++;
            }
        }
        
        return false;
    }  
     
    /*
     * 2015.1.1 Redo:
     * */
    public boolean search1(int[] A, int target) {
        if (A == null || A.length == 0) {
            return false;
        }
        
        int l = 0;
        int r = A.length - 1;
        
        while (l < r - 1) {
            int mid = l + (r - l) / 2;
            
            if (A[mid] == target) {
                return true;
            }
            
            // left sort
            if (A[mid] > A[l]) {
                // out of range.
                if (target > A[mid] || target < A[l]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            // right sort.    
            } else if (A[mid] < A[l]) {
                // out of range.
                if (target < A[mid] || target > A[r]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                // move one node.
                l++;
            }
        }
        
        if (A[l] == target || A[r] == target) {
            return true;
        }
        
        return false;
    }
    
    // Version 2:
    public boolean search2(int[] A, int target) {
        if (A == null || A.length == 0) {
            return false;
        }
        
        int l = 0;
        int r = A.length - 1;
        
        while (l <= r) {
            int mid = l + (r - l) / 2;
            
            if (A[mid] == target) {
                return true;
            }
            
            // left sort
            if (A[mid] > A[l]) {
                // out of range.
                if (target > A[mid] || target < A[l]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            // right sort.    
            } else if (A[mid] < A[l]) {
                // out of range.
                if (target < A[mid] || target > A[r]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                // move one node.
                l++;
            }
        }
        
        return false;
    }
    
    // Version3: Drop the sides quicker.
    public boolean search3(int[] A, int target) {
        if (A == null) {
            return false;
        }
        
        int l = 0;
        int r = A.length - 1;
        
        while (l < r - 1) {
            int mid = l + (r - l) / 2;
            int value = A[mid];
            
            if (target == value) {
                return true;
            }
            
            // The right side is sorted.
            if (value < A[l]) {
                if (target > A[r] || target < value) {
                    // Drop the right side.
                    r = mid;
                } else {
                    // Drop the left side.
                    l = mid;
                }
            // The left side is sorted.    
            } else if (value > A[l]){
                if (target > value || target < A[l]) {
                    // drop the left side.
                    l = mid;
                } else {
                    r = mid;
                }
            } else {
                if (value > A[r]) {
                    // The right side is unordered, so the left side should be ordered.
                    if (target > value || target < A[l]) {
                    // drop the left side.
                        l = mid;
                    } else {
                        r = mid;
                    }
                }
                
                l++;
            }
        }
        
        if (A[l] == target) {
            return true;
        } else if (A[r] == target) {
            return true;
        }
        
        return false;
    }
}
