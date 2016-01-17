package Algorithms;

// Anything you type or change here will be seen by the other person in real time.
import java.util.Stack;

import org.apache.commons.lang3.StringEscapeUtils;

class UberSerial {
    static String splitString = "\"";
    
    static class TreeNode {
        // Here. 
        String val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(String x) {
            //System.out.println(x);
            val = x;
            left = null;
            right = null;
        }
    }
    
    Stack<TreeNode> stack = new Stack<>();
    
    
    
    // We may use other things to sperate the strings instead of the SPACE . 
    public static void main(String[] str) {
        UberSerial codec = new UberSerial();
        
        // TEST CASES
        //        1
        //   2        3
        // 4  #     #   5
        //# #          # #
        TreeNode root = new TreeNode("1\"'");
        root.left = new TreeNode("2");
        root.right = new TreeNode("3");
        
        root.left.left = new TreeNode("4");
        
        root.right.right = new TreeNode("5");
        
        
        String encodeString = codec.serialize(root);
        System.out.printf("\n\nEncode:\n%s\n", encodeString);
        
        TreeNode decodedTree = codec.deserialize(encodeString);
        
        System.out.printf("\n\nAFTER Decode:\n");
        // Print the tree..
        codec.printTree(decodedTree);
        
        String encodeString2 = codec.serialize(decodedTree);
        
        System.out.printf("\n\nEncode 2nd:\n%s\n", encodeString2);
    }
    
    // sure!
    // print the tree
    public void printTree(TreeNode root) {
        if (root == null) {
            return;
        }
        
        System.out.println(root.val);
        
        printTree(root.left);
        printTree(root.right);
    }
    
    // Encode 
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        TreeNode cur = root;
        stack.push(cur);
        
        while (!stack.isEmpty()) {
            cur = stack.pop();
            if (cur == null) {
                sb.append("#" + splitString);
            } else {
                String escapeVal = StringEscapeUtils.escapeJava(cur.val);
                
                System.out.printf("print the str after escape: %s\n", escapeVal);
                
                sb.append(escapeVal + splitString);
                stack.push(cur.right);
                stack.push(cur.left);
            }
        }
        
        return sb.toString();
    }
    
    // Decode.
    public TreeNode deserialize(String data) {
        // remove the spaces.
        String[] arr = data.trim().split(splitString);
        
        int[] pos = new int[1];
        return helper(arr, pos);
    }
    
    private TreeNode helper(String[] arr, int[] pos) {
        String str = arr[pos[0]];
        pos[0]++;
        
        if (str.equals("#")) {
            return null;
        }
        
        // unescape the string.
        TreeNode root = new TreeNode(StringEscapeUtils.unescapeJava(str));
        root.left = helper(arr, pos);
        root.right = helper(arr, pos);
        
        return root;
    }
}
