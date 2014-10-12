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
public class DeleteDuplicates {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode cur = dummy;
        
        while (cur != null) {
            if (cur.next != null 
               && cur.next.next != null
               && cur.next.val == cur.next.next.val) {
                // remove cur.next;
                cur.next = cur.next.next;
            } else {
                cur = cur.next;    
            }
        }
        
        return dummy.next;
    }
}