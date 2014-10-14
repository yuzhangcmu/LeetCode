package Algorithms.dp;

public class LongestPalindrome {
    public static void main(String[] args) {
        String s = "9cadfasdfsadfabaabaed";
        System.out.println(longestPalindrome(s));
        
        System.out.println(Character.toLowerCase('9'));
        
    }

    // Solution 1: Brute Force
    public static String longestPalindrome(String s) {
        if (s == null) {
            return null;
        }

        int len = s.length();

        int max = 0;
        int begin = 0;
        int end = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (dfs(s, i, j)) {
                    if (j - i + 1 > max) {
                        max = j - i + 1;
                        begin = i;
                        end = j;
                    }
                }
            }
        }

        return s.substring(begin, end + 1);
    }

    public static boolean dfs(String s, int i, int j) {
        if (i >= j) {
            return true;
        }

        if (s.charAt(i) == s.charAt(j)) {
            return dfs(s, i + 1, j - 1);
        }

        return false;
    }
}
