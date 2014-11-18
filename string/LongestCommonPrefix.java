package Algorithms.string;

public class LongestCommonPrefix {
    //http://blog.csdn.net/fightforyourdream/article/details/14642079
    public String longestCommonPrefix(String[] strs) {
        if (strs == null) {
            return null;
        }
        
        if (strs.length == 0) {
            return "";
        }
        
        String s = strs[0];
        int len = s.length();
        
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() <= i || c != strs[j].charAt(i)) {
                    // The char i is invalid. 因为读到i时退出，所以不应包含i本身。
                    return s.substring(0, i);
                }
            }
        }
        
        // Didn't break, the whole String is valid.
        return s;
    }
}
