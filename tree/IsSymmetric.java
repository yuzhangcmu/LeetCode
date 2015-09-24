package Algorithms.tree;

import java.util.Stack;

public class IsSymmetric {
    /**
     * Definition for binary tree public class TreeNode { int val; TreeNode
     * left; TreeNode right; TreeNode(int x) { val = x; } }
     */
    // solution 1:
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return isSymmetricTree(root.left, root.right);
    }

    /*
     * 判断两个树是否互相镜像 (1) 根必须同时为空，或是同时不为空
     * 
     * 如果根不为空: (1).根的值一样 (2).r1的左树是r2的右树的镜像 (3).r1的右树是r2的左树的镜像
     */
    public boolean isSymmetricTree1(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 == null || root2 == null) {
            return false;
        }

        return root1.val == root2.val
                && isSymmetricTree(root1.left, root2.right)
                && isSymmetricTree(root1.right, root2.left);
    }

    // solution 2:
    public boolean isSymmetricTree(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 == null || root2 == null) {
            return false;
        }

        Stack<TreeNode> s1 = new Stack<TreeNode>();
        Stack<TreeNode> s2 = new Stack<TreeNode>();

        // 一定记得初始化
        s1.push(root1);
        s2.push(root2);

        while (!s1.isEmpty() && !s2.isEmpty()) {
            TreeNode cur1 = s1.pop();
            TreeNode cur2 = s2.pop();

            if (cur1.val != cur2.val) {
                return false;
            }

            if (cur1.left != null && cur2.right != null) {
                s1.push(cur1.left);
                s2.push(cur2.right);
            } else if (!(cur1.left == null && cur2.right == null)) {
                return false;
            }

            if (cur1.right != null && cur2.left != null) {
                s1.push(cur1.right);
                s2.push(cur2.left);
            } else if (!(cur1.right == null && cur2.left == null)) {
                return false;
            }
        }

        return true;
    }
}