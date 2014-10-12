package Algorithms.list;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

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
public class MergeKLists {
    public ListNode mergeKLists(List<ListNode> lists) {
        // 注意：lists.size() == 0 一定要处理，否则建立queue时 不能输入初始size为0
        if (lists == null || lists.size() == 0) {
            return null;
        }
        
        Comparator<ListNode> comparator = new Comparator<ListNode>() {
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        };
        
        // create a priority Queue.
        PriorityQueue<ListNode> q = new PriorityQueue<ListNode>(lists.size(), comparator);
        
        // add all the first nodes to the QUEUE.
        for (ListNode node: lists) {
            if (node != null) {
                // don't add EMPTY lists.
                q.offer(node);    
            }
        }
        
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        
        while (!q.isEmpty()) {
            // get a list node from the queue.
            ListNode node = q.poll();
            
            // add the node to the result link.
            tail.next = node;
            tail = tail.next;
            
            // add a new node from the lists to the priority queue.
            if (node.next != null) {
                q.offer(node.next);
            }
        }
        
        return dummy.next;
    }
}
