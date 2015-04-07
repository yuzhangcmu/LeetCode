package Algorithms.algorithm.others;
public class Interleaving {
    public static void main(String[] strs) {
        System.out.println(isInterleave("a", "b", "ab"));
        if ('a' >= 'd') {
            System.out.println("tre");
        }
        int a = 'c';
        int b = 'a';
        
        char c = Character.toLowerCase('9');
        return;
    }
    
    public static boolean isInterleave(String s1, String s2, String s3) {
        // f[i][j] means that  from 0-i in S1 and from 0-j in S2 compose to S3(i+j)
        // f[i][j]:
        // 1. s1.i == s3.(i+j) && s1(i - 1) + s2(j) compase s3(i + j  - 1)
        // || s2.j == s3.(i+j) && s1(i) + s2(j - 1) compase s3(i + j  - 1)
        // init: 
        // f[0][0] = true;
        // when i = 0, f[i][j] = f[i][j - 1] && s2(j) == s3(i+j)
        // when j = 0, f[i][j] = f[i - 1][j] && s1(i) == s3(i+j)
        if (s3 == null) {
            return false;
        }
        
        if (s2 == null && s1 == null) {
            return false;
        }
        
        if ((s1 == null || s1.length() == 0) && s2 != null) {
            return s2.equals(s3);
        } else if ((s2 == null || s2.length() == 0) && s1 != null) {
            return s1.equals(s3);
        }
        
        boolean[][] match = new boolean[s1.length() + 1][s2.length() + 1];
        
        match[0][0] = true;
        for (int j = 1; j <= s2.length(); j++) {
            match[0][j] = (s2.charAt(j - 1) == s3.charAt(j - 1) && match[0][j - 1]); 
        }
        
        for (int i = 1; i <= s1.length(); i++) {
            match[i][0] = (s1.charAt(i - 1) == s3.charAt(i - 1) && match[i - 1][0]); 
        }
        
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                // f[i][j]:
                // 1. s1.i == s3.(i+j) && s1(i - 1) + s2(j) compase s3(i + j  - 1)
                // || s2.j == s3.(i+j) && s1(i) + s2(j - 1) compase s3(i + j  - 1)
                match[i][j] = false;
                match[i][j] |= match[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                match[i][j] |= match[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
            }
        }
        
        return match[s1.length()][s2.length()];
    }
}