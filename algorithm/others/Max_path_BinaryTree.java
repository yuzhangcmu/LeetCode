package Algorithms.algorithm.others;
import java.util.ArrayList;

import Algorithms.tree.TreeNode;


public class Max_path_BinaryTree {
    public static void main(String args[]){
        TreeNode treeNode = new TreeNode(1);
        treeNode.right = new TreeNode(2);
        treeNode.right.right = new TreeNode(3);
        treeNode.right.right.right = new TreeNode(4);
        treeNode.right.right.right.right = new TreeNode(5);
        
        System.out.printf("Max path sum: %d",
                maxPathSum(treeNode)
                );
        
        int[] a = new int[3];
        a[0] = 1;
        a[2] = 3;
        
        TEST(a);
        
        System.out.printf("a: %d", a[0]);
        //a = {0, 1, 2, 3};
        
        
        ArrayList<Integer> a1 = new ArrayList<Integer>();
        ArrayList<Integer> b1 = a1;
        
        a1.add(1);
        b1.add(2);
        
        System.out.printf("size of a: %d", a1.size());
        
    }
    
    public static void TEST(int[] a){
        a[0] = 4;
    }
    
    public static int maxPathSum(TreeNode root) {
        // set the result to min value.
        int result = Integer.MIN_VALUE;
        
        if (root == null){
            return result;
        }
        
        // divide the problem to be two parts. If it is smaller than 0, discard it.
        int left_signal = Math.max(signal_Sum(root.left), 0);
        int right_signal = Math.max(signal_Sum(root.right), 0);
        
        System.out.printf("LeftSignal:%d right_signal:%d\n", left_signal, right_signal);
        
        // get the max of the cross.
        int cross_max = root.val + left_signal + right_signal;
        
        int left_max = maxPathSum(root.left);
        int right_max = maxPathSum(root.right);
        
        System.out.printf("cross_max:%d left_max:%d right_max:%d\n", cross_max, left_max, right_max);

        
        result = Math.max(cross_max, Math.max(left_max, right_max));
        
        return result;
    }
    
    // return the path which include the root and one of the branch.
    private static int signal_Sum(TreeNode root){
        if (root == null){
            return Integer.MIN_VALUE;
        }
        
        int result = Math.max(Math.max(signal_Sum(root.left), 0) + root.val, Math.max(signal_Sum(root.right), 0) + root.val);
        
        System.out.printf("SIGNAL_SUM result:%d\n", result);
        
        // if branch < 0, we can just return the root node value.
        return result;
    }
}
