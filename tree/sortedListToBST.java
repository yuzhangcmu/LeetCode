package Algorithms.tree;

import Algorithms.algorithm.others.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {h
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; next = null; }
 * }
 */
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class sortedListToBST {
    public TreeNode sortedListToBST1(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        
        ListNode pre = head;
        
        if (head == null) {
            return null;
        }
        
        TreeNode root = null;
        if (head.next == null) {
            root = new TreeNode(head.val);
            root.left = null;
            root.right = null;
            return root;
        }
        
        // get the middle node.
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            
            // record the node before the SLOW.
            pre = slow;
            slow = slow.next;
        }
        
        // cut the list to two parts.
        pre.next = null;
        TreeNode left = sortedListToBST1(head);
        TreeNode right = sortedListToBST1(slow.next);
        
        root = new TreeNode(slow.val);
        root.left = left;
        root.right = right;
        
        return root;
    }
    
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        
        int size = 0;
        ListNode cur = head;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
         
        CurrNode curNode = new CurrNode(head); 
        return sortedListToBSTHelp(curNode, size);
    }
    
    public class CurrNode {
        ListNode node;
        
        CurrNode(ListNode node) {
            this.node = node;
        }
    }
    
    // when the recursion is done, the curr node should point to the node
    // which is the next of the block.
    public TreeNode sortedListToBSTHelp(CurrNode curr, int size) {
        if (size <= 0) {
            return null;
        }
        
        TreeNode left = sortedListToBSTHelp(curr, size/2);
        
        // because we want to deal with the right block.
        TreeNode root = new TreeNode(curr.node.val);
        curr.node = curr.node.next;
        
        TreeNode right = sortedListToBSTHelp(curr, size - 1 - size/2);
        
        root.left = left;
        root.right = right;
        
        return root;
    }
    
    // Solution 3:
    // Recursion.
    ListNode curNode = null;
    
    public TreeNode sortedListToBST3(ListNode head) {
        if (head == null) {
            return null;
        }
        
        int size = 0;
        ListNode cur = head;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        
        curNode = head;
        return dfs(head, size);
    }
    
    // Use the size to control.
    public TreeNode dfs(ListNode head, int size) {
        if (size <= 0) {
            return null;
        }
        
        TreeNode left = dfs(head, size / 2);
        TreeNode root = new TreeNode(curNode.val);
        
        // move the current node to the next place.
        curNode = curNode.next;
        TreeNode right = dfs(curNode, size - size / 2 - 1);
        
        root.left = left;
        root.right = right;
        
        return root;
    }
}

