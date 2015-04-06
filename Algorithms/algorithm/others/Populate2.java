package Algorithms.algorithm.others;

import Algorithms.tree.TreeLinkNode;

public class Populate2 {
    public static void main(String[] args) {
        TreeLinkNode root = new TreeLinkNode(2);
        root.left = new TreeLinkNode(1);
        root.right = new TreeLinkNode(3);
        
        root.left.left = new TreeLinkNode(0);
        root.left.left.left = new TreeLinkNode(2);
        
        root.left.right = new TreeLinkNode(7);
        
        root.left.right.left = new TreeLinkNode(1);
        root.left.right.right = new TreeLinkNode(0);
        
        root.right.left = new TreeLinkNode(9);
        root.right.right = new TreeLinkNode(1);
        
        root.right.right.left = new TreeLinkNode(8);
        root.right.right.right = new TreeLinkNode(8);
        
        root.left.right.left.left = new TreeLinkNode(7);
        
        Populate2 p2 = new Populate2();
        p2.connect(root);
    }
    
    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        
        TreeLinkNode dummy0 = new TreeLinkNode(0);
        TreeLinkNode dummy = dummy0;
        
        if (root.left != null) {
            dummy = connect_help(dummy, root.left);
        }
        
        if (root.right != null) {
            dummy = connect_help(dummy, root.right);
        }
        
        if (dummy != dummy0) {
            TreeLinkNode nextRoot = root.next;
            while (nextRoot != null) {
                if (nextRoot.left != null) {
                    dummy.next = nextRoot.left;
                    break;
                } else if (nextRoot.right != null) {
                    dummy.next = nextRoot.right;
                    break;
                }
                nextRoot = nextRoot.next;
            }
            
        }
        
        connect(root.left);
        connect(root.right);
    }
    
    public TreeLinkNode connect_help(TreeLinkNode node1, TreeLinkNode node2) {
        node1.next = node2;
        node2.next = null;
        return node2;
    }
}