package Algorithms.lintcode.array;

class Rerange {
    public static void main(String[] strs) {
        int A[] = {1,2,3,4,-1, 2, 5, 5, 5, 6, -7,-8,843,-23,1}; 
        rerange(A);

        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i] + " ");
        }
    }
   
   /**
     * @param A: An integer array.
     * @return an integer array
     */
    // SOLUTION 2: 判断正数多还是负数多。 
    public static int[] rerange(int[] A) {
        // write your code here
        
        // Check the input parameter.
        if (A == null || A.length == 0) {
            return A;
        }
        
        int len = A.length;
        
        int left = -1;
        int right = A.length;
        
        // divide the negative and positive integers.
        while (true) {
            while (left < A.length - 1 && A[++left] < 0);
            
            while (left < right && A[--right] > 0);
            
            if (left >= right) {
                break;
            }
            
            swap(A, left, right);
        }
        
        // LEFT: point to the first positive number.
        int negNum = left;
        int posNum = len - left;
        
        int les = Math.min(negNum, posNum);
        int dif = Math.abs(negNum - posNum);
        
        // 如果负数比较多，把多的负数扔到后面去
        if (negNum > posNum) {
            int cnt = dif;
            int l = les;
            int r = len - 1;
            while (cnt > 0) {
                swap(A, l, r);
                l++;
                r--;
                cnt--;
            }
            
            // 负数多的时候，负数在前，反之，正数在前
            left = -1;
            // 跳过右边不需要交换的值
            right = A.length - dif;
        } else {
            // 正数在前
            left = -2;
            // 跳过右边不需要交换的值
            right = A.length - dif + 1;
        }
        
        /*
          -1 -2 -5 -6  3  4  les = 2;
          4  -2 -5 -6  3 -1
        */
        // swap the negative and the positive
        while (true) {
            left += 2;
            right -= 2;
            
            if (left >= les) {
                break;
            }
            swap(A, left, right);
        }

        return A;
   }
   
   public static void swap(int[] A, int n1, int n2) {
       int tmp = A[n1];
       A[n1] = A[n2];
       A[n2] = tmp;
   }
}
