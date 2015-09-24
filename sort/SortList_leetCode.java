package Algorithms.sort;

import Algorithms.algorithm.others.ListNode;

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class SortList_leetCode {
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }
        
        // !! Remember to add this line! Because this may cause infinit loop.
        if (head.next == null) {
            return head;
        }
        
        ListNode midPre = findmidPre(head);
        
        ListNode right = sortList(midPre.next);
        midPre.next = null;
        
        ListNode left = sortList(head);
        
        return merge(left, right);
    }
    
    // get the node before mid.
    public ListNode findmidPre(ListNode head) {
        if (head == null) {
            return null;
        }
        
        ListNode slow = head;
        ListNode fast = head.next;
        
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        
        return slow;
    }
    
    // get the node before mid.
    public ListNode merge(ListNode h1, ListNode h2) {
        ListNode dummy = new ListNode(0);
        
        ListNode cur = dummy;
        while (h1 != null && h2 != null) {
            if (h1.val < h2.val) {
                cur.next = h1;
                h1 = h1.next;
            } else {
                cur.next = h2;
                h2 = h2.next;
            }
            
            cur = cur.next;
        }
        
        if (h1 != null) {
            cur.next = h1;
        } else {
            cur.next = h2;
        }
        
        return dummy.next;
    }
}