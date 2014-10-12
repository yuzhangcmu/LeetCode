package Algorithms.algorithm.others;
public class SortList {
    public static void main(String[] str) {
        SortList sl = new SortList();
        ListNode head = new ListNode(1);
        sl.sortList(head);
        
    }
    
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return head;
        }
        
        ListNode mid = findMid(head);
        
        ListNode right = sortList(mid.next);
        mid.next = null;
        ListNode left = sortList(head);
        
        return merge(left, right);
    }
    
    private ListNode findMid(ListNode head) {
        ListNode s = head;
        ListNode f = head.next;
        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
        }
        
        return s;
    }
    
    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        
        ListNode tail = dummy;
        
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                tail.next = head1;
                head1 = head1.next;
            } else {
                tail.next = head2;
                head2 = head2.next;
            }
            
            tail = tail.next;
        }
        
        if (head1 != null) {
            tail.next = head1;
        } else {
            tail.next = head2;
        }
        
        return dummy.next;
    }
}