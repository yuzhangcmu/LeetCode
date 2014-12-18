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
public class IsBalanced {
    // Solution 1:
    public boolean isBalanced1(TreeNode root) {
        return dfs(root).isBalanced;
    }
    
    // bug 1: inner class is like:  "public class ReturnType {", no ()
    public class ReturnType {
        boolean isBalanced;
        int depth;
        
        ReturnType(int depth, boolean isBalanced) {
            this.depth = depth;
            this.isBalanced = isBalanced;
        }
    }
    
    public ReturnType dfs(TreeNode root) {
        ReturnType ret = new ReturnType(0, true);
        
        if (root == null) {
            return ret;
        }
        
        ReturnType left = dfs(root.left);
        ReturnType right = dfs(root.right);
        
        ret.isBalanced = left.isBalanced 
                         && right.isBalanced 
                         && Math.abs(left.depth - right.depth) <= 1;
                         
        // bug 2: remember to add 1( the root depth )                 
        ret.depth = Math.max(left.depth, right.depth) + 1;
        
        return ret;
    }
    
    // Solution 2:
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }        
        
        return isBalanced(root.left) && isBalanced(root.right)
            && Math.abs(getDepth(root.left) - getDepth(root.right)) <= 1;
    }
    
    public int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        return Math.max(getDepth(root.left), getDepth(root.right)) + 1;
    }
}