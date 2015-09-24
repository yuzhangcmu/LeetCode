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
        boolean[][] D = new boolean[len][len];
        for (int i = 0; i < len; i++) {
        	for (int j = 0; j < len; j++) {
        		D[i][j] = false;
        	}
        }

        int max = 0;
        int retB = 0;
        int retE = 0;
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

    // solution 2: ���������������������������������������������������������������������������������������������������������
    // ������������N^2 ���������inplace������������������O(1)
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
            // ���������������������
            String s1 = expandAround(s, i, i);
            if (s1.length() > max) {
                ret = s1;
                max = s1.length();
            }

            // ������������������������������
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
        
        // ��������������� substring������������c2���������1
        return s.substring(c1 + 1, c2);
    }
}
