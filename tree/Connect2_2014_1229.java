package Algorithms.tree;

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Connect2_2014_1229 {
    // SOLUTION 1: Iteration
    public void connect1(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        
        TreeLinkNode leftEnd = root;
        
        // Bug 1: don't need " && leftEnd.left != null"
        while (leftEnd != null) {
            TreeLinkNode cur = leftEnd;
            
            TreeLinkNode dummy = new TreeLinkNode(0);
            TreeLinkNode pre = dummy;
            while (cur != null) {
                if (cur.left != null) {
                    pre.next = cur.left;
                    pre = cur.left;
                }
                
                if (cur.right != null) {
                    pre.next = cur.right;
                    pre = cur.right;
                }
                
                cur = cur.next;
            }
            leftEnd = dummy.next;
        }
    }
    
    // SOLUTION 2: REC
    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        
        TreeLinkNode dummy = new TreeLinkNode(0);
        TreeLinkNode pre = dummy;
        
        if (root.left != null) {
            pre.next = root.left;
            pre = root.left;
        }
        
        if (root.right != null) {
            pre.right = root.right;
            pre = root.right;
        }
        
        if (root.left == null && root.right == null) {
            return;
        }
        
        // Try to find the next node;
        TreeLinkNode cur = root.next;
        TreeLinkNode next = null;
        while (cur != null) {
            if (cur.left != null) {
                next = cur.left;
                break;
            } else if (cur.right != null) {
                next = cur.right;
                break;
            } else {
                cur = cur.next;
            }
        }
        
        pre.next = next;
        
        if (root.right != null && (root.right.left != null || root.right.right != null)) {
            connect(root.right);    
        }
        
        if (root.left != null && (root.left.left != null || root.left.right != null)) {
            connect(root.left);    
        }
        
    }
}