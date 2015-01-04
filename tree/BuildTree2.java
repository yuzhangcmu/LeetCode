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
public class BuildTree2 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null) {
            return null;
        }
        
        return dfs(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }
    
    public TreeNode dfs(int[] inorder, int[] postorder, int inL, int inR, int postL, int postR) {
        if (inL > inR) {
            return null;
        }
        
        // create the root node.
        TreeNode root = new TreeNode(postorder[postR]);
        
        // find the position of the root node in the inorder traversal.
        int pos = 0;
        for (; pos <= inR; pos++) {
            if (inorder[pos] == postorder[postR]) {
                break;
            }
        }
        
        int leftNum = pos - inL;
        
        root.left = dfs(inorder, postorder, inL, pos - 1, postL, postL + leftNum - 1);
        root.right = dfs(inorder, postorder, pos + 1, inR, postL + leftNum, postR - 1);
        
        return root;
    }
}