package Algorithms.string;

public class IsPalindrome_2014_1229 {
    /*
    SOLUTION 1: Iterator.
    */
    public boolean isPalindrome1(String s) {
        if (s == null) {
            return false;
        }
        
        int len = s.length();
        
        int left = 0;
        int right = len - 1;
        
        String sNew = s.toLowerCase();
        
        while (left < right) {
            // bug 1: forget a )
            while (left < right && !isNumChar(sNew.charAt(left))) {
                left++;
            }
            
            while (left < right && !isNumChar(sNew.charAt(right))) {
                right--;
            }
            
            if (sNew.charAt(left) != sNew.charAt(right)) {
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }
    
    public boolean isNumChar(char c) {
        if (c <= '9' && c >= '0' || c <= 'z' && c >= 'a' || c <= 'Z' && c >= 'A') {
            return true;
        }
        
        return false;
    }
    
    /*
    SOLUTION 2: Iterator2.
    */
    public boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }
        
        int len = s.length();
        
        int left = 0;
        int right = len - 1;
        
        String sNew = s.toLowerCase();
        
        while (left < right) {
            // bug 1: forget a )
            if (!Character.isLetterOrDigit(sNew.charAt(left))) {
                left++;
            // bug 2: Line 67: error: cannot find symbol: method isLetterOrDigital(char)    
            } else if (!Character.isLetterOrDigit(sNew.charAt(right))) {
                right--;
            } else if (sNew.charAt(left) != sNew.charAt(right)) {
                return false;
            } else {
                left++;
                right--;
            }
        }
        
        return true;
    }
}