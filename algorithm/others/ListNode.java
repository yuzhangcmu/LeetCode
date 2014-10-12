package Algorithms.algorithm.others;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) {
        val = x;
        next = null;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode head = this;
        while (head != null) {
            sb.append(head.val);
            sb.append(" ");
            head = head.next;
        }
        
        sb.append("\n");
        return sb.toString();
    }
    
}
