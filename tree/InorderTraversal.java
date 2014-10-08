package Algorithms.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class InorderTraversal {
    public static List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        inorderTraversal(root, ret);
        return ret;
    }

    public static void inorderTraversalRec(TreeNode root, ArrayList<Integer> ret) {
        if (root == null) {
            return;
        }

        inorderTraversalRec(root.left, ret);

        ret.add(root.val);

        inorderTraversalRec(root.right, ret);       
    }
    
    public static void inorderTraversal(TreeNode root, ArrayList<Integer> ret) {
        if (root == null) {
            return;
        }

        TreeNode cur = root;    
        Stack<TreeNode> s = new Stack<TreeNode>();

        while (true) {
            // 因为是inorder,所以要一直先处理左节点，所以我们必须找到最最左边这一个节点，
            // 否则是不处理的，也就是一直压栈。
            while (cur != null) {
                s.push(cur);
                cur = cur.left;
            }

            // 如果栈空，表明没有任何需要处理的元素了.
            if (s.isEmpty()) {
                break;
            }
            
            /*
             *        1
             *       / \
             *      2   6
             *     / \
             *    3   5
             *   / 
             *  4     
             *  
             *  例如：1, 2, 3, 4会入栈。
             *  4，3，2陆续弹出
             *  
             *  然后会转向2的右节点，5. 5处理完后，会继续弹栈，也就是1. 
             *  最后处理6.
             *  
             * */

            // 因为所有的左节点全部已经加入栈中了，开始处理栈顶的元素，
            // 或者是右子树是空的，那么弹出一个之前的节点来处理
            cur = s.pop();

            // 处理当前节点（左节点与根节点 ）
            ret.add(cur.val);

            // 处理了左节点与根节点，再处理右子树。
            cur = cur.right;
        }
    }
}
