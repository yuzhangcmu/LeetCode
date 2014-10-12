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
 * 
 * Remove Duplicates from Sorted List II Total Accepted: 21701 Total Submissions: 87380 My Submissions
   Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

   For example,
   Given 1->2->3->3->4->4->5, return 1->2->5.
   Given 1->1->1->2->3, return 2->3.
 */
public class DeleteDuplicates2 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        
        // record the head.
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode cur = dummy;
        
        // to delete the last node in the list of duplications.
        boolean del = false;
        
        while (cur != null) {
            if (cur.next != null
                && cur.next.next != null
                && cur.next.val == cur.next.next.val) {
                cur.next = cur.next.next;
                del = true;
            } else {
                // delete the last node in a duplicaions list.
                if (del) {
                    cur.next = cur.next.next;
                    
                    // set back the flag to be false.
                    del = false;
                } else {
                    // move forward.
                    cur = cur.next;
                }
            }
        }
        
        return dummy.next;
    }
}