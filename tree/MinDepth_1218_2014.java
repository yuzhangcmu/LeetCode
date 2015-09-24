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
public class MinDepth_1218_2014 {
    // SOLUTION 1:
    public int minDepth1(TreeNode root) {
        /*
         主页君认为，在这应该是属于未定义行为，这里我们定义为MAX会比较好，因为
         null就是取不到任何节点，没有path，不应该将最小值定为0.
        */
        if (root == null) {
            return 0;
        }
        
        return dfs(root);
    }
    
    /*
     *  The Recursion Version:
     *  这种递归解法更简单。因为在本层递归中不需要考虑左右子树是否为NULL的情况。因为我们直接
        把 null 设置为返回一个最大值，这样的话，如果出现空子树，它不会影响最小值。但是如果左
        右均为空，则应返回1（即是仅仅为根节点）
        
        而且这种做法更加合理。 因为如果是空树，应该是无法到达才是。这时就应该将值设置为最大。
     * */
    public int dfs(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        
        // The base case: the root is a leaf.
        if (root.left == null && root.right == null) {
            return 1;
        }
        
        return Math.min(dfs(root.left), dfs(root.right)) + 1;
    }
    
    // SOLUTION 2: 
    // Level Traversal:
    public int minDepth(TreeNode root) {
        /*
         主页君认为，在这应该是属于未定义行为，这里我们定义为MAX会比较好，因为
         null就是取不到任何节点，没有path，不应该将最小值定为0.
        */
        if (root == null) {
            return 0;
        }
        
        int level = 0;
        
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        
        while (!q.isEmpty()) {
            int size = q.size();
            level++;
            for (int i = 0; i < size; i++) {
               TreeNode cur = q.poll();
               
               if (cur.left == null && cur.right == null) {
                   return level;
               }
               
               if (cur.left != null) {
                   q.offer(cur.left);
               }
               
               if (cur.right != null) {
                   q.offer(cur.right);
               }
            }
        }
        
        return 0;
    }
}