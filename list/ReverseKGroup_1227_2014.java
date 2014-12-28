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
public class ReverseKGroup_1227_2014 {
	public static void main(String[] strs) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		
		node1.next = node2;
		
		System.out.println(reverseKGroup(node1, 1));
		
	}
	
    public ListNode reverseKGroup1(ListNode head, int k) {
        if (head == null) {
            return null;
        }        
        
        return rec(head, k);
    }
    
    // Solution 1: Recursion.
    public ListNode rec(ListNode head, int k) {
        // Reverse k and link to the next section.
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // find the tail node of the section. If not find, just return.
        int cnt = k;
        ListNode tail = dummy;
        while (cnt > 0 && tail != null) {
            cnt--;
            tail = tail.next;
        }
        
        // We don't have k nodes to revers.
        // bug 1: we should judge that if tail == null to avoid the overflow.
        if (tail == null) {
            return head;
        }
        
        // cut the 2 list.
        ListNode next = tail.next;
        tail.next = null;
        
        // reverse the first list.
        ListNode newHead = reverse(head);
        
        // reverse the next section.
        next = rec(next, k);
        
        // link the 2 sections.
        head.next = next;
        
        return newHead;
    }
    
    public ListNode reverse(ListNode head) {
        ListNode dummy = new ListNode(0);
        while (head != null) {
            ListNode tmp = head.next;
            head.next = dummy.next;
            dummy.next = head;
            
            head = tmp;
        }
        
        return dummy.next;
    }
    
    /*
    SOLUTION 2: A better rec version.
    */
    public ListNode reverseKGroup2(ListNode head, int k) {
        if (head == null) {
            return null;
        }        
        
        return rec2(head, k);
    }
    
    public ListNode rec2(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        
        ListNode dummy = new ListNode(0);
        
        ListNode cur = head;
        int cnt = 0;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = dummy.next;
            dummy.next = cur;
            
            cur = tmp;
            
            cnt++;
            
            // reverse a k group.
            if (cnt == k) {
                // BUG 1: 
                head.next = rec2(tmp, k);
                return dummy.next;
            }
        }
        
        // we don't have k nodes.
        if (cnt != k) {
            cur = dummy.next;
            dummy.next = null;
            
            // reverse again.
            while (cur != null) {
                ListNode tmp = cur.next;
                cur.next = dummy.next;
                dummy.next = cur;
                
                cur = tmp;
            }
        }
        
        return dummy.next;
    }
    
    /*
    SOLUTION 3: A Iteration version.
    */
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }        
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode pre = dummy;
        ListNode cur = pre.next;
        
        int cnt = 0;
        while (cur != null) {
            cnt++;
            cur = cur.next;
            
            if (cnt == k) {
                cnt = 0;
                pre = reverseSection(pre, cur);
                cur = pre.next;
            }
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
    private static ListNode reverseSection(ListNode pre, ListNode next){
        ListNode cur = pre.next;
        
        // record the new tail.
        ListNode tail = cur;
        
        while (cur != next) {
            ListNode tmp = cur.next;
            cur.next = pre.next;
            pre.next = cur;
            cur = tmp;
        }
        
        tail.next = next;
        return tail;
    }
}