package Algorithms.string;

import java.util.ArrayList;
import java.util.List;

public class RestoreIpAddresses {
    public List<String> restoreIpAddresses(String s) {
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
            if (index == s.length() && path.size() == 4) {
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
            // 过滤Number > 255的情况。
            int num = Integer.parseInt(pre);
            if (num > 255) {
                continue;
            }
            
            path.add(pre);
            dfs(s, i + 1, path, ret);
            path.remove(path.size() - 1);
        }
    }
}