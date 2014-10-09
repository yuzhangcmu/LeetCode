package Algorithms.array;

public class MergeSortedArray {
    public void merge(int A[], int m, int B[], int n) {
        int cur = m + n - 1;
        
        // 指向A的尾部
        int pA = m - 1;
        
        // 指向B的尾部
        int pB = n - 1;
        
        while (cur >= 0) {
            if (pA < 0 || pB < 0) {
                break;
            }
            
            // 从尾部往前比较
            if (A[pA] > B[pB]) {
                A[cur] = A[pA--];
            } else {
                A[cur] = B[pB--];
            }
            
            cur--;
        }
        
        // copy the left over elements in B to A.
        System.arraycopy(B, 0, A, 0, pB + 1);
        
        return;        
    }
}
