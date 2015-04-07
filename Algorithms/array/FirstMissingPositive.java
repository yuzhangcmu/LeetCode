package Algorithms.array;

public class FirstMissingPositive {
    public static void main(String[] strs) {
        int[] in = {1,4,2,3};
        //int[] in = {3,4,-1,1};        
        System.out.println(firstMissingPositive(in));
    }
    
    public static int firstMissingPositive1(int[] A) {
        if (A == null) {
            return 0;
        }
        
        int len = A.length;
        for (int i = 0; i < len; i++) {
            // 1. The number should be in the range.
            // 2. The number should be positive.
            while (A[i] <= len && A[i] > 0 && A[A[i] - 1] != A[i]) {
                swap(A, i, A[i] - 1);
            }
        }
        
        for (int i = 0; i < len; i++) {
            if (A[i] != i + 1) {
                return i + 1;
            }
        }
        
        return len + 1;
    }
    
    public static void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
    
    // SOLUTION 2:
    public static int firstMissingPositive(int[] A) {
        // bug 3: when length is 0, return 1;
        if (A == null) {
            return 0;
        }
        
        for (int i = 0; i < A.length; i++) {
            // 1: A[i] is in the range;
            // 2: A[i] > 0.
            // 3: The target is different;
            while (A[i] <= A.length && A[i] > 0 && A[A[i] - 1] != A[i]) {
                swap(A, i, A[i] - 1);    
            }
        }
        
        for (int i = 0; i < A.length; i++) {
            if (A[i] != i + 1) {
                return i + 1;
            }
        }
        
        return A.length + 1;
    }
}