package Algorithms.sort;

import java.util.LinkedList;

import Algorithms.algorithm.others.ListNode;

public class MergeSort_LinkedList {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(0);
        ListNode node2 = new ListNode(15);
        ListNode node3 = new ListNode(6);
        ListNode node4 = new ListNode(9);
        ListNode node5 = new ListNode(4);
        
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        
        System.out.println(node1.toString());
        sort(node1);
        System.out.println(node1.toString());
    }
    
    public static ListNode sort(ListNode head) {
        if (head == null) {
            return null;
        }
        
        // 注意一定要加这一行，否则会不断对1个元素无限分下去
        if (head.next == null) {
            return head;
        }
        
        ListNode mid = findMidPre(head);
        
        // 将list切为2个list.
        ListNode right = mid.next;
        mid.next = null;
        
        //调用将2边分别排序
        ListNode left = sort(head);
        right = sort(right);
        
        // 将2个已经排序的List Merge在一起 
        return merge(left, right); 
    }
    
    public static ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);

        // cur 表示新链的尾部.
        ListNode cur = dummy;
        
        while (head1 != null && head2 != null) {
            // 将2个链中较小的一个接到新链的尾部
            if (head1.val < head2.val) {                
                cur.next = head1;
                head1 = head1.next;
            } else {
                cur.next = head2;
                head2 = head2.next;
            }

            // 将扫描指针移动到新链的尾部0
            cur = cur.next;
        }        

        // 把未扫描完毕的链接在新链的结尾即可
        if (head1 != null) {
            cur.next = head1;
        } else {
            cur.next = head2;
        }
        
        // 返回新链的头部 
        return dummy.next;   
    }
    
    // 这个函数是寻找Mid的前一个节点，
    // 技巧就是：一开始就将Fast放在head的前一个节点，这样当只有2个节点的时候：
    // 1->2->null
    // slow 会停在1处 ，这样就可以处理只有2个节点的情况.
    public static ListNode findMidPre(ListNode head) {
        if (head == null) {
            return null;
        }
        
        ListNode fast = head.next;
        ListNode slow = head;
        
        if (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }
}
