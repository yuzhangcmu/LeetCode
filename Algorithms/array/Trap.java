package Algorithms.array;

public class Trap {
    public int trap1(int[] A) {
        if (A == null) {
            return 0;
        }
        
        int max = 0;
        
        int len = A.length;
        int[] left = new int[len];
        int[] right = new int[len];
        
        // count the highest bar from the left to the current.
        for (int i = 0; i < len; i++) {
            left[i] = i == 0 ? A[i]: Math.max(left[i - 1], A[i]);
        }
        
        // count the highest bar from right to current.
        for (int i = len - 1; i >= 0; i--) {
            right[i] = i == len - 1 ? A[i]: Math.max(right[i + 1], A[i]);
        }
        
        // count the largest water which can contain.
        for (int i = 0; i < len; i++) {
            int height = Math.min(right[i], left[i]);
            if (height > A[i]) {
                max += height - A[i];
            }
        }
        
        return max;
    }
    
    public int trap(int[] A) {
        // 2:37
        if (A == null) {
            return 0;
        }
        
        int len = A.length;
        int[] l = new int[len];
        int[] r = new int[len];
        
        for (int i = 0; i < len; i++) {
            if (i == 0) {
                l[i] = A[i];
            } else {
                l[i] = Math.max(l[i - 1], A[i]);
            }
        }
        
        int water = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (i == len - 1) {
                r[i] = A[i];
            } else {
                // but: use Math, not max
                r[i] = Math.max(r[i + 1], A[i]);
            }
            
            water += Math.min(l[i], r[i]) - A[i];
        }
        
        return water;        
    }
}