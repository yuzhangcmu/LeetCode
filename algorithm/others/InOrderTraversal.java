package Algorithms.algorithm.others;
import java.util.ArrayList;
import java.util.Stack;

import Algorithms.tree.TreeNode;


public class InOrderTraversal {
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> rst = new ArrayList<Integer>();
        
        TreeNode curr = root;
        Stack<TreeNode> s = new Stack<TreeNode>();
        
        //s.push(curr);
        while (true) {
            // store the root and go to the far left.
            while (curr != null) {
                s.push(curr);
                curr = curr.left;
            }
            
            if (s.isEmpty()) {
                return rst;
            }
            
            curr = s.pop();
            
            rst.add(curr.val);
            
            // traversal the right branch.
            curr = curr.right;
        }
        
        //return rst;
    }
}
