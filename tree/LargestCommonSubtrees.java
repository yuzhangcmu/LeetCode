package Algorithms.tree;

import java.util.*;


public class LargestCommonSubtrees {
    /*
     * Function: Find all the common subtrees root that have most number
     */
    private static class TreeNode {
        int val;
        ArrayList<TreeNode> subNodes;
        public TreeNode(int val, ArrayList<TreeNode> subNodes) {
            this.val = val;
            this.subNodes = subNodes;
        }
    }
    
    public static void main(String[] args) {
        /*
         *             1
         *             
         *         /  \    \ 
         *        2    3    4
         *       / \       / \
         *      5   6     8   9
         *           \         \
         *            7         10
         * */
        TreeNode r1 = new TreeNode(1, new ArrayList<TreeNode>());
        TreeNode r2 = new TreeNode(2, new ArrayList<TreeNode>());
        TreeNode r3 = new TreeNode(3, new ArrayList<TreeNode>());
        TreeNode r4 = new TreeNode(4, new ArrayList<TreeNode>());
        TreeNode r5 = new TreeNode(5, new ArrayList<TreeNode>());
        TreeNode r6 = new TreeNode(6, new ArrayList<TreeNode>());
        TreeNode r7 = new TreeNode(7, new ArrayList<TreeNode>());
        TreeNode r8 = new TreeNode(8, new ArrayList<TreeNode>());
        TreeNode r9 = new TreeNode(9, new ArrayList<TreeNode>());
        TreeNode r10 = new TreeNode(10, new ArrayList<TreeNode>());
        
        r1.subNodes.add(r2);
        r1.subNodes.add(r3);
        r1.subNodes.add(r4);

        r2.subNodes.add(r5);
        r2.subNodes.add(r6);

        r6.subNodes.add(r7);

        r4.subNodes.add(r8);
        r4.subNodes.add(r9);

        r9.subNodes.add(r10);

        ArrayList<ArrayList<TreeNode>> ret = largestCommonSubtrees(r1);
        for (ArrayList<TreeNode> arrayl: ret) {
            for (TreeNode t: arrayl) {
                System.out.println(t.val);
            }
        }
        
    }

    public static ArrayList<ArrayList<TreeNode>> largestCommonSubtrees(TreeNode root) {
        if (root == null) {
            return null;
        }

        // store all the tree nodes to a arrayList.
        ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();
        traversalTree(root, nodes);

        int maxNum = 0;
        
        HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();

        TreeNode r1 = null;
        TreeNode r2 = null;
                
                
        // compare all the nodes.
        int size = nodes.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    continue;
                }
                int num = compareTree(nodes.get(i), nodes.get(j), hash);
                if (num > maxNum) {
                    maxNum = num;
                    r1 = nodes.get(i);
                    r2 = nodes.get(j);
                }
            }
        }

        ArrayList<ArrayList<TreeNode>> retNew = new ArrayList<ArrayList<TreeNode>>();
        retNew.add(new ArrayList<TreeNode>());
        retNew.get(0).add(r1);
        retNew.get(0).add(r2);
        return retNew;
    }


    // compare two tree, if same, return the number of leafs. if no, return -1;
    public static int compareTree(TreeNode r1, TreeNode r2, HashMap<Integer, Integer> hash) {
        if (r1 == null && r2 == null) {
            return 0;
        }

        if (r1 == null || r2 == null) {
            return -1;
        }

        // the number of subtrees should be same.
        if (r1.subNodes.size() != r2.subNodes.size()) {
            return -1;
        }
        
        int num = 1; // the root itself.
        
        for (int i = 0; i < r1.subNodes.size(); i++) {
            // get the subNode of r1.
            TreeNode subNode1 = r1.subNodes.get(i);
            TreeNode subNode2 = r2.subNodes.get(i);
            
            int HashCode = hashCode(subNode1, subNode2);
                
            Integer numNode = hash.get(HashCode);
            if (numNode == null) {
                numNode = compareTree(subNode1, subNode2, hash);
            }
            
            if (numNode == -1) {
                // not the same, should return;
                num = -1;
                break;
            } else {
                num += numNode;
                continue;
            }
        }
        
        int hashCodeRoot = hashCode(r1, r2);
        hash.put(hashCodeRoot, num);
        
        return num;
    }
    
    public static int hashCode(TreeNode r1, TreeNode r2) {
        int hash = r1.hashCode() * 31 + r2.hashCode();
        return hash;
    }

    public static void traversalTree(TreeNode root, ArrayList<TreeNode> ret) {
        if (root == null) {
            return;
        }

        ret.add(root);

        // add all the sub nodes.
        if (root.subNodes != null) {
            for(TreeNode t: root.subNodes) {
                traversalTree(t, ret);
            }       
        }        
    } 

    
}