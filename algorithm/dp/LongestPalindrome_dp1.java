package Algorithms.algorithm.dp;

public class LongestPalindrome_dp1 {
    public static void main(String[] args) {
        String s = "cabaabad";
        System.out.println(longestPalindrome(s));
    }
    
    // Solution 1:
    public static String longestPalindrome1(String s) {
        if (s == null) {
            return null;
        }
        
        int len = s.length();

        // Record i-j is a palindrome.
        boolean[][] D = new int[len][len];
        for (int i = 0; i < len; i++) {
        	for (int j = 0; j < len; j++) {
        		D[i][j] = false;
        	}
        }

        int max = 0;
        int retB = 0;
        int retE = 0;
        // 这样写的目的是，从前往后扫描时，被记录的DP值可以被复用
        // 因为D[i][j] 要用到i + 1, j - 1，所以每一次计算j时，把j对应的i全部计算完，这样
        // 下一次计算i,j的时候，可以有i+1, j-1可以用。
        for (int j = 0; j < len; j++) {
        	for (int i = 0; i <= j; i++) {
        	    if (s.charAt(i) == s.charAt(j)
                    && (j - i <= 3 || D[i + 1][j - 1])
                    ) {
                    D[i][j] = true;

                    if (j - i + 1 > max) {
                        retB = i;
                        retE = j;    
                    }
                } else {
                    D[i][j] = false;
                }            
        	}
        }
        
        return s.substring(retB, retE + 1);
    }

    // solution 2: 中心展开法。从头扫到尾部，每一个字符以它为中心向两边扩展，找最长回文。
    // 复杂度为N^2 并且是inplace，空间复杂度O(1)
    public static String longestPalindrome(String s) {
        if (s == null) {
            return null;
        }
        
        int len = s.length();

        if (len <= 0) {
            return "";
        }

        int max = 0;
        String ret = "";

        for (int i = 0; i < len; i++) {
            // 考虑奇数字符串
            String s1 = expandAround(s, i, i);
            if (s1.length() > max) {
                ret = s1;
                max = s1.length();
            }

            String s2 = expandAround(s, i, i);
            if (s2.length() > max) {
                ret = s2;
                max = s2.length();
            }
        }
        
        return ret;
    }

    public static String expandAround(String s, int c1, int c2) {
        int len = s.length();
        
        while (c1 >= 0 && c2 <= len - 1) {
            if (s.charAt(c1) != s.charAt(c2)) {
                break;    
            }

            c1++;
            c2--;
        }
        
        // 注意，根据 substring的定义，c2不要减1
        return s.substring(c1 + 1, c2);
    }
}
