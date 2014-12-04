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
public class Partition {
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode pre = dummy;
        ListNode cur = head;
        
        // Record the big list.
        ListNode bigDummy = new ListNode(0);
        ListNode bigTail = bigDummy;
        
        while (cur != null) {
            if (cur.val >= x) {
                // Unlink the cur;
                pre.next = cur.next;
               
                // Add the cur to the tail of the new link.
                bigTail.next = cur;
                cur.next = null;
               
                // Refresh the bigTail.
                bigTail = cur;
               
                // 移除了一个元素的时候，pre不需要修改，因为cur已经移动到下一个位置了。
            } else {
                pre = pre.next;
            }
            
            cur = pre.next;
        }
        
        // Link the Big linklist to the smaller one.
        pre.next = bigDummy.next;
        
        return dummy.next;
    }
}