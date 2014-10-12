package Algorithms.algorithm.others;
import java.util.Stack;

import Algorithms.tree.TreeNode;



public class Flatten {
    public static void main(String[] args) {
        
    }
    
    public void flatten(TreeNode root) {
        
        if(root == null) return;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = root;
 
        while(p != null || !stack.empty()){
 
            if(p.right != null){
                stack.push(p.right);
            }
 
            if(p.left != null){
                p.right = p.left;
                p.left = null;
            }else if(!stack.empty()){
                TreeNode temp = stack.pop();
                p.right=temp;
            }
 
            p = p.right;
        }
        
    }
}
