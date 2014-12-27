import java.util.HashMap;


public class LRUCache {
	public static void main(String[] strs) {
		LRUCache cache = new LRUCache(1);
		
		cache.set(2, 1);
		cache.get(2);
		cache.set(3, 2);
		cache.get(2);
	
	}
	
    HashMap<Integer, ListNode> map;
    int capacity;
    
    ListNode head;
    ListNode tail;
    
    public class ListNode {
        int key;
        int val;
        ListNode pre;
        ListNode next;
        
        public ListNode (int key, int val) {
            this.key = key;
            this.val = val;
            this.pre = null;
            this.next = null;
        }
    }
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<Integer, ListNode>();
        
        head = new ListNode(-1, -1);
        tail = new ListNode(-1, -1);
        
        head.next = tail;
        tail.pre = head;
    }
    
    public void moveToTail(ListNode node) {
        // connect the node into the list.
        node.pre = tail.pre;
        node.next = tail;
        
        tail.pre.next = node;
        tail.pre = node;
    }
    
    public void removeNode(ListNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
    
    public int get(int key) {
        if (map.get(key) == null) {
            return -1;
        } else {
            // move the node to the tail.
            ListNode node = map.get(key);
            removeNode(node);
            moveToTail(node);
            
            return node.val;
        }
    }
    
    public void set(int key, int value) {
        ListNode node = map.get(key);
        if (node == null) {
            node = new ListNode(key, value);
            
            // but 2: forget to add the node into the map.
            map.put(key, node);
        } else {
            // set the value.
            node.val = value;
            
            removeNode(node);
        }
        
        moveToTail(node);
        
        // the map is full, remove the head node.
        // bug 1: use map.size() instead of map.size.
        if (map.size() > capacity) {
            map.remove(head.next.key);
            
            removeNode(head.next);
        }
    }
}