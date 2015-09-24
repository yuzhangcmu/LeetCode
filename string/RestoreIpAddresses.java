package Algorithms.string;

import java.util.ArrayList;
import java.util.List;

public class RestoreIpAddresses {
    public static void main(String[] strs) {
        System.out.println(restoreIpAddresses("0000"));
    }
    
    public List<String> restoreIpAddresses1(String s) {
        if (s == null) {
            return null;
        }
        
        ArrayList<String> ret = new ArrayList<String>();
        ArrayList<String> path = new ArrayList<String>();
        
        dfs(s, 0, path, ret);
        
        return ret;
    }
    
    public void dfs(String s, int index, ArrayList<String> path, ArrayList<String> ret) {
        if (path.size() == 4) {
            if (index == s.length()) {
                StringBuilder sb = new StringBuilder();
                for (String str: path) {
                    sb.append(str);
                    sb.append('.');
                }
                
                sb.deleteCharAt(sb.length() - 1);
                ret.add(sb.toString());
            }
            
            return;
        }
        
        int len = s.length();
        for (int i = index; i < index + 3 && i < len; i++) {
            if (s.charAt(index) == '0' && i > index) {
                break;
            }
            
            String pre = s.substring(index, i + 1);
            int num = Integer.parseInt(pre);
            if (num > 255) {
                continue;
            }
            
            path.add(pre);
            dfs(s, i + 1, path, ret);
            path.remove(path.size() - 1);
        }
    }
    
    // 2015.1.1 Redo:
    public List<String> restoreIpAddresses2(String s) {
        List<String> ret = new ArrayList<String>();
        // Bug 1: not length, but length().
        if (s == null || s.length() < 4 || s.length() > 12) {
            return ret;
        }
        
        dfs(s, new ArrayList<String>(), ret, 0);
        return ret;
    }
    
    public void dfs(String s, List<String> path, List<String> ret, int index) {
        // THE BASE CASE:
        int len = s.length();
        if (path.size() == 4) {
            // Create a solution.
            if (index == len) {
                StringBuilder sb = new StringBuilder();
                for (String str: path) {
                    sb.append(str);
                    sb.append(".");
                }
                sb.deleteCharAt(sb.length() - 1);
                
                // bug 3: forget this statement.
                ret.add(sb.toString());
            }
            
            return;
        }
        
        // 2/ 25 / 255
        // bug 2: i should < len.
        for (int i = index; i < index + 3 && i < len; i++) {
            String sub = s.substring(index, i + 1);
            if (s.charAt(index) == '0' && i != index) {
                // only allow 0, not 02, 022
                break;
            }
            
            if (!isValid(sub)) {
                continue;
            }
            
            path.add(sub);
            dfs(s, path, ret, i + 1);
            path.remove(path.size() - 1);
        }
    }
    
    // 2015.1.20.
    public boolean isValid1(String s) {
        int num = Integer.parseInt(s);
        return num >= 0 && num <= 255;
    }
    
    public static List<String> restoreIpAddresses(String s) {
        // 1850
        List<String> ret = new ArrayList<String>();
        if (s == null) {
            return ret;
        }
        
        dfs(s, 0, new ArrayList<String>(), ret);
        return ret;
    }
    
    public static void dfs(String s, int index, List<String> path, List<String> ret) {
        int len = s.length();
        if (index == len && path.size() == 4) {
            StringBuilder sb = new StringBuilder();
            for (String str: path) {
                sb.append(str + ".");
            }
            sb.deleteCharAt(sb.length() - 1);
            ret.add(sb.toString());
            return;
        } else if (index == len || path.size() == 4) {
            // base case. out of range.
            return;
        }
        
        // bug 2: should use i < len to avoid overflow.
        for (int i = index; i < index + 3 && i < len; i++) {
            // Bug 1: Line 29: error: cannot find symbol: method sub(int,int)
            String sub = s.substring(index, i + 1);
            if (!isValid(sub)) {
                continue;
            }
            path.add(sub);
            dfs(s, i + 1, path, ret);
            path.remove(path.size() - 1);
        }
    }
    
    public static boolean isValid(String s) {
        if (s.length() != 1 && s.charAt(0) == '0') {
            return false;
        }
        
        int num = Integer.parseInt(s);
        return num <= 255 && num >= 0;
    }
}