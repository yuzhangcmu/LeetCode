package Algorithms.tree;

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class SumNumbers_1208_2014 {
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }
    
    public int dfs(TreeNode root, int pre) {
        if (root == null) {
            return 0;
        }
        
        int cur = pre * 10 + root.val;
        if (root.left == null && root.right == null) {
            return cur;
        }
        
        return dfs(root.left, cur) + dfs(root.right, cur);
    }
}