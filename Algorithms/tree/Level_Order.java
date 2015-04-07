package Algorithms.tree;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Level_Order {
    
    
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> levelOrder = new ArrayList<ArrayList<Integer>>();
        
        if (root == null){
            return levelOrder;
        }
        
        // record the level of the tree. 0: root.
        //int level = 0;
        
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        
        // add the root to the queue.
        queue.add(root);
        
        // init the 0 level of the order.
        //levelOrder[level] = new ArrayList<Integer>;
        
        // get all the elements out from the queue.        
        while(!queue.isEmpty()){
            
            // get the size of this level;
            int size = queue.size();
            
            ArrayList<Integer> tmpList = new ArrayList<Integer>();
            
            levelOrder.add(tmpList);
            
            // add this level to the array and store all the elements in the next level.
            for(int i = 0; i < size; i++){
                TreeNode currElement = queue.remove();
                
                // add all the element in this level to the arraylist.
                tmpList.add(currElement.val);
            
                // add all the element in the next level to the queue.
                if(currElement.left != null){
                    queue.add(currElement.left);
                }
            
                if(currElement.right != null){
                    queue.add(currElement.right);
                }    
            }
            
            //level ++;
            
        }
        
        return levelOrder;
    }
}