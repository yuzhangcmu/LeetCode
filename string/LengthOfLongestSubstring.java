package Algorithms.string;

import java.util.HashMap;

public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return 0;
        }
        
        int len = s.length();
        
        // The start of the window.
        int start = 0;
        int max = 0;
        
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        
        for (int end = 0; end < len; end++) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                if (map.get(c) >= start) {
                    start = map.get(c) + 1;
                }
            }
            
            //更新map里的char 的位置
            map.put(c, end);
            
            int subLen = end - start + 1;
            max = Math.max(max, subLen);
        }
        
        return max;
    }
}