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
public class ReorderList {
    public void reorderList(ListNode head) {
        // 4 STEP:
        // 1. find the mid.
        // 2. cut the list to two list.
        // 3. REVERSE the right side.
        // 4. MERGE the two list.
        if (head == null) {
            return;
        } else if (head.next == null) {
            return;
        }
        
        ListNode pre = findMidPre(head);
        
        // cut the two list.
        ListNode right = pre.next;
        pre.next = null;
        
        // reverse the right link.
        right = reverse(right);
        
        merge(head, right);
    }
    
    // 找到mid的前一个节点 
    // EXAMPLE: 1-> 4-> 2-> null
    // 我们要找的是1.
    // 这样可以把它断开为2个链：1->null   and    4->2->null
    // 这样做的是因为比如1->4->null
    // 我们只能找1，如果你找到的是4，你是不能断开为2个的。
    // 对于这个题目可能无所谓，但是对于merge sort你不能分开2个节点就不能工作.
    public ListNode findMidPre(ListNode head) {
        if (head == null) {
            return null;
        }
        
        ListNode slow = head;
        ListNode fast = head.next;
        
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        
        return slow;
    }
    
    // reverse the linked list.
    public ListNode reverse(ListNode head) {
        if (head == null) {
            return null;
        }
        
        ListNode dummy = new ListNode(0);
        while (head != null) {
            // store the next node.
            ListNode tmp = head.next;
            
            // insert the head into the head!
            head.next = dummy.next;
            dummy.next = head;
            
            head = tmp;
        }
        
        return dummy.next;
    }
    
    // merge head1 and head 2 one by one.
    public void merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        
        ListNode cur = dummy;
        
        while (head1 != null && head2 != null) {
            // 注意这里容易出错。head1要先指向它的下一个，再处理head2，否则cur.next=head2这里会改掉
            // head1的指向.
            cur.next = head1;
            cur = cur.next;
            head1 = head1.next;
            
            cur.next = head2;
            cur = cur.next;
            head2 = head2.next;
        }
        
        if (head1 != null) {
            cur.next = head1;
        } else {
            cur.next = head2;
        }
    }
}