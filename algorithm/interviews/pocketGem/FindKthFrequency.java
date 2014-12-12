package Algorithms.algorithm.interviews.pocketGem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;

/*
 * Input: int[] A = {1, 1, 2, 3, 4, 5, 2}; k = 3
 * return the highest frequency numbers.
 * return: [1, 2, 3] or [1, 2, 4] or [1, 2, 5]
 * */

public class FindKthFrequency {
    public static void main(String[] strs) {
        int[] A = {1, 1, 2, 3, 3, 3, 4, 4, 4, 4, 4, 5, 7, 2};
        System.out.println(findKthFrenquency(A, 3).toString());
    }

    /*
     * Solution 1:
     * 对HashMap Sort.
     * Complexity: O(NLogN)
     * */
    public static Set<Integer> findKthFrenquency1(int[] input, int k) {
        HashSet<Integer> set = new HashSet<Integer>();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num: input) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        
        ArrayList<Entry<Integer, Integer>> list = new ArrayList<Entry<Integer, Integer>>(map.entrySet());
        
        Collections.sort(list, new Comparator<Entry<Integer, Integer>>() {
            public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        
        for (int i = 0; i < k; i++) {
            set.add(list.get(i).getKey());
        }
        
        return set;
    }
    
    /*
     * Solution 2:
     * Use TreeMap.
     * */
    public static Set<Integer> findKthFrenquency2(int[] input, int k) {
        HashSet<Integer> set = new HashSet<Integer>();
        
        return set;
    }
    
    /*
     * Solution 3:
     * Use The priority queue.
     * */
    public static List<Integer> findKthFrenquency(int[] input, int k) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num: input) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        
        PriorityQueue<Entry<Integer, Integer>> q = new PriorityQueue<Entry<Integer, Integer>>(k + 1, new Comparator<Entry<Integer, Integer>>(){
           public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
               return o1.getValue() - o2.getValue();
           }
        });
        
        for (Entry<Integer, Integer> entry: map.entrySet()) {
            if (q.size() == k + 1) {
                // Delete the smallest element from the queue.
                q.poll();
            }
            q.offer(entry);
        }
        
        // delete one small element
        q.poll();

        while (!q.isEmpty()) {
            Entry<Integer, Integer> entry = q.poll(); 
            list.addFirst(entry.getKey());
        }
        
        return list;
    }
}
