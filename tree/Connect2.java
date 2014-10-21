package Algorithms.tree;

import java.util.LinkedList;
import java.util.Queue;

public class Connect2 {
    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        
        TreeLinkNode dummy = new TreeLinkNode(0);
        Queue<TreeLinkNode> q = new LinkedList<TreeLinkNode>();
        
        q.offer(root);
        q.offer(dummy);
        
        while(!q.isEmpty()) {
            TreeLinkNode cur = q.poll();
            if (cur == dummy) {
                if (!q.isEmpty()) {
                    q.offer(dummy);
                }
                continue;
            }
            
            if (q.peek() == dummy) {
                cur.next = null;
            } else {
                cur.next = q.peek();    
            }
            
            if (cur.left != null) {
                q.offer(cur.left);
            }
            
            if (cur.right != null) {
                q.offer(cur.right);
            }
        }
        
    }
}
