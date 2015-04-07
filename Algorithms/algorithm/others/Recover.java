package Algorithms.algorithm.others;

import Algorithms.tree.TreeNode;

public class Recover {
    private TreeNode big = null;
    private TreeNode small = null;
    private TreeNode pre = new TreeNode(Integer.MIN_VALUE);
    
    public void recoverTree(TreeNode root) {
        if (root == null) {
            return;
        }
        traverse(root);
        
        int tmp = big.val;
        big.val = small.val;
        small.val = tmp;
        
    }
    
    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        
        traverse(root.left);
        
        if (root.val < pre.val) {
            if (big == null) {
                big = pre;
                small = root;
            } else if (root.val < small.val){
                small = root;
            }
        }
        
        if (big == null) {
            pre = root;
        }
        
        traverse(root.right);
    }
}
