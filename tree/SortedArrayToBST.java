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
public class SortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] num) {
        if (num == null || num.length == 0) {
            return null;
        }        
        
        return sortedArrayToBST(num, 0, num.length - 1);
    }
    
    public TreeNode sortedArrayToBST(int[] num, int left, int right) {
        // The base case:
        if (left > right) {
            return null;
        }
        
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(num[mid]);
        
        TreeNode leftNode = sortedArrayToBST(num, left, mid - 1);
        TreeNode rightNode = sortedArrayToBST(num, mid + 1, right);
        
        root.left = leftNode;
        root.right = rightNode;
        
        return root;
    }
}