package Algorithms.algorithm.interviews.uber;

import java.util.ArrayList;

public class FindEqualIndex {
    public static void main(String[] str) {
        int[] input = {-4,-3,-2,-1,3,4,6,7,9,10,11,12};
        System.out.println(findEqual(input));
        
        int[] input2 = {-4,-3,-2,-1,3,4,6,7,8,9,10,11};
        System.out.println(findEqual(input2));
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
}
