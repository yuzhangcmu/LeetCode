package Algorithms.tree;

import java.util.Stack;

public class IsSymmetric_LeetCode {
    /*
     * Given a binary tree, check whether it is a mirror of itself (ie,
     * symmetric around its center).
     * 
     * For example, this binary tree is symmetric:
     * 
     * 1 / \ 2 2 / \ / \ 3 4 4 3
     * 
     * But the following is not:
     * 
     * 1 / \ 2 2 \ \ 3 3
     * 
     * Note: Bonus points if you could solve it both recursively and
     * iteratively.
     * 
     * confused what "{1,#,2,3}" means? > read more on how binary tree is
     * serialized on OJ.
     * 
     * OJ's Binary Tree Serialization:
     * 
     * The serialization of a binary tree follows a level order traversal, where
     * '#' signifies a path terminator where no node exists below.
     * 
     * Here's an example:
     * 
     * 1 / \ 2 3 / 4 \ 5
     * 
     * The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}".
     */
    //
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        return isMirrorRec(root.left, root.right);
    }

    /*
     * 判断两个树是否互相镜像 
     * (1) 根必须同时为空，或是同时不为空
     * 
     * 如果根不为空: 
     * (1).根的值一样 
     * (2).r1的左树是r2的右树的镜像 
     * (3).r1的右树是r2的左树的镜像
     */
    public boolean isMirrorRec(TreeNode r1, TreeNode r2) {
        if (r1 == null && r2 == null) {
            return true;
        }
        
        if (r1 == null || r2 == null) {
            return false;
        }
        
        // should compare the value of the root. remember this.
        return r1.val == r2.val 
                && isMirrorRec(r1.left, r2.right) 
                && isMirrorRec(r1.right, r2.left);
    }
    
    public boolean isMirror(TreeNode r1, TreeNode r2) {
        if (r1 == null && r2 == null) {
            return true;
        }
        
        if (r1 == null || r2 == null) {
            return false;
        }
        
        // We can do preOrder traverse to judge if the trees are mirror.
        Stack<TreeNode> s1 = new Stack<TreeNode>();
        Stack<TreeNode> s2 = new Stack<TreeNode>();
        s1.push(r1);
        s2.push(r2);
        
        while (!s1.isEmpty() && !s2.isEmpty()) {
            // pop the current node out.
            TreeNode cur1 = s1.pop();
            TreeNode cur2 = s2.pop();
            
            // Judge if the value of the node is equal.
            if (cur1.val != cur2.val) {
                return false;
            }
            
            // tree1的左节点，tree2的右节点，可以同时不为空，也可以同时为空，否则返回false. 
            if (cur1.left != null && cur2.right != null) {
                s1.push(cur1.left);
                s2.push(cur2.right);
            } else if (!(cur1.left == null && cur2.right == null)) {
                return false;
            }
            
            // tree1的右节点，tree2的左节点，可以同时不为空，也可以同时为空，否则返回false.
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
