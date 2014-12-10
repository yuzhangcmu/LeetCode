package Algorithms.list;

import Algorithms.algorithm.others.ListNode;

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class DetectCycle {
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        
        ListNode s = head;
        ListNode f = head;
        
        ListNode cross = null;
        
        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
            
            if (s == f) {
                cross = s;
                
                // remember to break here, or you will get a loop.
                break;
            }
        }
        
        // don't detect any cycle.
        if (cross == null) {
            return null;
        }
        
        // place the slow to the start again.
        s = head;
        while (true) {
            if (s == f) {
                return s;
            }
            
            s = s.next;
            f = f.next;
        }
    }
}