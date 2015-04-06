package Algorithms.tree;

import java.util.ArrayList;
import java.util.List;
/*
 * Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
 * */

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; left = null; right = null; }
 * }
 */
public class GenerateTree2 {
    public List<TreeNode> generateTrees(int n) {
        return generateTreesHelp(1, n);
    }
    
    /*
      使用递归来完成，我们可以分解为2个步骤：
      完成左子树，完成右子树。

      如果说左子树有n种组合，右子树有m种组合，那最终的组合数就是n*m. 把这所有的组合组装起来即可
    */    
    public List<TreeNode> generateTreesHelp(int start, int end) {
        ArrayList<TreeNode> ret = new ArrayList<TreeNode>();

        // null也是一种解，也需要把它加上去。这样在组装左右子树的时候，不会出现左边没有解的情况，或
        // 是右边没有解的情况
        if (start > end) {
            ret.add(null);
            return ret;
        }

        for (int i = start; i <= end; i++) {
            // 求出左右子树的所有的可能。
            List<TreeNode> left = generateTreesHelp(start, i - 1);
            List<TreeNode> right =  generateTreesHelp(i + 1, end);

            // 将左右子树的所有的可能性全部组装起来
            for (TreeNode l: left) {
                for(TreeNode r: right) {
                    // 先创建根节点 
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;

                    // 将组合出来的树加到结果集合中。
                    ret.add(root);
                }
            }
        }

        return ret;
    }
}
