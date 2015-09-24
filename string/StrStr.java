package Algorithms.string;

import java.util.ArrayList;

public class StrStr {
    public static void main(String[] strs) {
        System.out.println(strStr("ppa","pa"));
        System.out.println(findPattern("ppa","pax"));
        
        //System.out.println(findAllStrings("xxisxhis"));
        
    }
    
    public static String strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return null;
        }

        int len1 = haystack.length();
        int len2 = needle.length();

        // Pay attention. if find "pl" in "apple", then len1 = 5, len2 = 2, the
        // i should <= 3.
        // so it should be i <= len1 - len2;
        for (int i = 0; i <= len1 - len2; i++) {
            int j = 0;
            for (; j < len2; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
            }

            // j goes to the end, it means that the loop never break. That means
            // found the needle.
            if (j == len2) {
                return haystack.substring(i);
            }
        }

        // didn't find the needle.
        return null;
    }
    
    public static int findPattern(String base, String pattern) {
        if (base == null || pattern == null) {
            return -1;
        }
        
        int len1 = base.length();
        int len2 = pattern.length();
        
        for (int i = 0; i <= len1 - len2; i++) {
            int j = 0;
            for (; j < len2; j++) {
                if (base.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
                
                if (j == len2 - 1) {
                    return i;
                }
            }
        
        }

        // did not find pattern.
        return -1;
    }
}