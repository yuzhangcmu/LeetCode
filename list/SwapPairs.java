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

public class SwapPairs {
    public static void main(String[] strs) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        
        ListNode swap = swapPairs1(n1);
        System.out.println(swap.toString());
    }
    
    
    // Solution 1: recursion.
    public static ListNode swapPairs1(ListNode head) {
        return rec(head);
    }
    
    public static ListNode rec(ListNode head) {
        // nodes less than 2, do nothing.
        if (head == null || head.next == null) {
            return head;
        }
        
        // reverse the next part.        
        ListNode next = rec(head.next.next);
        
        if (next == null) {
            System.out.println("next is null ");
        } else {
            System.out.println("next:" + next.val + " ");
        }
        
        
        // store the new head;
        ListNode headNew = head.next;
        
        //System.out.println("headNew:" + headNew.val + " ");
        
        // reverse the two nodes.
        headNew.next = head;
        head.next = next;        
        
        System.out.println("headNew: " + headNew.toString());
        
        return headNew;
    }
    
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // the node before the area which want to reverse
        ListNode pre = dummy;
        
        while (pre.next != null && pre.next.next != null) {
            // the node after the area which want to reverse
            ListNode next = pre.next.next.next;
            
            // reserve the new tail.
            ListNode tmp = pre.next;
            
            // link pre to the new head;
            pre.next = pre.next.next;
            
            // link the next node.
            pre.next.next = tmp;
            
            // linke the area to the next area.
            tmp.next = next;
            
            // move forward the pre node.
            pre = tmp;
        }
        
        return dummy.next;
    }
}
