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
    public static void main(String[] strs) {
        int[] inorder = {2, 1};
        int[] postorder = {1, 2};
        
        buildTree(inorder, postorder);
    }
    
    public TreeNode buildTree1(int[] inorder, int[] postorder) {
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
    
    public static TreeNode buildTree(int[] inorder, int[] postorder) { 
        // 1952
        if (inorder == null || postorder == null) {
            return null;
        }
        
        return rec(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }
    
    public static TreeNode rec(int[] inorder, int[] postorder, int inStart, int inEnd, int postStart, int postEnd) {
        if (inStart > inEnd) {
            return null;
        }    
        
        TreeNode root = new TreeNode(postorder[postEnd]);
        int rootIndex = 0;
        for (int i = inStart; i < inEnd; i++) {
            if (inorder[i] == root.val) {
                rootIndex = i;
                break;
            }
        }
        
        int leftNum = rootIndex - inStart;
        
        root.left = rec(inorder, postorder, inStart, rootIndex - 1, postStart, postStart + leftNum - 1);
        root.right = rec(inorder, postorder, rootIndex + 1, inEnd, postStart + leftNum, postEnd - 1);
        
        return root;
    }
}