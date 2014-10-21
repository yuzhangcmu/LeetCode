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
public class Connect {
    /*
     * 使用level traversal来做。
     * */
    public void connect1(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        
        TreeLinkNode dummy = new TreeLinkNode(0);
        Queue<TreeLinkNode> q = new LinkedList<TreeLinkNode>();
        q.offer(root);
        q.offer(dummy);
        
        while (!q.isEmpty()) {
            TreeLinkNode cur = q.poll();
            if (cur == dummy) {
                if (!q.isEmpty()) {
                    q.offer(dummy);
                }
                continue;
            }
            
            if (q.peek() == dummy) {
                cur.next = null;
            } else {
                cur.next = q.peek();
            }
            
            if (cur.left != null) {
                q.offer(cur.left);
            }
            
            if (cur.right != null) {
                q.offer(cur.right);
            }
        }
    }
    
    /* 试一下 recursion */
    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        
        rec(root);
    }

    public void rec(TreeLinkNode root) {
        if (root == null) {
            return;
        }

        if (root.left != null) {
            root.left.next = root.right;            
        }

        if (root.right != null) {
            root.right.next = root.next.left;
        }
        
        rec(root.left);
        rec(root.right);
    }
}
