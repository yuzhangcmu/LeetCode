package Algorithms.list;

import Algorithms.algorithm.others.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // move tail N nodes faster than pre.
        ListNode tail = dummy;
        // 注意，这里用for比较好一点，用while时，经常不记得将参量--
        for (int i = n; i > 0; i--) {
            tail = tail.next;    
        }
        
        ListNode pre = dummy;
        
        // get the node before the node we want to delete.
        while (tail != null && tail.next != null) {
            tail = tail.next;
            pre = pre.next;
        }
        
        // DELTE.
        if (pre.next != null) {
            pre.next = pre.next.next;    
        }
        
        return dummy.next;
    }
}