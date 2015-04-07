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
public class InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0);
        
        while (head != null) {
            ListNode pre = dummy;
            
            // 注意，这里要用<= 来保证算法的稳定性
            // 因为假如有2个数相同，后面的数后找到，也要插入到后面才可以。也就是说当=的时候，是继续往下走
            while (pre.next != null && pre.next.val <= head.val) {
                pre = pre.next;
            }
            
            // unlink the node from the original link. And record the next position.
            ListNode headNext = head.next;
            head.next = pre.next;
            
            pre.next = head;
            head = headNext;
        }
        
        return dummy.next;
    }
}