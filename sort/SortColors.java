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
    
    // Solution 3: use switch
    public void sortColors3(int[] A) {
        if (A == null || A.length == 0) {
            return;
        }
        
        int left = 0;
        
        // Bug 1: right is wrong.
        int right = A.length - 1;
        
        int cur = 0;
        
        // left: the first one which is not 0
        // right: the first one which is not 2
        // So we should use <= because right may be not dealed with.
        while (cur <= right) {
            switch (A[cur]) {
                case 0:
                    // Bug 0: Forget to add A.
                    swap(A, left, cur);
                    left++;
                    cur++;
                    break;
                case 1:
                    cur++;
                    break;
                case 2:
                    swap(A, cur, right);
                    right--;
                    // 这里不cur++的原因是，有可能从右边换过来有0，1还要继续处理
                    break;
                
                default:
                    cur++;
                    break;
            }
        }
    }
    
    public void swap(int[] A, int n1, int n2) {
        int tmp = A[n1];
        A[n1] = A[n2];
        A[n2] = tmp;
    }
    
    
}