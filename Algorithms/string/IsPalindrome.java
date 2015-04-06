package Algorithms.string;

public class IsPalindrome {
    public boolean isPalindrome(String s) {
        // http://blog.csdn.net/fightforyourdream/article/details/12860445
        if (s == null) {
            return false;
        }
        
        int len = s.length();
        
        s = s.toLowerCase();
        
        int l = 0;
        int r = len - 1;
        while (l < r) {
            if (!isValid(s.charAt(l))) {
                l++;
            } else if (!isValid(s.charAt(r))) {
                r--;
            } else if (s.charAt(l) != s.charAt(r)) {
                return false;
            } else {
                l++;
                r--;
            }
        }
        
        return true;
    }
    
    public boolean isValid(char c) {
        return Character.isLetterOrDigit(c);
        // if (c <= 'z' && c >= 'a' || c <= 'Z' && c >= 'A'
        //   || c <= '9' && c >= '0') {
        //     return true;
        // }
        
        // return false;
    }
}
