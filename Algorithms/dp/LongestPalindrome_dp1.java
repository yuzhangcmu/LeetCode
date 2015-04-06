package Algorithms.dp;

public class LongestPalindrome_dp1 {
    public static void main(String[] args) {
        String s = "cabaabad";
        System.out.println(longestPalindrome(s));
    }
    
    // solution 1: DP.
    public static String longestPalindrome1(String s) {
        if (s == null) {
            return null;
        }
        
        int len = s.length();

        // Record i-j is a palindrome.
        boolean[][] D = new boolean[len][len];

        int max = 0;
        int retB = 0;
        int retE = 0;
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
    
    // SOLUTION 1: Re do 2014.12.26
    public String longestPalindrome4(String s) {
        if (s == null) {
            return null;
        }
        
        String ret = null;
        
        int len = s.length();
        int max = 0;
        
        boolean[][] D = new boolean[len][len];
        
        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                D[i][j] = s.charAt(i) == s.charAt(j) && (j - i <= 2 || D[i + 1][j - 1]);
                if (D[i][j]) {
                    if (j - i + 1 > max) {
                        max = j - i + 1;
                        ret = s.substring(i, j + 1);
                    }
                }
            }
        }
        
        return ret;
    }
    
    // solution 2: 中心展开法 空间复杂度O(1)
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
            // 鑰冭檻濂囨暟瀛楃涓�
            String s1 = expandAround(s, i, i);
            if (s1.length() > max) {
                ret = s1;
                max = s1.length();
            }

            // 鑰冭檻鍋舵暟闀垮害鐨勫瓧绗︿覆
            String s2 = expandAround(s, i, i + 1);
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

            c1--;
            c2++;
        }
        
        // 娉ㄦ剰锛屾牴鎹�substring鐨勫畾涔夛紝c2涓嶈鍑�
        return s.substring(c1 + 1, c2);
    }
    
    // SOLUTION 2: Redo 2014.12.26
    public String longestPalindrome3(String s) {
        if (s == null) {
            return null;
        }
        
        String ret = null;
        
        int len = s.length();
        int max = 0;
        for (int i = 0; i < len; i++) {
            String s1 = getLongest(s, i, i);
            String s2 = getLongest(s, i, i + 1);
            
            if (s1.length() > max) {
                max = Math.max(max, s1.length());
                ret = s1;
            }
            
            if (s2.length() > max) {
                max = Math.max(max, s2.length());
                ret = s2;
            }
        }
        
        return ret;
    }
    
    public String getLongest(String s, int left, int right) {
        int len = s.length();
        while (left >= 0 && right < len) {
            // when i is in the center.
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }
            
            left--;
            right++;
        }
        
        return s.substring(left + 1, right);
    }
}
