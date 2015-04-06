package Algorithms.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class LadderLength {
    public int ladderLength(String start, String end, Set<String> dict) {
        if (start == null || end == null) {
            return 0;
        }
        
        // we use BFS to solve the problem. use a QUEUE to store all the solutions in one level.
        Queue<String> q = new LinkedList<String>();
        
        int level = 0;
        
        q.offer(start);
        
        // 避免计算到重复的字符串
        HashSet<String> set = new HashSet<String>();
        set.add(start);
        
        while (!q.isEmpty()) {
            int size = q.size();
            
            level++;            
            for (int i = 0; i < size; i++) {
                String s = q.poll();
                
                int len = s.length();
                for (int j = 0; j < len; j++) {
                    StringBuilder sb = new StringBuilder(s);
                    // 注意，这时是char
                    for (char c = 'a'; c <= 'z'; c++) {
                        sb.setCharAt(j, c);
                        
                        String tmp = sb.toString();
                        
                        if (tmp.equals(end)) {
                            return level + 1;
                        }
                        
                        if (!set.contains(tmp) && dict.contains(tmp)) {
                            set.add(tmp);
                            q.offer(tmp);
                        }
                    }
                }
                
            }
        }
        
        return 0;
    }
}
