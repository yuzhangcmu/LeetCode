package Algorithms.algorithm.others;

public class NumTree {
    public static void main(String[] strs) {
        NumTree nt = new NumTree();
        nt.numTrees(1);
    }
    
    public int numTrees(int n) {
        // 0, 1, 2, 3...
        int num[] = new int[n + 1];
        
        // n3 = n0*n2 + n1*n1 + n2*n0    0~ n-1
        // n2 = n0*n1 + n1*n0
        // n1 = n0*n0
        // n0 = 1
        for (int i = 0; i < n + 1; i++) {
            if (i == 0) {
                // if there is only 0 or 1 element, the result should be 1;
                num[i] = 1;
            } else {
                for (int j = 0; j < i; j++) {
                    num[i] += num[j]*num[i - 1 - j];
                }
            }
        }
        
        return num[n];        
    }

}
