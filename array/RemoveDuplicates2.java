package Algorithms.array;

public class RemoveDuplicates2 {
    public static int removeDuplicates(int[] A) {
        if (A == null) {
            return 0;
        }
        
        if (A.length <= 1) {
            return A.length;
        }
        
        int len = 1;
        
        // 拷贝2次后就不再拷贝
        boolean canCopy = true;
        
        for (int i = 1; i < A.length; i++) {
            if (A[i] == A[i - 1]) {
                if (!canCopy) {
                    continue;    
                }
                canCopy = false;
            } else {
                canCopy = true;
            }
            
            A[len++] = A[i];
        }
        
        return len;
    }
    
    public static void main(String[] strs) {
        int[] A = {1,1,1,2,2,3};
        removeDuplicates(A);
        
        for (int i: A) {
            System.out.print(i + " ");
        }
    }
}
