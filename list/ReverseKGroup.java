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
    
    /*
     * Solution 2: iteration.
     * */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode cur = head;
        ListNode pre = dummy;
        
        int cnt = 0;
        
        while (cur != null) {
            cnt++;
            if (cnt == k) {
                cnt = 0;
                pre = reverse(pre, cur.next);
            }
            cur = cur.next;
        }
        
        return dummy.next;
    }
    
    /**
     * Reverse a link list between pre and next exclusively
     * an example:
     * a linked list:
     * 0->1->2->3->4->5->6
     * |           |   
     * pre        next
     * after call pre = reverse(pre, next)
     * 
     * 0->3->2->1->4->5->6
     *          |  |
     *          pre next
     * @param pre 
     * @param next
     * @return the reversed list's last node, which is the precedence of parameter next
     */
    private static ListNode reverse(ListNode pre, ListNode next){
        ListNode cur = pre.next;
        
        // record the new tail.
        ListNode last = cur;
        while (cur != next) {
            ListNode tmp = cur.next;
            cur.next = pre.next;
            pre.next = cur;
            cur = tmp;
        }
        
        last.next = next;
        return last;
    }

}
