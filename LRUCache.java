package Algorithms;

import java.util.HashMap;

public class LRUCache {
    public class Node {
        Node next;
        Node pre;
        
        int key;
        int value;
        
        public Node(int key, int value) {
            super();
            this.next = null;
            this.pre = null;
            this.key = key;
            this.value = value;
        }
    }
    
    int size;
    
    Node head;
    Node tail;
    
    HashMap<Integer, Node> map;
    
    public static void main(String[] strs) {
        LRUCache cache = new LRUCache(2);
        cache.add(1, 2);
        
        System.out.println("Begine the test");
        
        Integer node1 = cache.get(1);
        if (node1 == null) {
            System.out.println("null");
        } else {
            System.out.println(node1);
        }
        
        cache.add(1, 3);
        node1 = cache.get(1);
        if (node1 == null) {
            System.out.println("null");
        } else {
            System.out.println(node1);
        }
        
        cache.add(2, 23);
        node1 = cache.get(2);
        if (node1 == null) {
            System.out.println("null");
        } else {
            System.out.println(node1);
        }
        
        cache.add(4, 4);
        node1 = cache.get(4);
        if (node1 == null) {
            System.out.println("null");
        } else {
            System.out.println(node1);
        }
        
        //cache.add(4, 23);
        
        node1 = cache.get(2);
        if (node1 == null) {
            System.out.println("null");
        } else {
            System.out.println(node1);
        }
        
        
    }

    public LRUCache(int size) {
        super();
        this.size = size;
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        
        // connect the tail and head;
        this.head.next = this.tail;
        this.tail.pre = this.head;
        
        this.map = new HashMap<Integer, Node>();
    }
    
    // Delete.
    public void deleteNode(int key) {
        if (map.containsKey(key)) {
            removeNode(key);
            
            // Also remove from the map.
            map.remove(key);
        }
    }
    
    public void removeNode(int key) {
        if (map.containsKey(key)) {
            // remove the node.
            Node node = map.get(key);
            
            // remove the node from the list;
            node.pre.next = node.next;
            node.next.pre = node.pre;
            
            // remove the node from the map.
        }
    }
    
    public void addNodeToTail(Node node) {
        // connect the node.
        node.pre = tail.pre;
        node.next = tail;
        
        // connect the exit ones to the node.
        tail.pre.next = node;
        tail.pre = node;
    }
    
    public void add(int key, int value) {
        Node node = null;
        if (map.containsKey(key)) {
            node = map.get(key);
            
            // refresh the node value.
            node.value = value;
            removeNode(key);
        } else {
            node = new Node(key, value);
            map.put(key, node);
        }
        
        //System.out.println("the size is : " + map.size());
        
        addNodeToTail(node);
        
        if (map.size() > size && size >= 1) {
            int keyRemove = head.next.key;
            
            //System.out.println("the keyRemove is : " + keyRemove);
            
            head.next = head.next.next;
            head.next.pre = head;
            
            map.remove(keyRemove);
        }
    }
    
    public Integer get(int key) {
        Node node = null;
        if (map.containsKey(key)) {
            node = map.get(key);
            removeNode(key);
            
            addNodeToTail(node);
            
            return node.value;
        } else {
            return null;
        }
    }
}
