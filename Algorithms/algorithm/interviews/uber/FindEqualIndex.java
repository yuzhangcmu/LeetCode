package Algorithms.algorithm.interviews.uber;

import java.util.ArrayList;

public class FindEqualIndex {
    public static void main(String[] str) {
        int[] input = {-4,-3,-2,-1,3,4,6,7,9,10,11,12};
        System.out.println(findEqual(input));
        
        int[] input2 = {-4,-3,-2,-1,3,4,6,7,8,9,10,11};
        System.out.println(findEqual(input2));
        
        int[] input3 = {-4, -3, -2, -1, 3, 4, 6, 7, 9, 10, 11, 13};
        System.out.println(findEqual2(input3));
    }

    public static ArrayList<Integer> findEqual(int[] input) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        
        int l = 0;
        int r = input.length - 1;
        
        // find the left bound.
        while (l < r - 1) {
            int mid = l + (r - l) / 2;
            
            // the value >= index.
            if (input[mid] >= mid) {
                r = mid;
            } else {
                l = mid;
            }
        }
        
        int leftBound = l == input[l] ? l: r;
        
        // bug:should reset l, r.
        l = 0;
        r = input.length - 1;
        
        // find the right bound.
        while (l < r - 1) {
            int mid = l + (r - l) / 2;
            
            // the value <= index.
            if (input[mid] <= mid) {
                // move right.
                l = mid;
            } else {
                // move left.
                r = mid;
            }
        }
        
        int rightBound = r == input[r] ? r: l;
        
        for (int i = leftBound; i <= rightBound; i++) {
            ret.add(i);
        }
        
        return ret;
    }
    
    /*
     * Author: He Long.
     */
    public static ArrayList<Integer> findEqual2(int[] A) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (A == null || A.length == 0) {
            return res;
        }
        int ll = 0;
        int lr = A.length - 1;
        while (ll <= lr) {
            int m = ll + (lr - ll) / 2;
            if (A[m] - m < 0) {
                ll = m + 1;
            } else {
                lr = m - 1;
            }
        }
        int rl = 0;
        int rr = A.length - 1;
        while (rl <= rr) {
            int m = rl + (rr - rl) / 2;
            if (A[m] - m <= 0) {
                rl = m + 1;
            } else {
                rr = m - 1;
            }
        }
        if (ll <= rr) {
            while (ll <= rr) {
                res.add(A[ll++]);
            }
        }
        return res;
    }
}
