package Algorithms.list;

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
public class SortList {
    public ListNode sortList(ListNode head) {
        // Nodes should be more than 2.
        if (head == null || head.next == null) {
            return head;
        }
        
        // get the mid node.
        ListNode midPre = getMidPre(head);
        
        // Cut the two list.
        ListNode right = midPre.next;
        midPre.next = null;
        
        // Sort the left side and the right side.
        ListNode left = sortList(head);
        right = sortList(right);
        
        // Merge the two sides together.
        return merge(left, right);
    }
    
    // get the pre node before mid.
    public ListNode getMidPre1(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
    
    // get the pre node before mid.
    public ListNode getMidPre(ListNode head) {
        ListNode slow = head;
        
        // fast提前一点儿。这样就可以得到前一个节点喽。
        ListNode fast = head.next;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
    
    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                cur.next = head1;
                head1 = head1.next;
            } else {
                cur.next = head2;
                head2 = head2.next;
            }
            
            cur = cur.next;
        }
        
        if (head1 != null) {
            cur.next = head1;
        } else {
            cur.next = head2;
        }
        
        return dummy.next;
    }
}