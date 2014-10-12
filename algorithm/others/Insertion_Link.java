package Algorithms.algorithm.others;

public class Insertion_Link {
    public static void main(String[] str) {
        Insertion_Link il = new Insertion_Link();
        ListNode head = new ListNode(-5);
        head.next = new ListNode(4);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next = new ListNode(7);
        
        //il.insertionSortList(head);
        
        String s = "//...";
        String[] stub = s.split("/+");
        System.out.print(stub.toString());
    }
    
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0);

        while (head != null) {
            ListNode node = dummy;
            while (node.next != null && node.next.val < head.val) {
                
                //System.out.print(head.toString());
                node = node.next;
                
                //System.out.print("After print node ");
                //System.out.print(node.toString());
                
            }
            
            System.out.print("Node: ");
            System.out.print(node.toString());
            System.out.print("Head: ");
            System.out.print(head.toString());
            
            ListNode temp = head.next;
            head.next = node.next;
            node.next = head;
            head = temp;
            
            System.out.print("print dummy: ");
            System.out.print(dummy.toString());
        }
        
        

        return dummy.next;
    }
}
