package Algorithms.tree;

import java.util.Stack;

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class IsValidBST_1221_2014 {
    public boolean isValidBST1(TreeNode root) {
        // Just use the inOrder traversal to solve the problem.
        if (root == null) {
            return true;
        }
        
        Stack<TreeNode> s = new Stack<TreeNode>();
        TreeNode cur = root;
        
        TreeNode pre = null;
        
        while(true) {
            // Push all the left node into the stack.
            while (cur != null) {
                s.push(cur);
                cur = cur.left;
            }
            
            if (s.isEmpty()) {
                break;
            }
            
            // No left node, just deal with the current node.
            cur = s.pop();
            
            if (pre != null && pre.val >= cur.val) {
                return false;
            }
            
            pre = cur;
            
            // Go to the right node.
            cur = cur.right;
        }
        
        return true;
    }
    
    /*
        SOLUTION 2: Use the recursive version.
        REF: http://blog.csdn.net/fightforyourdream/article/details/14444883
    */
    public boolean isValidBST2(TreeNode root) {
        // Just use the inOrder traversal to solve the problem.
        if (root == null) {
            return true;
        }
        
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    public boolean dfs(TreeNode root, long low, long up) {
        if (root == null) {
            return true;
        }
        
        if (root.val >= up || root.val <= low) {
            return false;
        }
        
        return dfs(root.left, low, root.val) 
           && dfs(root.right, root.val, up);
    }
    
    /*
        SOLUTION 3: Use the recursive version2.
    */
    public boolean isValidBST3(TreeNode root) {
        // Just use the inOrder traversal to solve the problem.
        if (root == null) {
            return true;
        }
        
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    public class ReturnType {
        int min;
        int max;
        boolean isBST;
        public ReturnType (int min, int max, boolean isBST) {
            this.min = min;
            this.max = max;
            this.isBST = isBST;
        }
    }
    
    // BST:
    // 1. Left tree is BST;
    // 2. Right tree is BST;
    // 3. root value is bigger than the max value of left tree and 
    // smaller than the min value of the right tree.
    public ReturnType dfs(TreeNode root) {
        ReturnType ret = new ReturnType(Integer.MAX_VALUE, Integer.MIN_VALUE, true);
        if (root == null) {
            return ret;
        }
        
        ReturnType left = dfs(root.left);
        ReturnType right = dfs(root.right);
        
        // determine the left tree and the right tree;
        if (!left.isBST || !right.isBST) {
            ret.isBST = false;
            return ret;
        }
        
        // 判断Root.left != null是有必要的，如果root.val是MAX 或是MIN value,判断会失误
        if (root.left != null && root.val <= left.max) {
            ret.isBST = false;
            return ret;
        }
        
        if (root.right != null && root.val >= right.min) {
            ret.isBST = false;
            return ret;
        }
        
        return new ReturnType(Math.min(root.val, left.min), Math.max(root.val, right.max), true);
    }
    
    /*
        SOLUTION 4: Use the recursive version3.
    */
    TreeNode pre = null;
    
    public boolean isValidBST(TreeNode root) {
        // Just use the inOrder traversal to solve the problem.
        return dfs4(root);
    }
    
    public boolean dfs4(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        // Judge the left tree.
        if (!dfs4(root.left)) {
            return false;
        }
        
        // judge the sequence.
        if (pre != null && root.val <= pre.val) {
            return false;
        }
        pre = root;
        
        // Judge the right tree.
        if (!dfs4(root.right)) {
            return false;
        }
        
        return true;
    }
}