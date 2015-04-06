package Algorithms.array;

public class RemoveDuplicates {
    public int removeDuplicates(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        // A里至少有1个元素 
        int len = 1;
        
        for (int i = 1; i < A.length; i++) {
            if (A[i] != A[i - 1]) {
                A[len++] = A[i];
            }
        }
        
        return len;
    }
}
