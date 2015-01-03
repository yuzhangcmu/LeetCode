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
public class BuildTree {
	public static void main(String[] strs) {
		int[] pre = {1, 2};
		int[] in = {2, 1};
		
		buildTree(pre, in);
	}
	
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        // bug 3: consider when length is 0.
        if (preorder == null || inorder == null || preorder.length == 0 || preorder.length != inorder.length) {
            return null;
        }
        
        // bug 4: end index is length - 1.
        return buildTree(preorder, inorder, 0, preorder.length - 1, 0, preorder.length - 1);
    }
    
    public static TreeNode buildTree(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd) {
        // base case;
        if (preStart > preEnd) {
            return null;
        }
        
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        
        int pos = findTarget(inorder, rootVal, inStart, inEnd);
        
        // bug 5: left number is pos - instart can't add 1
        int leftNum = pos - inStart;
        
        root.left = buildTree(preorder, inorder, preStart + 1, preStart + leftNum, inStart, pos - 1);
        root.right = buildTree(preorder, inorder, preStart + leftNum + 1, preEnd, pos + 1, inEnd);
        
        return root;
    }
    
    // bug 1: return type required.
    public static int findTarget(int[] A, int target, int start, int end) {
    	for (int i = start; i <= end; i++) {
            if (target == A[i]) {
                return i;
            }
        }
        
        return -1;
    }
}