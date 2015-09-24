package Algorithms.sort;

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
public class InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        // 使用dummy node来记录新的头节点，每次把旧链找合适的位置来插入即可 
        if (head == null) {
            return null;
        }
        
        ListNode dummy = new ListNode(0);
        
        // 记录下插入位置的前趋节点.
        ListNode pre = dummy;
        
        while (head != null) {
            // every time we should reset the pre to the BEGIN OF THE LIST.
            pre = dummy;
            
            // 这样可以找到pre.next为第一个比head大的节点
            // if we use <= here we can keep the Algorithm stable.
            while (pre.next != null && pre.next.val <= head.val) {
                pre = pre.next;
            }
            
            // backup the next node of head;
            ListNode tmp = head.next;
            
            // Insert the head between PRE and PRE.next.
            head.next = pre.next;
            pre.next = head;
            
            // set head to the next node.
            head = tmp;
        }
        
        return dummy.next;
    }
}