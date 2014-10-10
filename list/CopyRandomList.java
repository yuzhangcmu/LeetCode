package Algorithms.list;

/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class CopyRandomList {
    public static class RandomListNode {
        int label;
        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
    }
    
    public static void main(String[] strs) {
        RandomListNode node = new RandomListNode(-1);
        RandomListNode copy = copyRandomList(node);
    }
    
    public static RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        
        // copy the list;
        RandomListNode cur = head;
        while (cur != null) {
            RandomListNode nodeCopy = new RandomListNode(cur.label);
            
            // insert the node between the old link.
            nodeCopy.next = cur.next;
            cur.next = nodeCopy;
            
            cur = cur.next.next;
        }
        
        // copy the radom list.
        cur = head;
        while (cur != null) {
            cur.next.random = cur.random;
            cur = cur.next.next;
        }
        
        // divide the two list.
        RandomListNode headCopy = head.next;
        cur = head;
        
        // track the new list.
        RandomListNode curNew = headCopy;
        while (cur != null) {
            cur.next = cur.next.next;
            cur = cur.next;
            
            if (curNew.next != null) {
                curNew.next = curNew.next.next;
                curNew = curNew.next;
            }
            
        }
        
        return curNew;
    }
}
