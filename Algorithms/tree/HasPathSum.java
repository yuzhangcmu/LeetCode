package Algorithms.tree;

public class HasPathSum {
    /**
     * Definition for binary tree public class TreeNode { int val; TreeNode
     * left; TreeNode right; TreeNode(int x) { val = x; } }
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null && sum == root.val) {
            return true;
        }

        return hasPathSum(root.left, sum - root.val)
                || hasPathSum(root.right, sum - root.val);
    }
    
    // Solution 2
    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        
        if (root.left == null && root.right == null && root.val == sum) {
            return true;
        }
        
        sum -= root.val;
        
        return hasPathSum2(root.left, sum) || hasPathSum2(root.right, sum);
    }
}
