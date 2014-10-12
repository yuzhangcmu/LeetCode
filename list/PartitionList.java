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
public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        // the list which is greater than or equal to x
        ListNode dummyGE = new ListNode(0);
        
        // the list which is less than x
        ListNode dummyLess = new ListNode(0);
        
        // link the dummy to the head;
        dummyGE.next = head;
        
        ListNode pre = dummyGE;
        
        ListNode tailLess = dummyLess;
        
        // go through the list and remove the small node to a new list.
        while (pre.next != null) {
            if (pre.next.val < x) {
                // Add this to the less list.
                tailLess.next = pre.next;
                tailLess = tailLess.next;
                
                // remove the node from the current list.              
                pre.next = pre.next.next;
            } else {
                // move the pre node forward.
                pre = pre.next;
            }
        }
        
        // link the LESS list and the Large list.
        tailLess.next = dummyGE.next;
        
        return dummyLess.next;
    }
}
