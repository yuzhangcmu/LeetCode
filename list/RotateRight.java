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
public class RotateRight {
    // Solution 1:
    public ListNode rotateRight1(ListNode head, int n) {
        if (head == null) {
            return head;
        }
        
        int len = getLen(head);
        
        // 不需要重复地rotate.
        n = n % len;
        
        if (n == 0) {
            return head;
        }
        
        ListNode end = head;
        while (n > 0) {
            end = end.next;
            n--;
        }
        
        ListNode pre = head;
        while (end.next != null) {
            pre = pre.next;
            end = end.next;
        }
        
        ListNode headNew = pre.next;
        end.next = head;
        pre.next = null;
        
        return headNew;
    }
    
    public int getLen(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }
    
    // Solution 2: 使用dummynode.
    public ListNode rotateRight(ListNode head, int n) {
        if (head == null) {
            return head;
        }
        
        int len = getLen(head);
        
        // 不需要重复地rotate.
        n = n % len;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode end = dummy;
        while (n > 0) {
            end = end.next;
            n--;
        }
        
        ListNode pre = dummy;
        while (end.next != null) {
            pre = pre.next;
            end = end.next;
        }
        
        end.next = dummy.next;
        ListNode headNew = pre.next;
        pre.next = null;
        
        return headNew;
    }
}