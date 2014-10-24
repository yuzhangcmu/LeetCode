package Algorithms.binarySearch;


/*
 * Find Minimum in Rotated Sorted Array II Total Accepted: 2541 Total Submissions: 9558 My Submissions Question Solution 
Follow up for "Find Minimum in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

The array may contain duplicates.
 * */
public class FindMin2 {
    public int findMin(int[] num) {
        if (num == null || num.length == 0) {
            return 0;
        }
        
        int len = num.length;
        if (len == 1) {
            return num[0];
        } else if (len == 2) {
            return Math.min(num[0], num[1]);
        }
        
        int left = 0;
        int right = len - 1;
        
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            // In this case, the array is sorted.
            // 这一句很重要，因为我们移除一些元素后，可能会使整个数组变得有序...
            if (num[left] < num[right]) {
                return num[left];
            }
            
            // left side is sorted. CUT the left side.
            if (num[mid] > num[left]) {
                left = mid;
            // left side is unsorted, right side is sorted. CUT the right side.
            } else if (num[mid] < num[left]) {
                right = mid;
            } else {
                left++;
            }
        }
        
        return Math.min(num[left], num[right]);        
    }
}
