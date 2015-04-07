package Algorithms.algorithm.NChapter.findKthNumber;


public class FindMedianSortedArrays {
    public static void main(String[] str) {
        int A[] = {1,2,3,3};
        int B[] = {3,3,7,8};
        double ret = findMedianSortedArrays(A, B);
        System.out.println(ret);
    }
    
    public static double findMedianSortedArrays(int A[], int B[]) {
        double ret = findKth(A, 0, B, 0, 6);
        return ret;
        
        /*
        if (len % 2 == 0) {
            return (findKth(A, 0, B, 0, len / 2) + findKth(A, 0, B, 0, len / 2 + 1)) / 2.0 ;
        } else {
            return findKth(A, 0, B, 0, len / 2 + 1);
        }*/
    }
    
    // find kth number of two sorted array
    public static int findKth(int[] A, int A_start, int[] B, int B_start, int k){       
        if(A_start >= A.length) 
            return B[B_start + k - 1];
        if(B_start >= B.length)
            return A[A_start + k - 1];

        if (k == 1)
            return Math.min(A[A_start], B[B_start]);
        
        int A_key = A_start + k / 2 - 1 < A.length
                    ? A[A_start + k / 2 - 1]
                    : Integer.MAX_VALUE;
        int B_key = B_start + k / 2 - 1 < B.length
                    ? B[B_start + k / 2 - 1]
                    : Integer.MAX_VALUE; 
        
        if (A_key < B_key) {
            return findKth(A, A_start + k / 2, B, B_start, k - k / 2);
        } else {
            return findKth(A, A_start, B, B_start + k / 2, k - k / 2);
        }
    }
}