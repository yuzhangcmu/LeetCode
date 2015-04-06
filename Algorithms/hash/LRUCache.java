package Algorithms.hash;

import java.util.HashMap;

public class LRUCache {
    private class DLink {
        DLink pre;
        DLink next;
        int val;
        int key;
        DLink(int key, int val) {
            this.val = val;
            this.key = key;
            pre = null;
            next = null;
        }
    }
    
    HashMap<Integer, DLink> map;
    
    DLink head;
    DLink tail;
    
    int capacity;

    public void removeFist() {
        removeNode(head.next);
    }
    
    public void removeNode(DLink node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
    
    // add a node to the tail.
    public void addToTail(DLink node) {
        tail.pre.next = node;
        
        node.pre = tail.pre;
        node.next = tail;
        
        tail.pre = node;
    }
    
    public LRUCache(int capacity) {
        map = new HashMap<Integer, DLink>();
        
        // two dummy nodes. In that case, we can deal with them more conviencely.
        head = new DLink(-1, -1);
        tail = new DLink(-1, -1);
        head.next = tail;
        tail.pre = head;
        
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if (map.get(key) == null) {
            return -1;
        }
        
        // update the node.
        DLink node = map.get(key);
        removeNode(node);
        addToTail(node);
        
        return node.val;
    }
    
    public void set(int key, int value) {
        DLink node = map.get(key);
        if (node == null) {
            // create a node and add the key-node pair into the map.
            node = new DLink(key, value);
            map.put(key, node);
        } else {
            // update the value of the node.
            node.val = value;
            removeNode(node);
        }
        
        addToTail(node);
        
        // if the LRU is full, just remove a node.
        if (map.size() > capacity) {
            map.remove(head.next.key);
            removeFist();
        }
    }
}