package Algorithms.dp;

public class MinCut {
    public int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int len = s.length();
        int[] D = new int[len];
        
        boolean[][] isPalid = new boolean[len][len];
        
        for (int i = len - 1; i >= 0; i--) {
            // the worst case is divide the word one by one.
            D[i] = len - 1 -i;
            for (int j = i; j <= len - 1; j++) {
                // init it to be false;
                isPalid[i][j] = false;
                
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || isPalid[i + 1][j - 1])) {
                    isPalid[i][j] = true;
                    if (j == len - 1) {
                        D[i] = 0;
                    } else {
                        // 如果前半部分是回文，那么我们可以分解为第一个回文 + 后半部分的最小分割数
                        D[i] = Math.min(D[i], D[j + 1] + 1);
                    }
                }
            }
        }
        
        return D[0];
    }
}
