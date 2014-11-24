package Algorithms.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class FindLadders {
    public static void main(String[] strs) {
        Set<String> dict = new HashSet<String>();
        dict.add("hot");
        dict.add("dot");
        dict.add("dog");
        dict.add("lot");
        dict.add("log");
        dict.add("cog");
        
        System.out.println(findLadders("hit", "cob", dict));
    }
    
    public static List<List<String>> findLadders(String start, String end, Set<String> dict) {
        if (start == null || end == null) {
            return null;
        }
        
        Queue<String> q = new LinkedList<String>();
        
        // 存储每一个单词对应的路径
        HashMap<String, List<List<String>>> map = new HashMap<String, List<List<String>>>();
        
        // 标记在某一层找到解
        boolean find = false;
        
        // store the length of the start string.
        int lenStr = start.length();
        
        List<List<String>> list = new ArrayList<List<String>>();
        
        // 唯一的路径
        List<String> path = new ArrayList<String>();
        path.add(start);
        list.add(path);
        
        // 将头节点放入
        map.put(start, list);
        q.offer(start);
        
        while (!q.isEmpty()) {
            int size = q.size();
            
            HashMap<String, List<List<String>>> mapTmp = new HashMap<String, List<List<String>>>();    
            for (int i = 0; i < size; i++) {
                // get the current word.
                String str = q.poll();
                for (int j = 0; j < lenStr; j++) {
                    StringBuilder sb = new StringBuilder(str);
                    for (char c = 'a'; c <= 'z'; c++) {
                        sb.setCharAt(j, c);
                        String tmp = sb.toString();
                        
                        // 1. 重复的单词，不需要计算。因为之前一层出现过，再出现只会更长
                        // 2. 必须要在字典中出现
                        if (map.containsKey(tmp) || (!dict.contains(tmp) && !tmp.equals(end))) {
                            continue;
                        }
                        
                        // 将前节点的路径提取出来
                        List<List<String>> pre = map.get(str);
                        
                        // 从mapTmp中取出节点，或者是新建一个节点
                        List<List<String>> curList = mapTmp.get(tmp);
                        if (curList == null) {
                            // Create a new list and add to the end word.
                            curList = new ArrayList<List<String>>();
                            mapTmp.put(tmp, curList);
                        
                            // 将生成的单词放入队列，以便下一次继续变换
                            // 放在这里可以避免Q重复加入
                            q.offer(tmp);
                        }
                        
                        // 将PRE的path 取出，加上当前节点，并放入curList中
                        for(List<String> pathPre: pre) {
                            List<String> pathNew = new ArrayList<String>(pathPre);
                            pathNew.add(tmp);
                            curList.add(pathNew);
                        }
                        
                        if (tmp.equals(end)) {
                            find = true;
                        }
                    }
                }
            }
            
            if (find) {
                return mapTmp.get(end);
            }
            
            // 把当前层找到的解放在MAP中。
            // 使用2个map的原因是：在当前层中，我们需要把同一个单词的所有的解全部找出来.
            map.putAll(mapTmp);
        }
        
        // 返回一个空的结果
        return new ArrayList<List<String>>();
    }
}