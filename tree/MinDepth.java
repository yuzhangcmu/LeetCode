package Algorithms.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class MinDepth {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        return minDepthHelp(root);
    }
    
    /*
     *  The Recursion Version1:
     * */
    public int minDepthRec(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        //if there is not left tree, just consider the right tree.
        if (root.left == null) {
            return minDepthRec(root.right) + 1;
            
        //if there is not right tree, just consider the left tree.    
        } else if (root.right == null) {
            return minDepthRec(root.left) + 1;
        }
        
        return Math.min(minDepthRec(root.right), minDepthRec(root.left)) + 1;
    }    
    
    /*
     *  The Recursion Version2:
     *  这种递归解法更简单。因为在本层递归中不需要考虑左右子树是否为NULL的情况。因为我们直接
        把 null 设置为返回一个最大值，这样的话，如果出现空子树，它不会影响最小值。但是如果左
        右均为空，则应返回1（即是仅仅为根节点）
        
        而且这种做法更加合理。 因为如果是空树，应该是无法到达才是。这时就应该将值设置为最大。
     * */
    public int minDepthHelp(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        
        // 如果root是叶子节点，直接就可以退出咯。
        if (root.left == null && root.right == null) {
            return 1;
        }
        
        // root不是叶子节点，输出左右子树的最小值，再加上root本身
        return 1 + Math.min(minDepthHelp(root.left), minDepthHelp(root.right));
    }
    
    /*
     * The Iteration Version:
     * */
    public int minDepthHelpIterator(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        TreeNode dummy = new TreeNode(0);
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        
        q.offer(root);
        q.offer(dummy);
        
        //如果一开始就退出，则起码是有根节点。
        int depth = 1;
        
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur == dummy) {
                //if queue is not empty, just add a new dummy to the end to sign a new line.
                if (!q.isEmpty()) {
                    q.offer(dummy);
                }
                //don't deal with the dummynode.
                depth++;
                continue;
            }
            
            if (cur.left != null) {
                q.offer(cur.left);
            }
            
            if (cur.right != null) {
                q.offer(cur.right);
            }
            
            //this is a leaf node.
            if (cur.left == null && cur.right == null) {
                return depth;
            }
        }
        
        return 0;
    }
    
} 