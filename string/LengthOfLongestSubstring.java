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
            
            map.put(c, end);
            
            int subLen = end - start + 1;
            max = Math.max(max, subLen);
        }
        
        return max;
    }
    
    public int lengthOfLongestSubstring1(String s) {
        if (s == null) {
            return 0;
        }
        
        int max = 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        
        int len = s.length();
        int l = 0;
        for (int r = 0; r < len; r++) {
            char c = s.charAt(r);
            
            if (map.containsKey(c) && map.get(c) >= l) {
                l = map.get(c) + 1;
            }
            
            // replace the last index of the character c.
            map.put(c, r);
            
            // replace the max value.
            max = Math.max(max, r - l + 1);
        }
        
        return max;
    }
    
    // SOLUTION 2: use the array.
    public int lengthOfLongestSubstring2(String s) {
        if (s == null) {
            return 0;
        }
        
        int max = 0;
        
        // suppose there are only ASCII code.
        int[] lastIndex = new int[128];
        for (int i = 0; i < 128; i++) {
            lastIndex[i] = -1;
        }
        
        int len = s.length();
        int l = 0;
        for (int r = 0; r < len; r++) {
            char c = s.charAt(r);
            
            if (lastIndex[c] >= l) {
                l = lastIndex[c] + 1;
            }
            
            // replace the last index of the character c.
            lastIndex[c] = r;
            
            // replace the max value.
            max = Math.max(max, r - l + 1);
        }
        
        return max;
    }
}