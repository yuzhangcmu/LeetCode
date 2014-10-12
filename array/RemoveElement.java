package Algorithms.array;

public class RemoveElement {
    public int removeElement(int[] A, int elem) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int cur = 0;
        int len = 0;
        while (cur < A.length) {
            if (A[cur] != elem) {
                A[len++] = A[cur++];
            } else {
                cur++;
            }
        }
        
        return len;
    }
}
