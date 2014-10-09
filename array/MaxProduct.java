package Algorithms.array;

public class MaxProduct {
    public static int maxProduct(int[] A) {
        int max = 0;
           
        int len = A.length;
        
        int product = 1;
        
        for (int i = 0; i < len; i++) {
            if (A[i] <= 0) {
                max = Math.max(max, product);
                product = 1;
                continue;
            }
                        
            product *= A[i];
        }
        
        return max;
    }
    
    /*
     * 作法是找到连续的正数，不断相乘即可。
     * */
    public static void main(String[] strs) {
        int[] A = {2, 3, -2, 4};
        
        System.out.println(maxProduct(A));
    }
}
