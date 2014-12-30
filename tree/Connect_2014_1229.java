package Algorithms.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Connect_2014_1229 {
    /*
        1. Iterator.
    */
    public void connect1(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        
        Queue<TreeLinkNode> q = new LinkedList<TreeLinkNode>();
        q.offer(root);
        
        while (!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                TreeLinkNode cur = q.poll();
                
                // ERROR 2: forget to determine if root don't have left and right.
                if (cur.left == null) {
                    return;
                }
                
                cur.left.next = cur.right;
                cur.right.next = cur.next == null ? null : cur.next.left;
                // bug 1: should put the offer inside the for loop
                q.offer(cur.left);
                q.offer(cur.right);
            }
        }
    }
    
    /*
        2. Iterator. More simple version.
    */
    public void connect2(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        
        Queue<TreeLinkNode> q = new LinkedList<TreeLinkNode>();
        q.offer(root);
        
        while (!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                TreeLinkNode cur = q.poll();
                
                // bug 1: should judge the size!
                cur.next = (i == size - 1) ? null: q.peek();
                
                if (cur.left != null) {
                    q.offer(cur.left);
                    q.offer(cur.right);
                }
            }
        }
    }
    
    /*
        3. A recursion version.
    */
    public void connect3(TreeLinkNode root) {
        if (root == null || root.left == null) {
            return;
        }
        
        root.left.next = root.right;
        root.right.next = root.next == null ? null: root.next.left;
        
        connect(root.left);
        connect(root.right);
    }
    
    /*
        4. Another constant iterator version.
    */
    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        
        TreeLinkNode leftEnd = root;
        while (leftEnd != null && leftEnd.left != null) {
            TreeLinkNode cur = leftEnd;
            while (cur != null) {
                cur.left.next = cur.right;
                cur.right.next = cur.next == null ? null: cur.next.left;
                
                cur = cur.next;
            }
            
            leftEnd = leftEnd.left;
        }
    }
}