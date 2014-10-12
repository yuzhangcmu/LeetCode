package Algorithms.algorithm.others;

import java.util.HashMap;

public class LRUCache{
    public static void main(String[] strs) {
        LRUCache lru = new LRUCache(1);
        lru.set(2,1);
        lru.get(2);
        System.out.print("xx");
    }
    
    private HashMap<Integer, Node> map;
    private int capacity;
    private Node head;
    private Node tail;
        
    private class Node {
        Node pre;
        Node next;
        int key;
        int value;
        
        public Node (int key, int value) {
            this.key = key;
            this.value = value;
            this.pre = null;
            this.next = null;
        }
    }
    
    private void removeFirst() {
        head.next = head.next.next;
        head.next.pre = head;
    }
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<Integer, Node>();
        
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        
        head.next = tail;
        tail.pre = head;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        
        Node node = map.get(key);
        
        // link the pre to the next. link the next to the pre.
        removeNode(node);
        
        // add the node to the tail.
        addToTail(node);
        
        return map.get(key).value;
    }
    
    public void removeNode(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
    
    public void addToTail(Node node) {
        tail.pre.next = node;
        node.next = tail;
        
        node.pre = tail.pre;
        tail.pre = node;
    }
    
    public void set(int key, int value) {
        
        
        Node insert;
        if (map.containsKey(key)) {
            insert = map.get(key);
            insert.value = value;
            
            // move out the insert from the list.
            removeNode(insert);
        } else {
            // remove a element if the size is full.        
            if (capacity == map.size()) {
                map.remove(head.next.key);
                removeFirst();            
            }
            
            insert = new Node(key, value);
            map.put(key, insert);
        }
        
        // move the node to the tail.
        addToTail(insert);
    }
}