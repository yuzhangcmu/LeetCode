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
public class MaxPathSum {
    public static void main(String[] strs) {
        TreeNode root = new TreeNode(2);
        TreeNode left = new TreeNode(-1);
        
        root.left = left;
        
        System.out.println(maxPathSum(root));
    }
    
    public static class ReturnType {
        int maxSingle;
        int max;
        ReturnType (int maxSingle, int max) {
            this.max = max;
            this.maxSingle = maxSingle;
        }
    }
    
    public static int maxPathSum(TreeNode root) {
        return dfs(root).max;        
    }
    
    public static ReturnType dfs(TreeNode root) {
        ReturnType ret = new ReturnType(Integer.MIN_VALUE, Integer.MIN_VALUE);
        if (root == null) {
            return ret;
        }
        
        ReturnType left = dfs(root.left);
        ReturnType right = dfs(root.right);
        
        int cross = root.val;
        
        // if any of the path of left and right is below 0, don't add it.
        cross += Math.max(0, left.maxSingle);
        cross += Math.max(0, right.maxSingle);
        
        int maxSingle = root.val + Math.max(left.maxSingle, right.maxSingle);
        
        // may left.maxSingle and right.maxSingle are below 0
        maxSingle = Math.max(maxSingle, root.val);
        
        ret.maxSingle = maxSingle;
        ret.max = Math.max(right.max, left.max);
        ret.max = Math.max(ret.max, cross);
        
        return ret;
    }
}