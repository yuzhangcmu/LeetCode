package Algorithms.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class LadderLength_1218_2014 {
    public static void main(String[] strs) {
        HashSet<String> set = new HashSet<String>();
        set.add("hot");
        set.add("dot");
        set.add("dog");
        set.add("lot");
        set.add("log");
        set.add("cog");
        System.out.println(ladderLength("hit", "cog", set));
    }
    
    public static int ladderLength(String start, String end, Set<String> dict) {
        if (start == null || end == null || dict == null) {
            return 0;
        }
        
        // Bug 1: quese is a interface not a class
        Queue<String> q = new LinkedList<String>();
        q.offer(start);
        
        HashSet<String> set = new HashSet<String>();
        set.add(start);
        
        // Bug 3: lever start from 1;
        int level = 1;
        
        while (!q.isEmpty()) {
            int size = q.size();
            
            level++;
            
            for (int i = 0; i < size; i++) {
                String s = q.poll();
                
                int len = s.length();
                
                for (int j = 0; j < len; j++) {
                    StringBuilder sb = new StringBuilder(s);
                    for (char c = 'a'; c <= 'z'; c++) {
                        // Bug 2: setCharAt
                        sb.setCharAt(j, c);
                        String tmp = sb.toString();
                        
                        // Should be in the dict and not in the hashset.
                        if (set.contains(tmp) || !dict.contains(tmp)) {
                            continue;
                        }
                        
                        if (tmp.equals(end)) {
                            return level;
                        }
                        
                        set.add(tmp);
                        q.offer(tmp);
                    }
                }
                
            }
        }
        
        // When not found, return 0;
        // "hot", "dog", ["hot","dog"]
        return 0;
    }
}