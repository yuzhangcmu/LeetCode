package Algorithms.tree;



/**
 *         1
 *     2     3
 *   4  5   6  7
 *               9   
 * 
 * 
 * 1. find 1, 3
 * 2. find 4, 7
 * 3. find 4, 5
 * 4, find 6, 9
 * @author zhangyu
 * 
 *  *     3
 *     2     6
 *   1     5  7
 *               9   
 * 
 * 
 * 1. find 1, 3
 * 2. find 4, 7
 * 3. find 4, 5
 * 4, find 6, 9
 * @author zhangyu
 *
 */

public class LCA {
    private static int cnt = 0;
    
    public static void main(String[] strs) {
//        TreeNode node1 = new TreeNode(1);
//        
//        TreeNode node2 = new TreeNode(2);
//        TreeNode node3 = new TreeNode(3);
//        TreeNode node4 = new TreeNode(4);
//        TreeNode node5 = new TreeNode(5);
//        TreeNode node6 = new TreeNode(6);
//        TreeNode node7 = new TreeNode(7);
//        TreeNode node9 = new TreeNode(9);
//        
//        node1.left = node2;
//        node2.left = node4;
//        node2.right = node5;
//        
//        node1.right = node3;
//        node3.left = node6;
//        node3.right = node7;
//        
//        node7.right = node9;
        
        TreeNode node1 = new TreeNode(1);
        
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node9 = new TreeNode(9);
        
        node3.left = node2;
        node3.right = node6;
        
        node2.left = node1;
        
        node6.left = node5;
        node6.right = node7;
        
        node7.right = node9;
                
        LCA lca = new LCA();
        //TreeNode rst = lca.getLCA(node1, node1, node3);
        //TreeNode rst = lca.getLCA(node1, node4, node7);
        //TreeNode rst = lca.getLCA(node1, node4, node5);
        //TreeNode rst = lca.getLCA(node1, node6, node9);
        TreeNode rst = lca.getLCABST(node3, node7, node9);
        
        if (rst == null) {
            System.out.print(0);
            return;
        }
        System.out.println(rst.toString());
        System.out.println(cnt);
    }
    
    public TreeNode getLCA(TreeNode root, TreeNode node1, TreeNode node2) {
        cnt++;
        if (root == null || node1 == root || root == node2) {
            return root;
        }
        
        TreeNode left = getLCA(root.left, node1, node2);
        TreeNode right = getLCA(root.right, node1, node2);
        
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
    
    public TreeNode getLCABST(TreeNode root, TreeNode node1, TreeNode node2) {
        cnt++;
        if (root == null || node1 == root || root == node2) {
            return root;
        }
        
        if (root.val > node1.val && root.val > node2.val) {
            return getLCABST(root.left, node1, node2);
        } else if (root.val < node1.val && root.val < node2.val) {
            return getLCABST(root.right, node1, node2);
        }
        
        return root;
    }
    
    

}
