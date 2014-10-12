package Algorithms.algorithm.others;

import java.util.ArrayDeque;

import Algorithms.tree.TreeNode;

public class IsSymmetric {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        if (root.left == null && root.right == null) {
            return true;
        } else if (root.left == null || root.right == null) {
            return false;
        }
        
        // left and right are both not null
        ArrayDeque<TreeNode> queueL = new ArrayDeque<TreeNode>();
        ArrayDeque<TreeNode> queueR = new ArrayDeque<TreeNode>();
        
        queueL.offer(root.left);
        queueR.offer(root.right);
        
        int num = 1;
        while (num != 0) {
            TreeNode l = queueL.poll();
            TreeNode r = queueR.poll();
            
            if (l != null && r != null) { // if they are all not null
                if (l.val != r.val) {
                    return false;
                }
                queueL.offer(l.left);
                queueL.offer(l.right);
                queueR.offer(r.right);
                queueR.offer(r.left);
                num += 4;
            } else if (l == null || r == null) {
                return false;
            }
            
        }
        
        return true;
    }
}
