package Algorithms.algorithm.NChapter.findKthNumber;

import java.util.Arrays;

/*
 * Version 1: The pivot is in the left.
 * */

public class FindKthNumber {
    public static void main(String[] strs) {
        int[] A1 = new int[]{1,6,9,2,3,5};
        FindKthNumber find = new FindKthNumber();
        int rst = find.findKthNumber(A1, 5);
        System.out.println(rst);
        
        System.out.println("after find");
        for (int i = 0; i < A1.length; i++) {
            System.out.print(A1[i]);
            System.out.print(" ");
        }
        
        System.out.println();
        
        Arrays.sort(A1);
        System.out.println("After sort");
        for (int i = 0; i < A1.length; i++) {
            System.out.print(A1[i]);
            System.out.print(" ");
        }
    }
    
    public int findKthNumber(int A[], int k) {
        
        return findKthNumberHelp(A, A.length + 1 - k, 0, A.length - 1);
    }
    
    public int findKthNumberHelp(int A[], int k, int start, int end) {
        int left = start;
        int right = end;
        int pivot = left;
        
        while (left <= right) {
            while (left <= right && A[left] <= A[pivot]) {
                left++;
            }
            
            while (left <= right && A[right] >= A[pivot]) {
                right--;
            }
                
            if (left < right) {
                swap(A, left, right);
            }
        }
        
        swap(A, pivot, right);
        
        if (right + 1 == k) {
            return A[right];
        }
        
        if (right + 1 > k) {
            // find in the left side.
            return findKthNumberHelp(A, k, start, right - 1);
        } else {
            return findKthNumberHelp(A, k, right + 1, end);
        }
    }
    
    private void swap(int A[], int one, int two) {
        int tmp = A[one];
        A[one] = A[two];
        A[two] = tmp;
    }
}
