package Algorithms.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Partition_2014_1229 {
    // Solution 1: The DFS version.
    public List<List<String>> partition1(String s) {
        List<List<String>> ret = new ArrayList<List<String>>();
        if (s == null) {
            return ret;
        }
        
        dfs(s, 0, new ArrayList<String>(), ret);
        return ret;
    }
    
    public static void dfs(String s, int index, List<String> path, List<List<String>> ret) {
        int len = s.length();
        if (index == len) {
            ret.add(new ArrayList<String>(path));
            return;
        }
        
        for (int i = index; i < len; i++) {
            String sub = s.substring(index, i + 1);
            if (!isPalindrome(sub)) {
                continue;
            }
            
            path.add(sub);
            dfs(s, i + 1, path, ret);
            path.remove(path.size() - 1);
        }
    }
    
    public static boolean isPalindrome(String s) {
        int len = s.length();
        int left = 0;
        int right = len - 1;
        
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
    
    // Solution 2: The DFS version with memory.
    public List<List<String>> partition2(String s) {
        List<List<String>> ret = new ArrayList<List<String>>();
        if (s == null) {
            return ret;
        }
        
        // bug: new map error.
        dfs2(s, 0, new ArrayList<String>(), ret, new HashMap<String, Boolean>());
        return ret;
    }
    
    public static void dfs2(String s, int index, List<String> path, List<List<String>> ret, HashMap<String, Boolean> map) {
        int len = s.length();
        if (index == len) {
            ret.add(new ArrayList<String>(path));
            return;
        }
        
        for (int i = index; i < len; i++) {
            String sub = s.substring(index, i + 1);
            if (!isPalindromeHash(sub, map)) {
                continue;
            }
            
            path.add(sub);
            dfs2(s, i + 1, path, ret, map);
            path.remove(path.size() - 1);
        }
    }
    
    // BUG 3: use boolean instead of Boolean.
    public static boolean isPalindromeHash(String s, HashMap<String, Boolean> map) {
        int len = s.length();
        int left = 0;
        int right = len - 1;
        
        if (map.get(s) != null) {
            return map.get(s);
        }
        
        map.put(s, true);
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                map.put(s, false);
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
    
    // Solution 3: Use DP to determine the palindrome first.
    public List<List<String>> partition(String s) {
        List<List<String>> ret = new ArrayList<List<String>>();
        if (s == null) {
            return ret;
        }
        
        int len = s.length();
        
        // D[i][j]: if this a palindrom for s.substring(i, j + 1).
        boolean[][] D = new boolean[len][len];
        
        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                D[i][j] = s.charAt(i) == s.charAt(j) && (j - i <= 2 || D[i + 1][j - 1]);
            }
        }
        
        // bug: new map error.
        dfs3(s, 0, new ArrayList<String>(), ret, D);
        return ret;
    }
    
    public static void dfs3(String s, int index, List<String> path, List<List<String>> ret, boolean[][] D) {
        int len = s.length();
        if (index == len) {
            ret.add(new ArrayList<String>(path));
            return;
        }
        
        for (int i = index; i < len; i++) {
            String sub = s.substring(index, i + 1);
            if (!D[index][i]) {
                continue;
            }
            
            path.add(sub);
            dfs3(s, i + 1, path, ret, D);
            path.remove(path.size() - 1);
        }
    }
}