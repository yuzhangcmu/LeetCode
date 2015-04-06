package Algorithms.lintcode.dp;

public class LongestCommonSubstring {
    /**
     * @param A, B: Two string.
     * @return: the length of the longest common substring.
     */
    public int longestCommonSubstring(String A, String B) {
        // write your code here
        if (A == null || B == null) {
            return 0;
        }
        
        int lenA = A.length();
        int lenB = B.length();
        
        // bug 1: use error init.
        int[][] D = new int[lenA + 1][lenB + 1];
        
        int max = 0;
        
        // BUG 2: should use <= instead of <
        for (int i = 0; i <= lenA; i++) {
            for (int j = 0; j <= lenB; j++) {
                if (i == 0 || j == 0) {
                    D[i][j] = 0;
                } else {
                    if (A.charAt(i - 1) == B.charAt(j - 1)) {
                        D[i][j] = D[i - 1][j - 1] + 1;
                    } else {
                        D[i][j] = 0;
                    }
                }
                
                max = Math.max(max, D[i][j]);
            }
        }
        
        return max;
    }
}
