package Algorithms.list;

import Algorithms.algorithm.others.ListNode;

public class ReverseLinkedList2 {
    public static void main(String[] args) {
        ReverseLinkedList2 rLL = new ReverseLinkedList2();
        ListNode head = new ListNode(3);
        head.next = new ListNode(5);
        rLL.reverseBetween(head, 2, 2);
    }
    
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return null;
        }
        
        if (head.next == null) {
            return head;
        }
        
        ListNode dumyNode = new ListNode(0);
        dumyNode.next = head;
        
        ListNode tmpHead = dumyNode;
        
        // get the head before the reverse list;
        for (int i = m; i > 1; i--) {
            tmpHead = tmpHead.next;
        }
      
        
        // store the pre pointer
        ListNode pre = null;
        ListNode pst = tmpHead.next;
        
        // store the head for later use.
        ListNode reverseHead = pst;
        
        // reverse the specific linkedlist.
        for (int i = 0; i < n - m + 1; i++) {
            ListNode tmp = pst.next;
            pst.next = pre;
            pre = pst;
            pst = tmp;
        }
        
        tmpHead.next = pre;
        reverseHead.next = pst;
        
        if (m > 1) {
            return head;    
        } else {
            return pre;
        }
        
    }

}
