package Algorithms.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FindSubstring {
    public static void main(String[] strs) {
        String[] L = {"fooo","barr","wing","ding","wing"};
        
        System.out.println(findSubstring("lingmindraboofooowingdingbarrwingmonkeypoundcake", L));
    }
    
    public static List<Integer> findSubstring1(String S, String[] L) {
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
    
    // SOLUTION 2:
    public static List<Integer> findSubstring(String S, String[] L) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        HashMap<String, Integer> found;
        List<Integer> ret = new ArrayList<Integer>();
        
        if (S == null || L == null || L.length == 0) {
            return ret;
        }
        
        // put all the strings into the map.
        for (String s: L) {
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }
        
        int lenL = L[0].length();
        
        // 注意这里的条件：i < S.length() - lenL * L.length
        // 这里很关键，如果长度不够了，不需要再继续查找 
        for (int i = 0; i <= S.length() - lenL * L.length; i++) {
            // 每一次，都复制之前的hashMap.
            found = new HashMap<String, Integer>(map);
            
            // 一次前进一个L的length.
            // 注意j <= S.length() - lenL; 防止越界
            for (int j = i; j <= S.length() - lenL; j += lenL) {
                String sub = S.substring(j, j + lenL);
                if (found.containsKey(sub)) {
                    // 将找到字符串的计数器减1.
                    found.put(sub, found.get(sub) - 1);
                    
                    // 减到0即可将其移出。否则会产生重复运算，以及我们用MAP为空来判断是否找到所有的单词。
                    if (found.get(sub) == 0) {
                        found.remove(sub);
                    }
                } else {
                    // 不符合条件，可以break,i前进到下一个匹配位置
                    break;
                }
                
                // L中所有的字符串都已经找到了。
                if (found.isEmpty()) {
                    ret.add(i);
                }
            }
        }
        
        return ret;
    }
}