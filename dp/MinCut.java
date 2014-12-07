package Algorithms.dp;

public class MinCut {
    public int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int len = s.length();
        
        // D[i] 的定义: 第i个字符到len - 1个字符的最小切割数。
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
                        // 这里就是特殊处理的地方了。当整个字符串都是回文的时候，因为没有D[Len]，所以这里直接
                        // 将D[i]置为0，意思就是这时不需要任何的划分。
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
