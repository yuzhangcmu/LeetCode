package Algorithms;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class TreeDemo {
    /* 
            1  
           / \  
          2   3  
         / \   \  
        4   5   6      
    */  
    public static void main(String[] args) {
//        TreeNode r1 = new TreeNode(1);
//        TreeNode r2 = new TreeNode(2);
//        TreeNode r3 = new TreeNode(3);
//        TreeNode r4 = new TreeNode(4);
//        TreeNode r5 = new TreeNode(5);
//        TreeNode r6 = new TreeNode(6);
        
/* 
       10  
       / \  
      6   14  
     / \   \  
    4   8   16      
*/ 
        TreeNode r1 = new TreeNode(10);
        TreeNode r2 = new TreeNode(6);
        TreeNode r3 = new TreeNode(14);
        TreeNode r4 = new TreeNode(4);
        TreeNode r5 = new TreeNode(8);
        TreeNode r6 = new TreeNode(16);
        
        TreeNode r7 = new TreeNode(0);
        
        r1.left = r2;
        r1.right = r3;
        r2.left = r4;
        r2.right = r5;
        r3.right = r6;
        
        r4.left = r7;
        
//      System.out.println(getNodeNumRec(null));
//      System.out.println(getNodeNum(r1));
//        System.out.println(getDepthRec(r1));
//        System.out.println(getDepth(r1));
//        
//        preorderTraversalRec(r1);
//        System.out.println();
//        preorderTraversal(r1);
//        System.out.println();
//        inorderTraversalRec(r1);
//        
//        System.out.println();
          inorderTraversal(r1);
//        postorderTraversalRec(r1);
//        System.out.println();
//        postorderTraversal(r1);
//        System.out.println();
//        levelTraversal(r1);
//        
//        System.out.println();
//        levelTraversalRec(r1);
        
//        TreeNode ret = convertBST2DLLRec(r1);
//        while (ret != null) {
//            System.out.print(ret.val + " ");
//            ret = ret.right;
//        }
        
//        TreeNode ret2 = convertBST2DLL(r1);
//        while (ret2.right != null) {
//            ret2 = ret2.right;
//        }
//        
//        while (ret2 != null) {
//            System.out.print(ret2.val + " ");
//            ret2 = ret2.left;
//        }
//        
//        TreeNode ret = convertBST2DLL(r1);
//        while (ret != null) {
//            System.out.print(ret.val + " ");
//            ret = ret.right;
//        }
        
        System.out.println();
        System.out.println(findLongest(r1));
        System.out.println();
        System.out.println(findLongest2(r1));
        
    }
    
    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val = val;
            left = null;
            right = null;                    
        }
    }
    
    /*
     * null返回0，然后把左右子树的size加上即可。
     * */
    public static int getNodeNumRec(TreeNode root) {
        if (root == null) {
            return 0;
        }
                
        return getNodeNumRec(root.left) + getNodeNumRec(root.right) + 1;
    }

    /** 
     *  求二叉树中的节点个数迭代解法O(n)：基本思想同LevelOrderTraversal， 
     *  即用一个Queue，在Java里面可以用LinkedList来模拟  
     */  
    public static int getNodeNum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        Queue<TreeNode> q = new LinkedList<TreeNode>(); 
        q.offer(root);
        
        int cnt = 0;
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node.left != null) {
                q.offer(node.left);
            }
            
            if (node.right != null) {
                q.offer(node.right);
            }
            
            cnt++;
        }
        
        return cnt;
    }
    
    public static int getDepthRec(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        return Math.max(getDepthRec(root.left), getDepthRec(root.right)) + 1;
    }
    
    /*
     * 可以用 level LevelOrderTraversal 来实现，我们用一个dummyNode来分隔不同的层，这样即可计算出实际的depth.
     *      1  
           / \  
          2   3  
         / \   \  
        4   5   6
     * 
     * 在队列中如此排列： 1, dummy, 2, 3, dummy, 4, 5, 5, dummy  
     * 
    */  
    public static int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        TreeNode dummy = new TreeNode(0);
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        q.offer(dummy);
        
        int depth = 0;
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr == dummy) {
                depth++;
                if (!q.isEmpty()) {  // 使用DummyNode来区分不同的层， 如果下一层不是为空，则应该在尾部加DummyNode.
                    q.offer(dummy);
                }
            }
            
            if (curr.left != null) {
                q.offer(curr.left);
            }
            if (curr.right != null) {
                q.offer(curr.right);
            }
        }
        
        return depth;
    }
    
    /*
     * 3. 前序遍历，中序遍历，后序遍历: preorderTraversalRec, preorderTraversal, inorderTraversalRec, postorderTraversalRec 
     * (https://en.wikipedia.org/wiki/Tree_traversal#Pre-order_2)
     * */
    public static void preorderTraversalRec(TreeNode root) {
        if (root == null) {
            return;
        }
        
        System.out.print(root.val + " ");
        preorderTraversalRec(root.left);
        preorderTraversalRec(root.right);
    }
    
    /*
     * 前序遍历，Iteration 算法. 把根节点存在stack中。
     * */
    public static void preorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        
        while (!s.isEmpty()) {
            TreeNode node = s.pop();
            System.out.print(node.val + " ");
            if (node.right != null) { //
                s.push(node.right);
            }
            
            // 我们需要先压入右节点，再压入左节点，这样就可以先弹出左节点。 
            if (node.left != null) {
                s.push(node.left);
            }                       
        }
    }
    
    /*
     * 中序遍历
     * */
    public static void inorderTraversalRec(TreeNode root) {
        if (root == null) {
            return;
        }
        
        inorderTraversalRec(root.left);
        System.out.print(root.val + " ");
        inorderTraversalRec(root.right);
    }
    
    /** 
     * 中序遍历迭代解法 ，用栈先把根节点的所有左孩子都添加到栈内， 
     * 然后输出栈顶元素，再处理栈顶元素的右子树 
     * http://www.youtube.com/watch?v=50v1sJkjxoc 
     *  
     * 还有一种方法能不用递归和栈，基于线索二叉树的方法，较麻烦以后补上 
     * http://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion-and-without-stack/ 
     */  
    public static void inorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        
        Stack<TreeNode> s = new Stack<TreeNode>();
        
        TreeNode cur = root;
        
        while(true) {
            // 把当前节点的左节点都push到栈中.
            while (cur != null) {
                s.push(cur);
                cur = cur.left;
            }
            
            if (s.isEmpty()) {
                break;
            }
            
            // 因为此时已经没有左孩子了，所以输出栈顶元素 
            cur = s.pop();
            System.out.print(cur.val + " ");
            
            // 准备处理右子树  
            cur = cur.right;            
        }
    }
    
    // 后序遍历
    /*
     *      1  
           / \  
          2   3  
         / \   \  
        4   5   6
        
        if put into the stack directly, then it should be:
        1, 2, 4, 5, 3, 6 in the stack.
        when pop, it should be: 6, 3, 5, 4, 2, 1
        
        if I 
     * */
    
    public static void postorderTraversalRec(TreeNode root) {
        if (root == null) {
            return;
        }
        
        postorderTraversalRec(root.left);
        postorderTraversalRec(root.right);
        System.out.print(root.val + " ");
    }
    
    /** 
     *  后序遍历迭代解法 
     *  http://www.youtube.com/watch?v=hv-mJUs5mvU 
     *  http://blog.csdn.net/tang_jin2015/article/details/8545457
     *  从左到右的后序 与从右到左的前序的逆序是一样的，所以就简单喽！ 哈哈
     *  用另外一个栈进行翻转即可喽 
     */ 
    public static void postorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        
        Stack<TreeNode> s = new Stack<TreeNode>();
        Stack<TreeNode> out = new Stack<TreeNode>();
        
        s.push(root);
        while(!s.isEmpty()) {
            TreeNode cur = s.pop();
            out.push(cur);
            
            if (cur.left != null) {
                s.push(cur.left);
            }
            if (cur.right != null) {
                s.push(cur.right);
            }
        }
        
        while(!out.isEmpty()) {
            System.out.print(out.pop().val + " ");
        }
    }
    
    /*
     * 分层遍历二叉树（按层次从上往下，从左往右）迭代 
     * 其实就是广度优先搜索，使用队列实现。队列初始化，将根节点压入队列。当队列不为空，进行如下操作：弹出一个节点 
     * ，访问，若左子节点或右子节点不为空，将其压入队列 
     * */
    public static void levelTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            
            System.out.print(cur.val + " ");
            if (cur.left != null) {
                q.offer(cur.left);
            }
            if (cur.right != null) {
                q.offer(cur.right);
            }
        }
    }
    
    public static void levelTraversalRec(TreeNode root) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        levelTraversalVisit(root, 0, ret);
        System.out.println(ret);
    }
    
    /** 
     *  分层遍历二叉树（递归） 
     *  很少有人会用递归去做level traversal 
     *  基本思想是用一个大的ArrayList，里面包含了每一层的ArrayList。 
     *  大的ArrayList的size和level有关系 
     *   
     *  http://discuss.leetcode.com/questions/49/binary-tree-level-order-traversal#answer-container-2543 
     */  
    public static void levelTraversalVisit(TreeNode root, int level, ArrayList<ArrayList<Integer>> ret) {
        if (root == null) {
            return;
        }
        
        // 如果ArrayList的层数不够用， 则新添加一层
        // when size = 3, level: 0, 1, 2
        if (level >= ret.size()) {
            ret.add(new ArrayList<Integer>());
        }
        
        // visit 当前节点
        ret.get(level).add(root.val);
        
        // 将左子树， 右子树添加到对应的层。
        levelTraversalVisit(root.left, level + 1, ret);
        levelTraversalVisit(root.right, level + 1, ret);
    }
    
    /*
     * 题目要求：将二叉查找树转换成排序的双向链表，不能创建新节点，只调整指针。
       查找树的结点定义如下：
       既然是树，其定义本身就是递归的，自然用递归算法处理就很容易。将根结点的左子树和右子树转换为有序的双向链表，
       然后根节点的left指针指向左子树结果的最后一个结点，同时左子树最后一个结点的right指针指向根节点；
       根节点的right指针指向右子树结果的第一个结点，
       同时右子树第一个结点的left指针指向根节点。
     * */
    public static TreeNode convertBST2DLLRec(TreeNode root) {
        return convertBST2DLLRecHelp(root)[0];
    }
    
    /*
     * ret[0] 代表左指针
     * ret[1] 代表右指针
     * */
    public static TreeNode[] convertBST2DLLRecHelp(TreeNode root) {
        TreeNode[] ret = new TreeNode[2];
        ret[0] = null;
        ret[1] = null;
                
        if (root == null) {
            return ret;
        }
        
        if (root.left != null) {
            TreeNode left[] = convertBST2DLLRecHelp(root.left);
            left[1].right = root;  // 将左子树的尾节点连接到根
            root.left = left[1];
            
            ret[0] = left[0];
        } else {
            ret[0] = root;   // 左节点返回root.
        }
        
        if (root.right != null) {
            TreeNode right[] = convertBST2DLLRecHelp(root.right);
            right[0].left = root;  // 将右子树的头节点连接到根
            root.right = right[0];
            
            ret[1] = right[1];
        } else {
            ret[1] = root;  // 右节点返回root.
        }
        
        return ret;
    }
    
    /** 
     * 将二叉查找树变为有序的双向链表 迭代解法 
     * 类似inOrder traversal的做法 
     */  
    public static TreeNode convertBST2DLL(TreeNode root) {
        while (root == null) {
            return null;
        }
        
        TreeNode pre = null;
        Stack<TreeNode> s = new Stack<TreeNode>();
        TreeNode cur = root;
        TreeNode head = null;       // 链表头
        
        while (true) {
            while (cur != null) {
                s.push(cur);
                cur = cur.left;
            }
            
            // if stack is empty, just break;
            if (s.isEmpty()) {
                break;
            }
            
            cur = s.pop(); 
            if (head == null) {
                head = cur;
            }

            // link pre and cur.
            cur.left = pre;
            if (pre != null) {
                pre.right = cur;
            }
            
            // 左节点已经处理完了，处理右节点
            cur = cur.right;
            pre = cur;
        }
        
        return root;
    }
    
    /*
     * 第一种解法：
     * 返回左边最长，右边最长，及左子树最长，右子树最长。
     * */
    public static int findLongest(TreeNode root) {
        if (root == null) {
            return -1;
        }
        
        TreeNode l = root;
        int cntL = 0;
        while (l.left != null) {
            cntL++;
            l = l.left;
        }
        
        TreeNode r = root;
        int cntR = 0;
        while (r.right != null) {
            cntR++;
            r = r.right;
        }
        
        int lmax = findLongest(root.left);
        int rmax = findLongest(root.right);
        
        int max = Math.max(lmax, rmax);
        max = Math.max(max, cntR);
        max = Math.max(max, cntL);
        
        return max;
    }
    
    /*      1
     *    2   3
     *  3       4
     *         6  1
     *        7
     *       9
     *     11
     *    2
     *  14      
     * */
    public static int findLongest2(TreeNode root) {
        int [] maxVal = new int[1];
        maxVal[0] = -1;
        findLongest2Help(root, maxVal);
        return maxVal[0];
    }
    
    // ret:
    // 0: the left side longest,
    // 1: the right side longest.
    static int maxLen = -1;
    static int[] findLongest2Help(TreeNode root, int[] maxVal) {
        int[] ret = new int[2];
        if (root == null) {
            ret[0] = -1;
            ret[1] = -1;
            return ret;
        }
        
        ret[0] = findLongest2Help(root.left, maxVal)[0] + 1;
        ret[1] = findLongest2Help(root.right, maxVal)[1] + 1;
        //maxLen = Math.max(maxLen, ret[0]);
        //maxLen = Math.max(maxLen, ret[1]);
        maxVal[0] = Math.max(maxVal[0], ret[0]);
        maxVal[0] = Math.max(maxVal[0], ret[1]);

        return ret;
    }
}