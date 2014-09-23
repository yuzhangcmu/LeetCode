package Algorithms;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;


public class PostOrder {
    /**
     * Definition for binary tree
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        
        if (root == null) {
            return result;
        }
        
        Stack<TreeNode> s = new Stack<TreeNode>();
        Stack<Integer> s2 = new Stack<Integer>();
        
        int currval = 0;
        //s.push(root);
        //s2.push(root.val);
        
        TreeNode curr = root;
        
        while (true) {
            // store all the right node and the root node. then shift to the left node.            
            while (curr != null) {
                if (curr.right != null) {
                //    s.push(curr.right);
                }
                s.push(curr);
                s2.push(curr.val);
                curr = curr.left;
            }
            
            if (s.isEmpty()) {
                return result;
            }
            
            curr = s.pop();
            currval = s2.pop();
            if (curr.right != null && (s.isEmpty() || s.peek() != null)) {
                // this is to indicate that this node has been visited, 
                // when come back from the right branch, we can know that we don't need 
                // to search right branch again.
                s.push(null);
                s.push(curr);
                s2.push(0);
                s2.push(curr.val);
                curr = curr.right;
            } else {
                result.add(curr.val);
                if (!s.isEmpty() && s.peek() == null) {
                    s.pop(); // give up the "null" node.
                    s2.pop();
                }
                
                curr = null;
            }
        }
    }
    
    public static void main(String[] args) {
        PostOrder p = new PostOrder();
        
        TreeNode root = new TreeNode(1);
        
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(6);
        
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        
        root.left.left.left = new TreeNode(7);
        
        ArrayList<Integer> rst = p.postorderTraversal(root);
        
        System.out.printf(rst.toString());
        
        
    }
}
