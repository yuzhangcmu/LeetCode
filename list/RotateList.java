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
public class RotateList {
    public static void main(String[] strs) {
        ListNode node1 = new ListNode(1);
        ListNode ret = rotateRight(node1, 0);
        System.out.println(ret.toString());
    }
    
    public static ListNode rotateRight(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        
        // when the list rotate n, it will go back.
        n = n % getlen(head);
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // get the pre node before the node which is the new head;
        // for example: Given 1->2->3->4->5->NULL and k = 2,
        // we should find 3
        // return 4->5->1->2->3->NULL.
        ListNode tail = dummy;
        while (n > 0) {
            tail = tail.next;
            n--;
        }
        
        ListNode pre = dummy;
        while (tail != null && tail.next != null) {
            tail = tail.next;
            pre = pre.next;
        }
        
        // cut the two list and connect the head to the tail.
        tail.next = dummy.next;
        
        ListNode headNew = pre.next;
        pre.next = null;
        
        return headNew;
    }
    
    // get the list lenght.
    public static int getlen(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        
        return len;
    }
}
