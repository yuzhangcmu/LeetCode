package Algorithms.tree;

import java.util.ArrayList;
import java.util.List;
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
public class PreorderTraversal {
    // sol1:
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        
        rec(root, ret);
        return ret;
    }
    
    public void rec(TreeNode root, List<Integer> ret) {
        if (root == null) {
            return;
        }
        
        ret.add(root.val);
        rec(root.left, ret);
        rec(root.right, ret);
    }
    
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        
        if (root == null) {
            return ret;
        }        
        
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        
        while (!s.isEmpty()) {
            TreeNode cur = s.pop();
            ret.add(cur.val);
            
            if (cur.right != null) {
                s.push(cur.right);
            }
            
            if (cur.left != null) {
                s.push(cur.left);
            }
        }
        
        return ret;
    }
}