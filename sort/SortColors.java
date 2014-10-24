package Algorithms.sort;

public class SortColors {
    public void sortColors(int[] A) {
        if (A == null || A.length == 0) {
            return;
        }
        
        int len = A.length;
        
        int red = 0;
        int white = 0;
        
        for (int i = 0; i < len; i++) {
            if (A[i] == 0) {
                red++;    
            } else if (A[i] == 1) {
                white++;
            }
        }
        
        for (int i = 0; i < len; i++) {
            if (red > 0) {
                A[i] = 0;
                red--;
            } else if (white > 0) {
                A[i] = 1;
                white--;
            } else {
                A[i] = 2;
            }
        }
    }
    
    public void sortColors2(int[] A) {
        if (A == null || A.length == 0) {
            return;
        }
        
        int len = A.length - 1;
        int left = 0;
        int right = len;
        
        int cur = 0;
        while (cur <= right) {
            if (A[cur] == 2) {
                // 换到右边，换过来的有可能是0，也有可能是1，所以cur要停留
                
                swap(A, cur, right);
                right--;
            } else if (A[cur] == 0) {
                // 从左边换过来的只可能是1，所以可以直接cur++
                // 因为所有的2全部换到右边去了。
                
                swap(A, cur, left);
                left++;
                cur++;
            } else {
                cur++;
            }
        }
    }
    
    public void swap(int[] A, int n1, int n2) {
        int tmp = A[n1];
        A[n1] = A[n2];
        A[n2] = tmp;
    }
}