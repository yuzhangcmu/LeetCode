package Algorithms.tree;

import java.util.ArrayList;

/*
 * 
 * Sum Root to Leaf Numbers Total Accepted: 23940 Total Submissions: 80436 My Submissions
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.
 * */

public class SumNumbers {
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        ArrayList<Integer> ret = new ArrayList<Integer>();
        
        // 存储从根节点到当前节点的路径上的数字
        ArrayList<Integer> path =  new ArrayList<Integer>();
        
        dfs(root, path, ret);
        int sum = 0;
        for (int n: ret) {
            sum += n;
        }
        
        return sum;
    }
    
    public void dfs(TreeNode root, ArrayList<Integer> path, ArrayList<Integer> ret) {
        if (root == null) {
            return;
        }

        path.add(root.val);

        if (root.left == null && root.right == null) {
            int num = 0;
            for (int n: path) {
                num = num * 10 + n;
            }
            ret.add(num);
        } else {
            // 向左右子树递归
            dfs(root.left, path, ret);
            dfs(root.right, path, ret);    
        }

        // 一定要记得回溯，也就是说递归不能修改Path本身，否则以上向左右子树分别递归时 path就会被改。 
        path.remove(path.size() - 1);
    }
}
