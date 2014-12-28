package Algorithms.dp;

import java.util.Arrays;

public class IsScramble {
    public static void main(String[] strs) {
        System.out.println(isScramble("a", "a"));

    }

    public static boolean isScramble1(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false;
        }

        int len1 = s1.length();
        int len2 = s2.length();

        // the two strings should be the same length.
        if (len1 != len2) {
            return false;
        }

        return rec(s1, s2);
    }

    // Solution 1: The recursion version.
    public static boolean rec1(String s1, String s2) {
        int len = s1.length();

        // the base case.
        if (len == 1) {
            return s1.equals(s2);
        }

        // 鍒掑垎2涓瓧绗︿覆
        for (int i = 1; i < len; i++) {
            // we have two situation;
            // the left-left right-right & left-right right-left
            if (rec1(s1.substring(0, i), s2.substring(0, i))
                    && rec1(s1.substring(i, len), s2.substring(i, len))) {
                return true;
            }

            if (rec1(s1.substring(0, i), s2.substring(len - i, len))
                    && rec1(s1.substring(i, len), s2.substring(0, len - i))) {
                return true;
            }
        }

        return false;
    }

    // Solution 2: The recursion version with sorting.
    // 鎺掑簭涔嬪悗鐨勫壀鏋濆彲浠ラ�杩嘗eetCode鐨勬鏌�
    public static boolean rec(String s1, String s2) {
        int len = s1.length();

        // the base case.
        if (len == 1) {
            return s1.equals(s2);
        }

        // sort to speed up.
        char[] s1ch = s1.toCharArray();
        Arrays.sort(s1ch);
        String s1Sort = new String(s1ch);

        char[] s2ch = s2.toCharArray();
        Arrays.sort(s2ch);
        String s2Sort = new String(s2ch);

        if (!s1Sort.equals(s2Sort)) {
            return false;
        }

        // 鍒掑垎2涓瓧绗︿覆
        for (int i = 1; i < len; i++) {
            // we have two situation;
            // the left-left right-right & left-right right-left
            if (rec(s1.substring(0, i), s2.substring(0, i))
                    && rec(s1.substring(i, len), s2.substring(i, len))) {
                return true;
            }

            if (rec(s1.substring(0, i), s2.substring(len - i, len))
                    && rec(s1.substring(i, len), s2.substring(0, len - i))) {
                return true;
            }
        }

        return false;
    }

    // Solution 3: The recursion version with memory.
    // 閫氳繃璁板繂鐭╅樀鏉ュ噺灏戣绠楅噺
    public static boolean isScramble3(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false;
        }

        int len1 = s1.length();
        int len2 = s2.length();

        // the two strings should be the same length.
        if (len1 != len2) {
            return false;
        }

        int[][][] mem = new int[len1][len1][len1];
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len1; j++) {
                for (int k = 0; k < len1; k++) {
                    // -1 means unseted.
                    mem[i][j][k] = -1;
                }
            }
        }

        return recMem(s1, 0, s2, 0, len1, mem);
    }

    // Solution 3: The recursion version with memory.
    // 閫氳繃璁板繂鐭╅樀鏉ュ噺灏戣绠楅噺
    public static boolean recMem(String s1, int index1, String s2, int index2,
            int len, int[][][] mem) {
        // the base case.
        if (len == 1) {
            return s1.charAt(index1) == s2.charAt(index2);
        }

        // LEN: 1 - totalLen-1
        int ret = mem[index1][index2][len - 1];
        if (ret != -1) {
            return ret == 1 ? true : false;
        }

        // 鍒濆鍖栦负false
        ret = 0;

        // 鍒掑垎2涓瓧绗︿覆. i means the length of the left side in S1
        for (int i = 1; i < len; i++) {
            // we have two situation;
            // the left-left right-right & left-right right-left
            if (recMem(s1, index1, s2, index2, i, mem)
                    && recMem(s1, index1 + i, s2, index2 + i, len - i, mem)) {
                ret = 1;
                break;
            }

            if (recMem(s1, index1, s2, index2 + len - i, i, mem)
                    && recMem(s1, index1 + i, s2, index2, len - i, mem)) {
                ret = 1;
                break;
            }
        }

        mem[index1][index2][len - 1] = ret;
        return ret == 1 ? true : false;
    }

    /*
     * Solution 4: The DP Version.
     */
    public static boolean isScramble4(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false;
        }

        int len1 = s1.length();
        int len2 = s2.length();

        // the two strings should be the same length.
        if (len1 != len2) {
            return false;
        }

        /*
         * i: The index of string 1. j: The index of string 2. k: The length of
         * the two string. 1 ~ len1
         * 
         * D[i][j][k] =
         */
        boolean[][][] D = new boolean[len1][len1][len1 + 1];
        for (int subLen = 1; subLen <= len1; subLen++) {
            for (int i1 = 0; i1 <= len1 - subLen; i1++) {
                for (int i2 = 0; i2 <= len1 - subLen; i2++) {
                    if (subLen == 1) {
                        D[i1][i2][subLen] = s1.charAt(i1) == s2.charAt(i2);
                        continue;
                    } 
                    
                    D[i1][i2][subLen] = false;
                    for (int l = 1; l < subLen; l++) {
                        if (D[i1][i2][l] && D[i1 + l][i2 + l][subLen - l]
                                || D[i1][i2 + subLen - l][l] && D[i1 + l][i2][subLen - l]
                                ) {
                            D[i1][i2][subLen] = true;
                            break;
                        }
                    }
                }
            }
        }

        return D[0][0][len1];
    }
    
    /*
     * Solution 4: The DP Version. REDO
     */
    public static boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false;
        }
        
        int len = s1.length();
        
        if (s2.length() != len) {
            return false;
        }

        boolean[][][] D = new boolean[len][len][len + 1];
        
        // D[i][j][k] = D[i][]
        for (int k = 1; k <= len; k++) {
            // 注意这里的边界选取。 如果选的不对，就会发生越界的情况.. orz..
            // attention: should use "<="
            for (int i = 0; i <= len - k; i++) {
                for (int j = 0; j <= len - k; j++) {
                    if (k == 1) {
                        D[i][j][k] = s1.charAt(i) == s2.charAt(j);
                        continue;
                    }
                    
                    D[i][j][k] = false;
                    for (int l = 1; l <= k - 1; l++) {
                        if (D[i][j][l] && D[i + l][j + l][k - l] 
                            || D[i][j + k - l][l] && D[i + l][j][k - l] ) {
                            D[i][j][k] = true;
                            break;
                        }
                    }
                }
            }
        }
        
        return D[0][0][len];
    }

}
