package Algorithms.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class FindLadders_1218_2014 {
    public static List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> ret = new ArrayList<List<String>>();
        if (start == null || end == null || dict == null) {
            return ret;
        }
        
        HashMap<String, List<List<String>>> map = new HashMap<String, List<List<String>>>();
        
        // Store the map of the current level.
        HashMap<String, List<List<String>>> mapTmp = new HashMap<String, List<List<String>>>();
        
        Queue<String> q = new LinkedList<String>();
        q.offer(start);
        
        List<List<String>> listStart = new ArrayList<List<String>>();
        
        // 唯一的路径
        List<String> path = new ArrayList<String>();
        path.add(start);
        listStart.add(path);
        
        // 将头节点放入
        map.put(start, listStart);
        
        while (!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                String s = q.poll();
                
                int len = s.length();
                for (int j = 0; j < len; j++) {
                    StringBuilder sb = new StringBuilder(s);
                    for (char c = 'a'; c <= 'z'; c++) {
                        // Bug 2: should seperate the setCharAt(j, c) function and the sb.toString() function.
                        sb.setCharAt(j, c);
                        String tmp = sb.toString();
                        
                        // 1. 不在字典中，并且不是end.
                        // 2. 前面的map中已经出现过
                        
                        // Bug 1: map should use containsKey;
                        if ((!dict.contains(tmp) && !tmp.equals(end)) || map.containsKey(tmp)) {
                            continue;
                        }
                        
                        // Try to get the pre list.
                        List<List<String>> pre = map.get(s);
                        
                        // 从mapTmp中取出节点，或者是新建一个节点
                        List<List<String>> curList = mapTmp.get(tmp);
                        if (curList == null) {
                            curList = new ArrayList<List<String>>();
                            
                            // Only offer new string to the queue.
                            // 将生成的单词放入队列，以便下一次继续变换
                            // 放在这里可以避免Q重复加入
                            q.offer(tmp);
                            
                            // create a new map.
                            mapTmp.put(tmp, curList);
                        }
                        
                        // 将PRE的path 取出，加上当前节点，并放入curList中
                        for (List<String> strList: pre) {
                            // Should create a new list. 
                            List<String> strListNew = new ArrayList<String>(strList);
                            strListNew.add(tmp);
                            curList.add(strListNew);
                        }
                    }
                }
            }
            
            if (mapTmp.containsKey(end)) {
                return mapTmp.get(end);
            }
            
            // add the tmp map into the map.
            map.putAll(mapTmp);
        }
        
        return ret;
    }
}