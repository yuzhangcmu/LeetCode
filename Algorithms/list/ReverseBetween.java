package Algorithms.list;

import Algorithms.algorithm.others.ListNode;

public class ReverseBetween {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null) {
            return head;
        }
        
        if (m >= n) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode pre = dummy;
        
        //1. get the pre node before m.
        for (int i = m; i > 1; i--) {
            pre = pre.next;
        }
        
        // record the tail of the reversed link.
        ListNode reverseTail = pre.next;
        pre.next = null;
        
        // reverse the link.
        ListNode cur = reverseTail;
        
        for (int i = n - m + 1; i > 0; i--) {
            if (i == 1) {
                // 这里是翻转段后的第一个元素 .
                reverseTail.next = cur.next;
            }
            
            ListNode tmp = cur.next;
            
            cur.next = pre.next;
            pre.next = cur;
            
            cur = tmp;
        }
        
        return dummy.next;
    }

}
