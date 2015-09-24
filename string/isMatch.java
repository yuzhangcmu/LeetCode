package Algorithms.string;

public class isMatch {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        
        return isMatchRec(s, p, 0, 0);
    }
    
    public boolean isMatchRec(String s, String p, int indexS, int indexP) {
        int lenS = s.length();
        int lenP = p.length();
        
        // we get to the end of the string.
        if (indexP == lenP) {
            return indexS == lenS;
        }
        
        // At lease 2 match character left
        if (indexP < lenP - 1 && p.charAt(indexP + 1) == '*') {
            // match 0;
            if (isMatchRec(s, p, indexS, indexP + 2)) {
                return true;
            }
            
            // we can match 0 or more.
            for (int i = indexS; i < lenS; i++) {
                // match once or more.
                if (!isMatchChar(s.charAt(i), p.charAt(indexP))) {
                    return false;
                }
                
                if (isMatchRec(s, p, i + 1, indexP + 2)) {
                    return true;
                }
            }
            
            // if any of them does not match, just return false.
            return false;
        }
        
        // match current character and the left string.
        return indexS < lenS 
                 && isMatchChar(s.charAt(indexS), p.charAt(indexP)) 
                 && isMatchRec(s, p, indexS + 1, indexP + 1);
    }
    
    public boolean isMatchChar(char s, char p) {
        if (p == '*') {
            return false;
        }
        
        if (s == p || p == '.') {
            return true;
        }
        
        return false;
    }
    
}