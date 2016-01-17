package Algorithms.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class MaxSameTree2 {
    // 0:04
    public static void main(String[] strs) {
        /*
         *            0
         *        1       3
         *      4  5    6   8
         *     9
         * */
        TreeNode tree1 = new TreeNode(0);
        tree1.left = new TreeNode(1);
        tree1.left.left = new TreeNode(4);
        tree1.left.right = new TreeNode(5);
        
        tree1.left.left.left = new TreeNode(9);
        
        tree1.right = new TreeNode(3);
        tree1.right.left = new TreeNode(6);
        tree1.right.right = new TreeNode(8);
        
        TreeNode tree2 = new TreeNode(0);
        tree2.left = new TreeNode(1);
        tree2.left.left = new TreeNode(4);
        tree2.left.right = new TreeNode(5);
        
        tree2.left.left.left = new TreeNode(9);
        
        tree2.right = new TreeNode(3);
        tree2.right.left = new TreeNode(6);
        //tree2.right.right = new TreeNode(8);
        
        System.out.println(MaxSameTreeRec(tree1, tree2));
    }
    
    public static int MaxSameTreeRec(TreeNode r1, TreeNode r2) {
        HashMap<Long, Integer> map = new HashMap<Long, Integer> (); 
        
        ArrayList<TreeNode> list1 = new ArrayList<TreeNode> ();
        traversal(list1, r1);
        
        ArrayList<TreeNode> list2 = new ArrayList<TreeNode> ();
        traversal(list2, r2);
        
        int max = 0;
        
        for (TreeNode node1: list1) {
            for (TreeNode node2: list2) {
                int cnt = isSymmetricTree1(node1, node2, map);
                if (cnt > max) {
                    max = cnt; 
                }
            }
        }
        
        return max;
    }
    
    public static int isSymmetricTree1(TreeNode root1, TreeNode root2, HashMap<Long, Integer> map) {
        if (root1 == null && root2 == null) {
            return 0;
        }
        
        if (root1 == null || root2 == null) {
            return -1;
        }
        
        int hash1 = root1.hashCode();
        int hash2 = root2.hashCode();
        
        Long hashCode = (long) ((long)hash1 << 32 + hash2);
        
        Integer sameCnt = map.get(hashCode);
        if (sameCnt != null) {
            return sameCnt;
        }
        
        int n1 = isSymmetricTree1(root1.left, root2.left, map);
        int n2 = isSymmetricTree1(root1.right, root2.right, map);
        
        if (n1 >= 0 && n2 >= 0) {
            sameCnt = 1 + n2 + n1;
        } else {
            sameCnt = -1;
        }
        
        map.put(hashCode, sameCnt);
        return sameCnt;
    }
    
    public static void traversal (ArrayList<TreeNode> list, TreeNode r1) {
        if (r1 == null) {
            return;
        }
        
        list.add(r1);
        traversal(list, r1.left);
        traversal(list, r1.right);
    }
}
