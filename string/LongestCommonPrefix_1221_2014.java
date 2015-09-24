package Algorithms.string;

public class LongestCommonPrefix_1221_2014 {
    //http://blog.csdn.net/fightforyourdream/article/details/14642079
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            // bug 2: should not return null.
            return "";
        }
        
        // Find out the shortest length.
        String s0 = strs[0];
        int len = s0.length();
        for (String s: strs) {
            len = Math.min(len, s.length());
        }
        
        // The index of the character which is examing.
        // Bug 3: 当不会break的时候，结果是错的
        // Bug 4: forget to add int i = 0;
        for (int i = 0; i < len; i++) {
            // Bug 1: forget to write charAt(i);
            char c = s0.charAt(i);
            for (int j = 0; j < strs.length; j++) {
                if (strs[j].charAt(i) != c) {
                    // Bug 5: write substring to substring
                    return s0.substring(0, i);
                }                
            }
        }
        
        // Never break, means strs[0].0-len is the solution.
        return s0.substring(0, len);
    }
}