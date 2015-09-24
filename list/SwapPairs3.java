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
public class SwapPairs3 {
    // Solution 1: the recursion version.
    public ListNode swapPairs1(ListNode head) {
        if (head == null) {
            return null;
        }
        
        return rec(head);
    }
    
    public ListNode rec(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode next = head.next.next;
        
        // 翻转后面的链表
        next = rec(next);
        
        // store the new head.
        ListNode tmp = head.next;
        
        // reverse the two nodes.
        head.next = next;
        tmp.next = head;
        
        return tmp;
    }
    
    // Solution 2: the iteration version.
    public ListNode swapPairs(ListNode head) {
        // 如果小于2个元素，不需要任何操作
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // The node before the reverse area;
        ListNode pre = dummy;
        
        while (pre.next != null && pre.next.next != null) {
            // The last node of the reverse area;
            ListNode tail = pre.next.next;
            
            ListNode tmp = pre.next;
            pre.next = tail;
            
            ListNode next = tail.next;
            tail.next = tmp;
            tmp.next = next;
            
            // move forward the pre node.
            pre = tmp;
        }
        
        return dummy.next;
    }
    
    // Solution 3: the iteration version.
    public ListNode swapPairs3(ListNode head) {
        // 如果小于2个元素，不需要任何操作
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // The node before the reverse area;
        ListNode pre = dummy;
        
        while (pre.next != null && pre.next.next != null) {
            ListNode next = pre.next.next.next;
            
            ListNode tmp = pre.next;
            pre.next = pre.next.next;
            pre.next.next = tmp;
            
            tmp.next = next;
                        
            // move forward the pre node.
            pre = tmp;
        }
        
        return dummy.next;
    }
}