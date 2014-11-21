package Algorithms.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FindSubstring {
    public static void main(String[] strs) {
        String[] L = {"fooo","barr","wing","ding","wing"};
        
        System.out.println(findSubstring("lingmindraboofooowingdingbarrwingmonkeypoundcake", L));
    }
    
    public static List<Integer> findSubstring(String S, String[] L) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        HashMap<String, Integer> found = new HashMap<String, Integer>();
        List<Integer> ret = new ArrayList<Integer>();
        
        if (S == null || L == null || L.length == 0) {
            return ret;
        }
        
        int cntL = 0;
        
        // put all the strings into the map.
        for (String s: L) {
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
                cntL++;
            }
        }
        
        int lenL = L[0].length();
        
        int cntFound = 0;
        
        // 注意这里的条件：i < S.length() - lenL * L.length
        // 这里很关键，如果长度不够了，不需要再继续查找 
        for (int i = 0; i <= S.length() - lenL * L.length; i++) {
            // clear the found hashmap.
            found.clear();
            cntFound = 0;
            
            // 一次前进一个L的length.
            // 注意j <= S.length() - lenL; 防止越界
            for (int j = i; j <= S.length() - lenL; j += lenL) {
                String sub = S.substring(j, j + lenL);
                if (map.containsKey(sub)) {
                    if (found.containsKey(sub)) {
                        if (found.get(sub) == map.get(sub)) {
                            // 超过了限制数目
                            break;
                        }
                        
                        found.put(sub, found.get(sub) + 1);
                    } else {
                        found.put(sub, 1);
                    }
                    
                    if (found.get(sub) == map.get(sub)) {
                        cntFound++;
                    }
                    
                    // L中所有的字符串都已经找到了。
                    if (cntFound == cntL) {
                        ret.add(i);
                    }
                } else {
                    // 不符合条件，可以break,i前进到下一个匹配位置
                    break;
                }
            }
        }
        
        return ret;
    }
}