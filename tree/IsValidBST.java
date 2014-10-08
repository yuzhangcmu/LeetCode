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
public class IsValidBST {
    public boolean isValidBST(TreeNode root) {
        return isValidBSTHelp(root).isValidBST;
    }
    
    public ReturnType isValidBSTHelp(TreeNode root) {
        ReturnType ret = new ReturnType(Integer.MAX_VALUE, Integer.MIN_VALUE, true);
        if (root == null) {
            return ret;
        }
        
        ReturnType left = isValidBSTHelp(root.left);
        ReturnType right = isValidBSTHelp(root.right);
        
        /* the left tree and the right tree should both be Valid BST.
           And the value of the root should be in the middle.
        */
        
        if (!left.isValidBST 
             || !right.isValidBST
             || left.max >= root.val
             || right.min <= root.val
             ) {
            ret.isValidBST = false;
            return ret;
        }
        
        // get the min value of the tree;
        ret.min = Math.min(left.min, root.val);
        
        // get the max value of the tree, consider the right node may be null;
        ret.max = Math.max(right.max, root.val);
        
        return ret;
    }
    
    public class ReturnType {
        int min;
        int max;
        boolean isValidBST;
        
        ReturnType(int min, int max, boolean isValidBST) {
            this.min = min;
            this.max = max;
            this.isValidBST = isValidBST;
        }
    }
}