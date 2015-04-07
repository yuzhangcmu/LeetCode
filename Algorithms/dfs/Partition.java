package Algorithms.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Partition {
    public List<List<String>> partition1(String s) {
        List<List<String>> ret = new ArrayList<List<String>>();
        List<String> path = new ArrayList<String>();

        if (s == null) {
            return ret;
        }

        HashMap<String, Boolean> map = new HashMap<String, Boolean>();

        dfs(s, path, ret, 0, map);

        return ret;
    }

    public boolean isPalindrom(String s) {
        int len = s.length();
        if (len <= 1) {
            return true;
        }

        int left = 0;
        int right = len - 1;
        for (; left < right; left++, right--) {
            if (s.charAt(right) != s.charAt(left)) {
                return false;
            }
        }

        return true;
    }
    
    /*
      we use a map to store the solutions to reduce the times of computing.
    */
    public void dfs(String s, List<String> path, List<List<String>> ret, int index, HashMap<String, Boolean> map) {
        if (index == s.length()) {
            ret.add(new ArrayList<String>(path));
            return;
        }

        for (int i = index; i < s.length(); i++) {
            String sub = s.substring(index, i + 1);

            Boolean flag = map.get(sub);
            if (flag == null) {
                flag = isPalindrom(sub);
                map.put(sub, flag);
            }
            
            if (!flag) {
                continue;
            }

            path.add(sub);
            dfs(s, path, ret, i + 1, map);
            path.remove(path.size() - 1);
        }
    }

    public List<List<String>> partition(String s) {
        List<List<String>> ret = new ArrayList<List<String>>();
        List<String> path = new ArrayList<String>();

        if (s == null) {
            return ret;
        }

        boolean[][] isPalindrom = buildPalindromDP(s);

        dfs2(s, path, ret, 0, isPalindrom);

        return ret;
    }
    
    /*
     * Solution 2: Use DP to reduce the duplicate count.
     * */
    boolean[][] buildPalindromDP(String s) {
        int len = s.length();
        boolean[][] D = new boolean[len][len];

        for (int j = 0; j < len; j++) {
        	for (int i = 0; i <= j; i++) {
        		if (j == 0) {
        			D[i][j] = true;
        			continue;
        		} 

        		D[i][j] = s.charAt(i) == s.charAt(j) 
        		    && (j - i <= 2 || D[i + 1][j - 1]);
        	}
        }

        return D;
    }

    /*
      we use a map to store the solutions to reduce the times of computing.
    */
    public void dfs2(String s, List<String> path, List<List<String>> ret, int index, boolean[][] isPalindromDP) {
        if (index == s.length()) {
            ret.add(new ArrayList<String>(path));
            return;
        }

        for (int i = index; i < s.length(); i++) {
            String sub = s.substring(index, i + 1);
            if (!isPalindromDP[index][i]) {
                continue;
            }

            path.add(sub);
            dfs2(s, path, ret, i + 1, isPalindromDP);
            path.remove(path.size() - 1);
        }
    }
}
