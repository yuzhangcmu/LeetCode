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

public class SwapPairs {
    // Solution 1: recursion.
    public ListNode swapPairs1(ListNode head) {
        return rec(head);
    }
    
    public ListNode rec(ListNode head) {
        // nodes less than 2, do nothing.
        if (head == null || head.next == null) {
            return head;
        }
        
        // reverse the next part.
        ListNode next = rec(head.next.next);
        
        // store the new head;
        ListNode headNew = head.next;
        
        // reverse the two nodes.
        headNew.next = head;
        head.next = next;
        
        return headNew;
    }
    
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // the node before the area which want to reverse
        ListNode pre = dummy;
        
        while (pre.next != null && pre.next.next != null) {
            // the node after the area which want to reverse
            ListNode next = pre.next.next.next;
            
            // reserve the new tail.
            ListNode tmp = pre.next;
            
            // link pre to the new head;
            pre.next = pre.next.next;
            
            // link the next node.
            pre.next.next = tmp;
            
            // linke the area to the next area.
            tmp.next = next;
            
            // move forward the pre node.
            pre = tmp;
        }
        
        return dummy.next;
    }
}
