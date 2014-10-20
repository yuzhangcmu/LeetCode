package Algorithms.array;

public class FirstMissingPositive {
    public static void main(String[] strs) {
        int[] in = {1,2,0};
        //int[] in = {3,4,-1,1};        
        System.out.println(firstMissingPositive(in));
    }
    
    public static int firstMissingPositive(int[] A) {
        if (A == null) {
            return 0;
        }
        
        /*
        使用桶排序，将数字放置在正确的位置，如果最后发现在某个位置不存在正确的数字，说明这个数字是缺失的。
        在排序过程中，因为要不断交换直到不能再交换为止，所以遇到目标位置已经有正确数字时，不要再交换，以
        免发生死循环。
        */
        int len = A.length;
        for (int i = 0; i < len; i++) {
            // 1. The number should be in the range.
            // 2. The number should be positive.
            // 注意：和将要交换的值不可以相同，否则会死循环 
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
}