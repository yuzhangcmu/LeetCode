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
public class RecoverTree {
    TreeNode pre = null;
    TreeNode first = null;
    TreeNode second = null;
    
    
    public void recoverTree(TreeNode root) {
        inOrder(root);
        
        // swap the value of first and second node.
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }
    
    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        
        // inorder traverse.
        inOrder(root.left);

        /*
        Find the place which the order is wrong.
        For example: 1 3 4 6 7 8 10 13 14
        Wrong order: 1 3 8 6 7 4 10 13 14      
        FIND:            ___
        Then we find:        ___
        8, 6 是错误的序列, 但是，7，4也是错误的序列。
        因为8，6前面的序列是正确的，所以8，6一定是后面的序列交换来的。 
        而后面的是比较大的数字，也就是说8一定是被交换过来的。而7，4
        中也应该是小的数字4是前面交换过来的。

        用反证法来证明：
        假设：6是后面交换过来的
        推论: 那么8比6还大，那么8应该也是后面交换来的，
        这样起码有3个错误的数字了
        而题目是2个错误的数字，得证，只应该是8是交换过来的。
        */

        // 判断 pre 是否已经设置
        if (pre != null && pre.val > root.val) {
            if (first == null) {
                // 首次找到反序.
                first = pre;
                second = root;
            } else {
                // 第二次找到反序，更新Second.
                second = root;
            }
        }

        pre = root;

        // inorder traverse.
        inOrder(root.right);
    }
}
