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
public class MergeKLists_1204 {
    /*
        SOL 1:
          使用merge sort和分治法完成
    */
    public ListNode mergeKLists1(List<ListNode> lists) {
        // 记得加上这个合法性判断。
        if (lists == null || lists.size() == 0) {
            return null;
        }
        
        return helper(lists, 0, lists.size() - 1);
    }
    
    /*
    l, r表示list的左右边界
    */
    public ListNode helper(List<ListNode> lists, int l, int r) {
        if (l < r) {
            int mid = l + (r - l) / 2;
            
            /*
               分治法。把问题分为2个更小的子问题：左边list的merge，和右边list的merge.
               再把2个生成的解合并在一起。
            */
            return merge(helper(lists, l, mid), helper(lists, mid + 1, r));
        }
        
        return lists.get(l);
    }
    
    public ListNode merge(ListNode n1, ListNode n2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        
        while (n1 != null && n2 != null) {
            if (n1.val < n2.val) {
                cur.next = n1;
                n1 = n1.next;
            } else {
                cur.next = n2;
                n2 = n2.next;
            }
            
            cur = cur.next;
        }
        
        if (n1 != null) {
            cur.next = n1;
        } else {
            cur.next = n2;
        }
        
        return dummy.next;
    }
    
    /*
        SOL 2:
          使用 priority Queue.
    */
    public ListNode mergeKLists(List<ListNode> lists) {
        // 记得加上这个合法性判断。
        if (lists == null || lists.size() == 0) {
            return null;
        }
        
        int size = lists.size();
        
        PriorityQueue<ListNode> q = new PriorityQueue<ListNode>(size, 
            new Comparator<ListNode>() {
                // 注意，此处参数用ListNode
                public int compare(ListNode o1, ListNode o2) {
                    return o1.val - o2.val;
                }
            }
        );
        
        // Add all the head node to the priority queue.
        for (ListNode node: lists) {
            if (node != null) {
                // Should skip the null node.s
                q.offer(node);    
            }
        }
        
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        
        while (!q.isEmpty()) {
            // get the smallest node from the queue.
            ListNode cur = q.poll();
            
            tail.next = cur;
            tail = tail.next;
            
            // 将下一个节点补充进来。
            if (cur.next != null) {
                q.offer(cur.next);
            }
        }
        
        return dummy.next;
    }
}