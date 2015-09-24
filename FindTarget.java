package Algorithms;

import java.util.ArrayList;

public class FindTarget {
    //public
    
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        public TreeNode(int val, TreeNode left, TreeNode right) {
            super();
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public TreeNode(int val) {
            super();
            this.val = val;
        }
    }
    
    public static void main(String[] strs) {
        TreeNode node8 = new TreeNode(8);
        
        TreeNode node3 = new TreeNode(3);
        TreeNode node1 = new TreeNode(1);
        TreeNode node6 = new TreeNode(6);
        TreeNode node4 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node10 = new TreeNode(10);
        TreeNode node13 = new TreeNode(13);
        TreeNode node14 = new TreeNode(14);
        
        node8.left = node3;
        node8.right = node10;
        
        node3.left = node1;
        node3.right = node6;
        
        node6.left = node4;
        node6.right = node7;
        
        node10.right = node14;
        node14.left = node13;
        
        findTarget(node8, 11);
    }
    
    public static void findTarget(TreeNode root, int target) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        dfs(root, list);
        
        int left = 0;
        int right = list.size() - 1;
        
        while (left < right) {
            int sum = list.get(left) + list.get(right); 
            if (sum == target) {
                System.out.println("The soluction: " + list.get(left) + " " + list.get(right));
                break;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        
        return;
    }

    public static void dfs(TreeNode root, ArrayList<Integer> list) {
        if (root == null) {
            return;
        }
        
        dfs(root.left, list);
        list.add(root.val);
        dfs(root.right, list);
    }
}
