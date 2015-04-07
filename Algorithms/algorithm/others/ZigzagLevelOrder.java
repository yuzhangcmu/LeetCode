package Algorithms.algorithm.others;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import Algorithms.tree.TreeNode;


public class ZigzagLevelOrder {
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        // when dir is true, direction is from left to right;
        boolean dir = true;
        
        ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
        if (root == null) {
            return rst;
        }
        
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        Stack<Integer> s = new Stack<Integer>();
        
        q.offer(root);
        while (!q.isEmpty()) {
            
            
            ArrayList<Integer> list = new ArrayList<Integer>();
            int size = q.size();
            for (int i = 0; i < size; i++){
                TreeNode curr = q.poll();
                if (dir == true) {
                    list.add(curr.val);
                } else {
                    s.add(curr.val);
                }
                
                if (curr.left != null) {
                    q.offer(curr.left);
                }
                
                if (curr.right != null) {
                    q.offer(curr.right);
                }
            }
            
            if (dir == true) {
                rst.add(list);
                dir = false;
            } else {
                while (!s.isEmpty()) {
                    list.add(s.pop());
                }
                rst.add(list);
                dir = true;
            }
            
            
        }
        return rst;
        
    }
    
    public static TreeNode LCA(TreeNode root,TreeNode a,TreeNode b){
        TreeNode left=null,right=null;
        if(root==null) return root;
        if(root==a || root==b) return root;
        left=LCA(root.left,a,b);
        right=LCA(root.right,a,b);
        if(left!=null && right!=null)return root;
        return (left!=null)?left:right; 
     }
    
    /*
    private TreeNode getRoot(TreeNode node) {
        while (node.parent != null) {
            node = node.parent;
        }
        return node;
    }*/


    public static boolean isDuplicate(String s) {
        if (s == null) {
            return false;
        }
        
        int check = 0;
        for(int i=0; i<s.length(); i++) {
            int val = (int)(s.charAt(i)-'a');
            if((check & (1<<val)) == 0) {
                check |= (1<<val);
            } else{
                return false;
            }
        }
        return true;
    }
    
    private TreeNode getAncestor(TreeNode root, TreeNode node1, TreeNode node2) {
        if (root == null || root == node1 || root == node2) {
            return root;
        }
        
        // Divide
        TreeNode left = getAncestor(root.left, node1, node2);
        TreeNode right = getAncestor(root.right, node1, node2);
        
        // Conquer
        if (left != null && right != null) {
            return root;
        } 
        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }
        return null;
    }
    
    /*
    public TreeNode latestCommonAncestor(TreeNode node1, TreeNode node2) {
        if (node1 == null || node2 == null) {
            return null;
        }
        TreeNode root = getRoot(node1);
        return getAncestor(root, node1, node2);
    }*/
    
    public static void main(String[] args) {
        ZigzagLevelOrder p = new ZigzagLevelOrder();
        
        /**
         *      1
         *    2     3
         *  4  5
         *7       
         */
        
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(6);
        
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        
        root.left.left.left = new TreeNode(7);
        
        //ArrayList<ArrayList<Integer>> rst = p.zigzagLevelOrder(root);
        ArrayList<ArrayList<Integer>> rst = p.zigzagLevelOrder(root);
        
        TreeNode rst2 = p.LCA(root, root.left.left.left, root.left.right);
        
        System.out.printf(rst2.toString());
        
        //abca
        //97 98

        //int[] A = new int[256]; //O(1) space complexity
        //String s = null;
        //A[97] = true;
        //A[98] = true;
        //A[99] = true;
        //boolean rst = ZigzagLevelOrder.isDuplicate(s);
        //System.out.printf("test, %s", rst);

        //O(1)
        
    }
}
