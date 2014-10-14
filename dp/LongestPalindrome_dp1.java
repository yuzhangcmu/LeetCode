package Algorithms.dp;

public class LongestPalindrome_dp1 {
    public static void main(String[] args) {
        String s = "cabaabad";
        System.out.println(longestPalindrome(s));
    }
    
    // Solution 1:
    public static String longestPalindrome(String s) {
        if (s == null) {
            return null;
        }
        
        int len = s.length();

        // Record i-j is a palindrome.
        boolean[][] D = new boolean[len][len];

        int max = 0;
        int retB = 0;
        int retE = 0;
        // 这样写的目的是，从前往后扫描时，被记录的DP值可以被复用
        // 因为D[i][j] 要用到i + 1, j - 1，所以每一次计算j时，把j对应的i全部计算完，这样
        // 下一次计算i,j的时候，可以有i+1, j-1可以用。
        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j)
                    && (j - i <= 2 || D[i + 1][j - 1])
                    ) {
                    D[i][j] = true;

                    if (j - i + 1 > max) {
                        retB = i;
                        retE = j;
                        max = j - i + 1;
                    }
                } else {
                    D[i][j] = false;
                }            
            }
        }
        
        return s.substring(retB, retE + 1);
    }
}
