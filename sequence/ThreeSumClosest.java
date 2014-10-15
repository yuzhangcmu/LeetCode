package Algorithms.sequence;

public class ThreeSumClosest {
    public int threeSumClosest(int[] num, int target) {
        if (num == null) {
            return 0;
        }
        
        int len = num.length;
        
        int diffMin = Integer.MAX_VALUE;
        
        int ret = 0;
        for (int i = 0; i < len; i++) {
            
            int l = i + 1;
            int r = len - 1;
            
            while (l < r) {
                int diff = target - (num[i] + num[l] + num[r]);
                
                if (Math.abs(diff) < diffMin) {
                    diffMin = Math.abs(diff);
                    ret = num[i] + num[l] + num[r];
                }
                
                if (diff > 0) {
                    // move right;
                    l++;
                } else if (diff < 0) {
                    // move left;
                    r--;
                } else {
                    // We get the 0 now. There is no way that it would be less than 0.
                    return ret;
                }
            }
        }
        
        return ret;
    }
}