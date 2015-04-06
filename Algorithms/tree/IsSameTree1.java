package Algorithms.tree;

import java.util.Stack;

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class IsSameTree1 {
    // solution 1:
    public boolean isSameTree1(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        
        if (p == null || q == null) {
            return false;
        }
        
        return p.val == q.val &&
           isSameTree(p.left, q.left) &&
           isSameTree(p.right, q.right);
    }
    
    // Solution 2:
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        
        if (p == null || q == null) {
            return false;
        }
        
        Stack<TreeNode> s1 = new Stack<TreeNode>();
        Stack<TreeNode> s2 = new Stack<TreeNode>();
        
        s1.push(p);
        s2.push(q);
        
        while (!s1.isEmpty() && !s2.isEmpty()) {
            TreeNode cur1 = s1.pop();
            TreeNode cur2 = s2.pop();
            
            // 弹出的节点的值必须相等 
            if (cur1.val != cur2.val) {
                return false;
            }
            
            // tree1的right节点，tree2的right节点，可以同时不为空，也可以同时为空，否则返回false.
            if (cur1.left != null && cur2.left != null) {
                s1.push(cur1.left);
                s2.push(cur2.left);
            } else if (!(cur1.left == null && cur2.left == null)) {
                return false;
            }
            
            // tree1的左节点，tree2的left节点，可以同时不为空，也可以同时为空，否则返回false.
            if (cur1.right != null && cur2.right != null) {
                s1.push(cur1.right);
                s2.push(cur2.right);
            } else if (!(cur1.right == null && cur2.right == null)) {
                return false;
            }
        }
        
        return true;
    }
}