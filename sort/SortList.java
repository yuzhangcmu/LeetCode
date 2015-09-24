package Algorithms.sort;

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
public class SortList {
    public static void main(String[] strs) {
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(18);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(54);
        ListNode n5 = new ListNode(6);
        ListNode n6 = new ListNode(90);
        ListNode n7 = new ListNode(2);
        ListNode n8 = new ListNode(1);
        ListNode n9 = new ListNode(19);
        ListNode n10 = new ListNode(30);
        
        
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        n8.next = n9;
        n9.next = n10;
        n10.next = null;
        
        ListNode ret = sortList(n1);
        System.out.println(ret.toString());
    }
    
    public static ListNode sortList1(ListNode head) {
        // Nodes should be more than 2.
        if (head == null || head.next == null) {
            return head;
        }
        
        // get the mid node.
        ListNode midPre = getMidPre(head);
        
        // Cut the two list.
        ListNode right = midPre.next;
        midPre.next = null;
        
        // Sort the left side and the right side.
        ListNode left = sortList(head);
        right = sortList(right);
        
        // Merge the two sides together.
        return merge(left, right);
    }
    
    // get the pre node before mid.
    public static ListNode getMidPre1(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
    
    // get the pre node before mid.
    public static ListNode getMidPre(ListNode head) {
        ListNode slow = head;
        
        // fast提前一点儿。这样就可以得到前一个节点喽。
        ListNode fast = head.next;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
    
    public static ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                cur.next = head1;
                head1 = head1.next;
            } else {
                cur.next = head2;
                head2 = head2.next;
            }
            
            cur = cur.next;
        }
        
        if (head1 != null) {
            cur.next = head1;
        } else {
            cur.next = head2;
        }
        
        return dummy.next;
    }
    
    /*
    The Solution 2:
    Quick Sort.
    */
    public static ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }
        
        // Sort the list from 0 to len - 1
        return quickSort(head);
    }
    
    // The quick sort algorithm
    
    // All the elements are the same!
    public static boolean isDuplicate(ListNode head) {
        while (head != null) {
            if (head.next != null && head.next.val != head.val) {
                return false;
            }            
            
            head = head.next;
        }
        
        return true;
    }
    
    public static ListNode quickSort(ListNode head) {
        if (head == null) {
            return null;
        }
        
        // 如果整个链是重复的，直接跳过。
        if (isDuplicate(head)) {
            return head;
        }
        
        // Use the head node to be the pivot.
        ListNode headNew = partition(head, head.val);
        
        // Find the pre position of the pivoit.
        ListNode cur = headNew;
        
        ListNode dummy = new ListNode(0);
        dummy.next = headNew;
        
        ListNode pre = dummy;
        
        // Find the pre node and the position of the piviot.
        while (cur != null) {
            if (cur.val == head.val) {
                break;
            }
            
            // move forward.
            cur = cur.next;
            pre = pre.next;
        }
        
        // Cut the link to be three parts.
        pre.next = null;
        
        // Get the left link;
        ListNode left = dummy.next;
        
        // Get the right link.
        ListNode right = cur.next;
        cur.next = null;
        
        // Recurtion to call quick sort to sort left and right link.
        left = quickSort(left);
        right = quickSort(right);
        
        // Link the three part together.
        
        // Link the first part and the 2nd part.
        if (left != null) {
            dummy.next = left;
            
            // Find the tail of the left link.
            while (left.next != null) {
                left = left.next;
            }
            left.next = cur;
        } else {
            dummy.next = cur;
        }
        
        cur.next = right;
        
        // The new head;
        return dummy.next;
    }
    
    // Return the new head;
    public static ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode pre = dummy;
        ListNode cur = head;
        
        // Record the big list.
        ListNode bigDummy = new ListNode(0);
        ListNode bigTail = bigDummy;
        
        while (cur != null) {
            if (cur.val >= x) {
                // Unlink the cur;
                pre.next = cur.next;
               
                // Add the cur to the tail of the new link.
                bigTail.next = cur;
                cur.next = null;
               
                // Refresh the bigTail.
                bigTail = cur;
               
                // 移除了一个元素的时候，pre不需要修改，因为cur已经移动到下一个位置了。
            } else {
                pre = pre.next;
            }
            
            cur = pre.next;
        }
        
        // Link the Big linklist to the smaller one.
        pre.next = bigDummy.next;
        
        return dummy.next;
    }
}