package Algorithms.string;

public class isMatch_2014_1228 {
    // Solution 2: DFS.
    public boolean isMatch1(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        
        return dfs(s, p, 0, 0);
    }
    
    public boolean dfs(String s, String p, int indexS, int indexP) {
        int lenS = s.length();
        int lenP = p.length();
        
        // THE BASE CASE:
        if (indexP >= lenP) {
            // indexP is out of range. Then the s should also be empty.
            return indexS >= lenS;
        }
        
        // The first Case: next node is *
        if (indexP != lenP - 1 && p.charAt(indexP + 1) == '*') {
            // p can skip 2 node, and the S can skip 0 or more characters.
            if (dfs(s, p, indexS, indexP + 2)) {
                return true;
            }
            
            for (int i = indexS; i < lenS; i++) {
                // the char is not equal.
                // bug 2: Line 31: java.lang.StringIndexOutOfBoundsException: String index out of range: -1
                if (!isSame(s.charAt(i), p.charAt(indexP))) {
                    return false;
                }
                
                if (dfs(s, p, i + 1, indexP + 2)) {
                    return true;
                }
            }
            
            // Not any of them can match.
            return false;
        } else {
            // S should have at least one character left.
            if (indexS >= lenS) {
                return false;
            }
            
            if (!isSame(s.charAt(indexS), p.charAt(indexP))) {
                return false;
            }
            
            // bug 1: forget ';'
            return dfs(s, p, indexS + 1, indexP + 1);
        }
    }
    
    public boolean isSame(char c, char p) {
        return p == '.' || c == p;
    }
    
    // solution3: dfs + memory
    public boolean isMatch2(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        
        int[][] mem = new int[s.length() + 1][p.length() + 1];
        
        // BUG 1: forget to init the memory array.
        // BUG 2: the corner is <=
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j <= p.length(); j++) {
                mem[i][j] = -1;
            }
        }
        
        return dfsMem(s, p, 0, 0, mem);
    }
    
    public boolean dfsMem(String s, String p, int indexS, int indexP, int[][] mem) {
        int lenS = s.length();
        int lenP = p.length();
        
        if (mem[indexS][indexP] != -1) {
            return mem[indexS][indexP] == 1;
        }
        
        // THE BASE CASE:
        if (indexP >= lenP) {
            // indexP is out of range. Then the s should also be empty.
            mem[indexS][indexP] = indexS >= lenS ? 1: 0;
            return indexS >= lenS;
        }
        
        // The first Case: next node is *
        if (indexP != lenP - 1 && p.charAt(indexP + 1) == '*') {
            // p can skip 2 node, and the S can skip 0 or more characters.
            if (dfsMem(s, p, indexS, indexP + 2, mem)) {
                mem[indexS][indexP] = 1; 
                return true;
            }
            
            for (int i = indexS; i < lenS; i++) {
                // the char is not equal.
                // bug 2: Line 31: java.lang.StringIndexOutOfBoundsException: String index out of range: -1
                if (!isSame(s.charAt(i), p.charAt(indexP))) {
                    mem[indexS][indexP] = 0;
                    return false;
                }
                
                if (dfsMem(s, p, i + 1, indexP + 2, mem)) {
                    mem[indexS][indexP] = 1;
                    return true;
                }
            }
            
            // Not any of them can match.
            mem[indexS][indexP] = 0;
            return false;
        } else {
            // S should have at least one character left.
            boolean ret =  indexS < lenS 
                && isSame(s.charAt(indexS), p.charAt(indexP))
                && dfsMem(s, p, indexS + 1, indexP + 1, mem);
            
            mem[indexS][indexP] = ret ? 1: 0;
            return ret;
        }
    }
    
    // solution4: DP
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        
        // bug 2: should use boolean instead of int.
        boolean[][] D = new boolean[s.length() + 1][p.length() + 1];
        
        // D[i][j]: i, j, the length of String s and String p.        
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j <= p.length(); j++) {
                if (j == 0) {
                    // when p is empth, the s should be empty.
                    D[i][j] = i == 0;
                } else if (p.charAt(j - 1) == '*') {
                    /*
                        P has at least one node.
                    */
                    
                    // The last node in p is '*'
                    if (j < 2) {
                        // a error: there should be a character before *.
                        //return false;
                    }
                    
                    // we can match 0 characters or match more characters.
                    for (int k = 0; k <= i; k++) {
                        // BUG 3: severe! Forget to deal with the empty string!!
                        if (k != 0 && !isSame(s.charAt(i - k), p.charAt(j - 2))) {
                            D[i][j] = false;
                            break;
                        }
                        
                        if (D[i - k][j - 2]) {
                            D[i][j] = true;
                            break;
                        }
                    }
                } else {
                    D[i][j] = i >= 1 
                         && isSame(s.charAt(i - 1), p.charAt(j - 1))
                         && D[i - 1][j - 1];
                }
            }
        }
        
        return D[s.length()][p.length()];
    }
    
}