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
public class Connect2 {
    /*
    Solution 1: space: O(N), the nodes of the last level.
    */
    public void connect1(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        
        TreeLinkNode dummy = new TreeLinkNode(0);
        Queue<TreeLinkNode> q = new LinkedList<TreeLinkNode>();
        
        q.offer(root);
        q.offer(dummy);
        
        while(!q.isEmpty()) {
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
    
    /*
    Solution 2: recursion with O(h) space. H: the height of the tree.
    */
    public void connect2(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        
        TreeLinkNode cur = root.next;
        TreeLinkNode next = null;
        // this is very important. should exit after found the next.
        while (cur != null && next == null) {
            if (cur.left != null) {
                next = cur.left;
            } else if (cur.right != null) {
                next = cur.right;
            } else {
                cur = cur.next;
            }
        }
        
        if (root.right != null) {
            root.right.next = next;
            next = root.right;
        }
        
        if (root.left != null) {
            root.left.next = next;
        }
        
        // The order is very important. We should deal with right first!
        connect2(root.right);
        connect2(root.left);
    }
    
    /*
    Solution 3: iterator with O(1) space.
    */
    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }

        connIterator(root);
    }

    /*
    This is a iterator version.
    */
    public void connIterator(TreeLinkNode root) {
        TreeLinkNode leftEnd = root;
        while (leftEnd != null) {
            TreeLinkNode p = leftEnd;

            // Connect all the nodes in the next level together.
            while (p != null) {

                // find the 
                TreeLinkNode next = findLeftEnd(p.next);

                if (p.right != null) {
                    p.right.next = next;
                    next = p.right;
                }

                if (p.left != null) {
                    p.left.next = next;
                }

                // continue to deal with the next point.
                p = p.next;
            }

            // Find the left end of the NEXT LEVEL.
            leftEnd = findLeftEnd(leftEnd);
        }
        
    }

    // Find out the left end of the next level of Root TreeNode.
    public TreeLinkNode findLeftEnd(TreeLinkNode root) {
        while (root != null) {
            if (root.left != null) {
                return root.left;
            }

            if (root.right != null) {
                return root.right;
            }

            root = root.next;
        }

        return null;
    }
}
