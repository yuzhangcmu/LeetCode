package Algorithms.array;

public class MaxProduct {
    public static int maxProduct(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        // record the max value in the last node.
        int DMax = A[0];
        
        // record the min value in the last node.
        int DMin = A[0];
        
        // This is very important, should recode the A[0] as the initial value.
        int max = A[0];
        
        for (int i = 1; i < A.length; i++) {
            int n1 = DMax * A[i];
            int n2 = DMin * A[i];
            
            // we can select the former nodes, or just discade them.
            DMax = Math.max(A[i], Math.max(n1, n2));
            max = Math.max(max, DMax);
            
            // we can select the former nodes, or just discade them.
            DMin = Math.min(A[i], Math.min(n1, n2));
        }
        
        return max;
    }
    
    /*
     * 2014.12.20 Redo
     * */
    public static int maxProduct2(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int max = 1;
        int min = 1;
        
        int ret = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            int n1 = max * A[i];
            int n2 = min * A[i];
            
            System.out.println("n1:" + n1);
            System.out.println("n2:" + n2);
            
            max = Math.max(A[i], Math.max(n1, n2));
            min = Math.min(A[i], Math.min(n1, n2));
            
            System.out.println("max:" + max);
            System.out.println("min:" + min);
            
            ret = Math.max(max, ret);
        }
        
        return ret;
    }
    
    /*
     * 作法是找到连续的正数，不断相乘即可。
     * */
    public static void main(String[] strs) {
        int[] A = {-2, 0, -1};
        
        System.out.println(maxProduct2(A));
    }
}
