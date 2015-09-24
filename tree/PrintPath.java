package Algorithms.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class PrintPath {
    public static void main(String[] strs) {
        /*       1
         *   2      3 
         *  6 8        7
         *   
         * */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(8);

        root.right = new TreeNode(3);
        root.right.right = new TreeNode(7);

        getPath(root);
    }

    // 22:35
    public static void getPathIter2(TreeNode root) {
        ArrayList<ArrayList<TreeNode>> ret = new ArrayList<ArrayList<TreeNode>>();

        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);

        // Node: Parent.
        HashMap<TreeNode, TreeNode> map = new HashMap<TreeNode, TreeNode>();

        while (!s.isEmpty()) {
            TreeNode cur = s.pop();
            if (cur.left == null && cur.right == null) {
                // System.out.println(map);
                ArrayList<TreeNode> list = new ArrayList<TreeNode>();
                TreeNode node = cur;
                while (node != null) {
                    list.add(node);
                    node = map.get(node);
                }

                ret.add(list);
            }

            if (cur.right != null) {
                map.put(cur.right, cur);

                s.push(cur.right);
            }

            if (cur.left != null) {
                map.put(cur.left, cur);

                s.push(cur.left);
            }
        }

        System.out.println(ret);
    }

    // 22:35
    public static void getPathIter(TreeNode root) {
        ArrayList<ArrayList<TreeNode>> ret = new ArrayList<ArrayList<TreeNode>>();

        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);

        HashMap<TreeNode, ArrayList<TreeNode>> map = new HashMap<TreeNode, ArrayList<TreeNode>>();
        ArrayList<TreeNode> rootList = new ArrayList<TreeNode>();
        rootList.add(root);

        map.put(root, rootList);

        while (!s.isEmpty()) {
            TreeNode cur = s.pop();
            if (cur.left == null && cur.right == null) {
                // System.out.println(map);
                ret.add(map.get(cur));
            }

            if (cur.right != null) {
                ArrayList<TreeNode> list = new ArrayList<TreeNode>(map.get(cur));
                list.add(cur.right);
                map.put(cur.right, list);

                s.push(cur.right);
            }

            if (cur.left != null) {
                ArrayList<TreeNode> list = new ArrayList<TreeNode>(map.get(cur));
                list.add(cur.left);

                map.put(cur.left, list);
                s.push(cur.left);
            }
        }

        System.out.println(ret);
    }

    // 22:20
    public static void getPath(TreeNode root) {
        ArrayList<ArrayList<TreeNode>> ret = new ArrayList<ArrayList<TreeNode>>();
        dfs(root, new ArrayList<TreeNode>(), ret);
        System.out.println(ret.toString());
    }

    public static void dfs(TreeNode root, ArrayList<TreeNode> path,
            ArrayList<ArrayList<TreeNode>> ret) {
               
        if (root == null) {
            return;
        }
        
        //System.out.println(path);

        // Add the current node into the path.
        path.add(root);

        // A leaf node.
        if (root.left == null && root.right == null) {
            ret.add(new ArrayList<TreeNode>(path));
        }

        dfs(root.left, path, ret);
        dfs(root.right, path, ret);

        path.remove(path.size() - 1);
    }
}
