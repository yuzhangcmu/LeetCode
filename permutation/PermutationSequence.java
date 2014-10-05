package Algorithms.permutation;

/*
 The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

    "123"
    "132"
    "213"
    "231"
    "312"
    "321"

Given n and k, return the kth permutation sequence.

Note: Given n will be between 1 and 9 inclusive.
 * */
public class PermutationSequence {
    public static String getPermutation(int n, int k) {
        if (n == 0) {
            return "";
        }
        
        // 先计算出(n)!
        int num = 1;
        for (int i = 1; i <= n; i++) {
            num *= i;
        }
        
        boolean[] use = new boolean[n];
        for (int i = 0; i < n; i++) {
            use[i] = false;
        }
        
        // 因为index是从0开始计算 
        k--;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            // 计算完第一个数字前，num要除以(n)
            num = num / (n - i);
            
            int index = k / num;
            k = k % num;
            
            for (int j = 0; j < n; j++) {                
                if (!use[j]) {
                    if (index == 0) {
                        // 记录下本次的结果.
                        sb.append((j + 1) + "");
                        use[j] = true;
                        break;
                    }
                    
                    // 遇到未使用过的数字，记录index
                    index--;
                }
            }
        }
        
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(getPermutation(3, 5));
    }

}
