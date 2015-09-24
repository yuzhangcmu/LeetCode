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
public class PostorderTraversal {
    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        dfs(root, ret);
        return ret;
    }
    
    // Solution 1: rec
    public void dfs(TreeNode root, List<Integer> ret) {
        if (root == null) {
            return;
        }
        
        dfs(root.left, ret);
        dfs(root.right, ret);
        ret.add(root.val);
    }
    
    // Solution 2: iterator
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        if (root == null) {
            return ret;
        }
        
        Stack<TreeNode> s = new Stack<TreeNode>();
        Stack<Integer> out = new Stack<Integer>();
        
        s.push(root);
        
        while (!s.isEmpty()) {
            TreeNode cur = s.pop();
            out.push(cur.val);
            
            if (cur.left != null) {
                s.push(cur.left);
            }
            
            if (cur.right != null) {
                s.push(cur.right);
            }
        }
        
        while (!out.isEmpty()) {
            ret.add(out.pop());
        }
        
        return ret;
    }
}