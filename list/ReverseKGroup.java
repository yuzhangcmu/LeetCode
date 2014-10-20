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
public class ReverseKGroup {
    // Solution 1:
    // recursion.
    public ListNode reverseKGroup1(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        
        return rec(head, k);
    }
    
    public class ReturnType{
        ListNode head;
        ListNode tail;
        
        ReturnType(ListNode head, ListNode tail) {
            this.head = head;
            this.tail = tail;
        }
    }
    
    public ListNode rec(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        
        dummy.next = head;
        
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = dummy.next;
            dummy.next = cur;
            cur = tmp;
            len++;
            
            // reverse all the k nodes.
            if (len == k) {
                // link the tail to the next node in the list.
                head.next = rec(tmp, k);
                break;
            }
        }
        
        if (len != k) {
            ListNode node = dummy.next;
            dummy.next = null;
            
            while (node != null) {
                ListNode next = node.next;
                node.next = dummy.next;
                dummy.next = node;
                node = next;
            }
        }
        
        return dummy.next;
    }
}
