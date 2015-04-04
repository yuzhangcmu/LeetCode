package Algorithms.algorithm.thread;

import Algorithms.tree.TreeNode;

public class Deadlock_II {
    public static void main(String[] args) {
        // final String s1 = "ratan";
        // final String s2 = "vimal";
        // // t1 tries to lock resource1 then resource2
        //
        // // t2 tries to lock resource2 then resource1
        //
        // Thread11 t1 = new Thread11(s1, s2);
        // Thread22 t2 = new Thread22(s1, s2);
        //
        // t1.start();
        // t2.start();
        // int n = Integer.parseInt(in.nextLine().trim());
//        int n = 1;
//        for (int i = 0; i < n; i++) {
//            // int num = Integer.parseInt(in.nextLine().trim());
//            int num = 5;
//
//            int cnt = 0;
//            while (num > 0) {
//                if (num % 2 == 0) {
//                    num /= 2;
//                } else {
//                    num--;
//                }
//                cnt++;
//            }
//
//            System.out.println(cnt);
//            // 5 = 0 + 1 * 2 * 2 + 1
//        }
        TreeNode r1 = new TreeNode(0);
        TreeNode r2 = new TreeNode(1);
        TreeNode r3 = new TreeNode(2);
        TreeNode r4 = new TreeNode(3);
        TreeNode r5 = new TreeNode(4);
        TreeNode r6 = new TreeNode(5);
        TreeNode r7 = new TreeNode(6);
        TreeNode r8 = new TreeNode(7);
        
        r1.left = r2;
        r2.left = r3;
        r3.left = r4;
        r4.left = r5;
        r5.left = r6;
        r6.left = r7;
        r7.left = r8;
        
        System.out.println(isBalanced(r1));
        System.out.println(level);
        System.out.println(level2);
    }

    static int level = 0;
    static int level2 = 0;
    
    
    // Solution 2:
    public static boolean isBalanced(TreeNode root) {
        level++;
        System.out.println("enter isBalanced");
        if (root == null) {
            return true;
        }

        int l = maxDepth(root.left);
        int r = maxDepth(root.right);

//        return isBalanced(root.left) && isBalanced(root.right)
//                && Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1;
        return isBalanced(root.left) && isBalanced(root.right)
              && Math.abs(l - r) <= 1;
    }

    public static int maxDepth(TreeNode root) {
        level2++;
        
        System.out.println("enter maxDepth");
        if (root != null) {
            System.out.println(root.val);
        }
        if (root == null) {
            return -1;
        }

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
