package Algorithms.tree;


import java.util.ArrayList;
import java.util.Iterator;

/**
 * REFS:  
 * http://blog.csdn.net/fightforyourdream/article/details/16843303 面试大总结之二：Java搞定面试中的二叉树题目
 * http://blog.csdn.net/luckyxiaoqiang/article/details/7518888     轻松搞定面试中的二叉树题目 
 * http://www.cnblogs.com/Jax/archive/2009/12/28/1633691.html      算法大全（3） 二叉树 
 
 * 1. 求二叉树中两个节点的最低公共祖先节点：
 *      (1) LAC        求解最小公共祖先, 使用list来存储path.
 *        算法复杂度是：O(n), 这里遍历2次，加一次对最高为H的list的遍历. 并且有O(h)的空间复杂度。 
 *      (2) LCARec     递归算法 .
 *        算法复杂度是：O(n)
 *      (3) LCABstRec  递归求解BST树.
 *        算法复杂度: O(logN).
 */  

public class LCA_Demo {
    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val = val;
            left = null;
            right = null;                    
        }
    }
    /*
     * 1. 求二叉树中两个节点的最低公共祖先节点：
     * Recursion Version:
     * LACRec 
     * 1. If found in the left tree, return the Ancestor.
     * 2. If found in the right tree, return the Ancestor.
     * 3. If Didn't find any of the node, return null.
     * 4. If found both in the left and the right tree, return the root.
     * */
    public static TreeNode LACRec(TreeNode root, TreeNode node1, TreeNode node2) {
        if (root == null || node1 == null || node2 == null) {
            return null;
        }
        
        // If any of the node is the root, just return the root.
        if (root == node1 || root == node2) {
            return root;
        }
        
        // if no node is in the node, just recursively find it in LEFT and RIGHT tree.
        TreeNode left = LACRec(root.left, node1, node2);
        TreeNode right = LACRec(root.right, node1, node2);
        
        if (left == null) {  // If didn't found in the left tree, then just return it from right.
            return right;
        } else if (right == null) { // Or if didn't found in the right tree, then just return it from the left side.
            return left;
        } 
        
        // if both right and right found a node, just return the root as the Common Ancestor.
        return root;
    }
    
    /*
     * 11. 求BST中两个节点的最低公共祖先节点：
     * Recursive version:
     * LCABst 
     * 
     * 1. If found in the left tree, return the Ancestor.
     * 2. If found in the right tree, return the Ancestor.
     * 3. If Didn't find any of the node, return null.
     * 4. If found both in the left and the right tree, return the root.
     * */
    public static TreeNode LCABstRec(TreeNode root, TreeNode node1, TreeNode node2) {
        if (root == null || node1 == null || node2 == null) {
            return null;
        }
        
        // If any of the node is the root, just return the root.
        if (root == node1 || root == node2) {
            return root;
        }
        
        int min = Math.min(node1.val, node2.val);
        int max = Math.max(node1.val, node2.val);
        
        // if the values are smaller than the root value, just search them in the left tree.
        if (root.val > max) {
            return LCABstRec(root.left, node1, node2);
        } else if (root.val < min) {
        // if the values are larger than the root value, just search them in the right tree.    
            return LCABstRec(root.right, node1, node2);
        }
        
        // if root is in the middle, just return the root.
        return root;
    }
    
    /*
     * 解法1. 记录下path,并且比较之:
     * LAC
     * http://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/
     * */
    public static TreeNode LCA(TreeNode root, TreeNode r1, TreeNode r2) {
        // If the nodes have one in the root, just return the root.
        if (root == null || r1 == null || r2 == null) {
            return null;
        }
        
        ArrayList<TreeNode> list1 = new ArrayList<TreeNode>();
        ArrayList<TreeNode> list2 = new ArrayList<TreeNode>();
        
        boolean find1 = LCAPath(root, r1, list1);
        boolean find2 = LCAPath(root, r2, list2);
        
        // If didn't find any of the node, just return a null.
        if (!find1 || !find2) {
            return null;
        }
        
        // 注意: 使用Iterator 对于linkedlist可以提高性能。
        // 所以 统一使用Iterator 来进行操作。
        Iterator<TreeNode> iter1 = list1.iterator();
        Iterator<TreeNode> iter2 = list2.iterator();
        
        TreeNode last = null;
        while (iter1.hasNext() && iter2.hasNext()) {
            TreeNode tmp1 = iter1.next();
            TreeNode tmp2 = iter2.next();
            
            if (tmp1 != tmp2) {
                return last;
            }
            
            last = tmp1;
        }
        
        // If never find any node which is different, means Node 1 and Node 2 are the same one.
        // so just return the last one.
        return last;
    }
    
    public static boolean LCAPath(TreeNode root, TreeNode node, ArrayList<TreeNode> path) {
        // if didn't find, we should return a empty path.
        if (root == null || node == null) {
            return false;
        }
        
        // First add the root node.
        path.add(root);
        
        // if the node is in the left side.
        if (root != node 
                && !LCAPath(root.left, node, path)
                && !LCAPath(root.right, node, path)
                ) {
            // Didn't find the node. should remove the node added before.
            path.remove(root);
            return false;
        }
        
        // found
        return true;
    }
}