package Algorithms.tree;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode parent;
    
    public TreeNode(int x) { val = x; left = null; right = null;}
    
    public String toString() {
        return Integer.toString(val);
    }
}
