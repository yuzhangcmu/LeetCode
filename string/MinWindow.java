package Algorithms.string;

import java.util.HashMap;

public class MinWindow {
    public static void main(String[] strs) {
        System.out.println(minWindow("aa", "aab"));
        System.out.println(minWindow1("aa", "aab"));
        
        int a [] = new int [5] ;     
        System.out.println("" + a[0]) ;  
    }
    /*
     * Use Hashmap to do it.
     * */
    public static String minWindow1(String S, String T) {
        if (S == null || T == null) {
            return null;
        }
        
        // Create a map to record the characters exit time.
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        
        int lenS = S.length();
        int lenT = T.length();
        for (int i = 0; i < lenT; i++) {
            Integer times = map.get(T.charAt(i));
            if (times == null) {
                map.put(T.charAt(i), 1);    
            } else {
                map.put(T.charAt(i), times + 1);
            }
        }
        
        int left = 0;
        int right = 0;
        
        int size = 0;
        
        String ret = "";
        
        int minLen = Integer.MAX_VALUE;
        
        // 注意:这里right 要++
        for (right = 0 ; right < lenS; right++) {
            char cRight = S.charAt(right);
            if (map.containsKey(cRight)) {
                map.put(cRight, map.get(cRight) - 1);
                if (map.get(cRight) == 0) {
                    // 表示某一个字符全部出现了.
                    size++;
                }
            }
            
            // shift the left point to right, until all the dupilcate characters gone.
            while (left < lenS) {
                char c = S.charAt(left);
                // 如果这字符出现的次数刚好，或是不够，是不可以移动Left的
                if (map.get(c) != null && map.get(c) >= 0) {
                    break;
                }
                
                // 可以删除无关的字符，以及重复的字符
                if (map.get(c) != null) {
                    map.put(c, map.get(c) + 1);    
                }
                
                left++;
            }
            
            int len = right - left + 1;
            // 字符都出现了
            if (size == map.size() && len < minLen) {
                 ret = S.substring(left, right + 1);
                 minLen = len;
            }
        }
        
        return ret;
    }
    
    /*
     * Try to do it with Arrays.
     * */
    public static String minWindow(String S, String T) {
        if (S == null || T == null) {
            return null;
        }
        
        // 隐式初始化后，它们默认是0
        // http://developer.51cto.com/art/200906/128274.htm
        int[] cntS = new int[128];
        int[] cntT = new int[128];
        
        int lenS = S.length();
        int lenT = T.length();
        
        // count all the characters in T.
        int cntCharT = 0;
        for (int i = 0; i < lenT; i++) {
            cntT[T.charAt(i)]++;
            if (cntT[T.charAt(i)] == 1) {
                // 计算T中不同字母的个数 
                cntCharT++;
            }
        }
        
        // 从左至右扫描 S
        int left = 0; // LEFT set to the left and move right.
        int cnt = 0;
        int minLen = Integer.MAX_VALUE;
        
        String ret = "";
        for (int i = 0; i < lenS; i++) {
            char c = S.charAt(i);
            if (cntT[c] != 0) {
                cntS[c]++;
                //c字母全部出现了
                if (cntS[c] == cntT[c]) {
                   cnt++; 
                }
            }
            
            while (cnt == cntCharT) {
                // 更新最小长度
                if (i - left + 1 < minLen) {
                    minLen = i - left + 1;
                    ret = S.substring(left, i + 1);
                }
                
                // 从左边移除一个字符
                if (cntT[c] != 0) {
                    cntS[c]--;
                }
                
                // 如果某个字符被减到小于预定值，cnt要减少。
                if (cntS[c] < cntT[c]) {
                    cnt--;
                }
                left++;
            }
        }
        
        return ret;
    }
}