package Algorithms.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class PathSum2 {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        
        ArrayList<Integer> path = new ArrayList<Integer>();
        
        pathSumHelp(root, sum, path, ret);
        
        return ret;
    }
    
    public void pathSumHelp(TreeNode root, int sum, ArrayList<Integer> path, List<List<Integer>> ret) {
        if (root == null) {
            return;
        }
        
        path.add(root.val);
        
        if (root.left == null 
            && root.right == null
            && root.val == sum) {
            ret.add(new ArrayList<Integer>(path));
        } else {
            // 继续递归
            pathSumHelp(root.left, sum - root.val, path, ret);
            pathSumHelp(root.right, sum - root.val, path, ret);    
        }
        
        // 注意，递归和回溯的特点就是 递归不可以改变path的值。也就是说，你返回时，这个path不能被改变
        // 所以在这里要执行remove操作。
        path.remove(path.size() - 1);
    }
}