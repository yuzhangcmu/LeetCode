package Algorithms.hash;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache2 {
    public static void main(String[] strs) {
        LRUCache2 lrc2 = new LRUCache2(2);
        lrc2.set(1,3);
        lrc2.set(2,2);
        lrc2.set(1,4);
        lrc2.set(4,2);
        
        System.out.println(lrc2.get(1));
    }
    
    LinkedHashMap<Integer, Integer> map;
    int capacity;
    
    public LRUCache2(final int capacity) {
        // create a map.
        map = new LinkedHashMap<Integer, Integer>(capacity) {
            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }
        };
        this.capacity = capacity;
    }
    
    public int get(int key) {
        Integer ret = map.get(key);
        if (ret == null) {
            return -1;
        } else {
            map.remove(key);
            map.put(key, ret);
        }
        
        return ret;
    }
    
    public void set(int key, int value) {
        map.remove(key);
        map.put(key, value);
    }
}
