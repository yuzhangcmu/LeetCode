package Algorithms.algorithm.interviews.ea.epic;

import Algorithms.tree.TreeNode;

public class FindVisiableTree {
    public static void main(String[] strs) {
        //int[] A = {1, 0, 0, 1, 0, 0, 1, 0};
        //int[] A = {1, 0, 0, 1, 0, 0, 1, 0};
        int[] A = {1, 1, 1, 1};
        //int[] A = {0,0,1,0,0};
        
        int[] A1 = {};
        System.out.println("num:");
//        System.out.println(find(A));
    }
    
    public int findNum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        return findNum(root, Integer.MIN_VALUE);
    }

    public int findNum(TreeNode root, int max) {
        if (root == null) {
            return 0;
        }
        
        int ret = 0;
        if (root.val >= max) {
            ret++;
        }
        
        max = Math.max(root.val, max);
        ret += findNum(root.left, max);
        ret += findNum(root.right, max);
        
        return ret;
    }
    
    
    public static int solution1(int[] arr) {
        int n = arr.length;
        int result = 0;
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] == arr[i + 1]) {
                result = result + 1;
            }
            //System.out.println(result);
        }
        int r = 0;
        for (int i = 0; i < n; i++) {
            int count = 0;
            if (i > 0) {
                if (arr[i - 1] != arr[i])
                    count = count + 1;
                else
                    count = count - 1;
            }
            if (i < n - 1) {
                if (arr[i + 1] != arr[i])
                    count = count + 1;
                else
                    count = count - 1;
            }
            r = Math.max(r, count);
        }
        return result + r;
    }
    
    public static int solution(int[] arr) {
        int n = arr.length;
        int result = 0;
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] == arr[i + 1])
                result = result + 1;
            //System.out.println(result);
        }
        
        int r = Integer.MIN_VALUE;
        
        for (int i = 0; i < n; i++) {
            int count = 0;
            if (i > 0) {
                if (arr[i - 1] != arr[i])
                    count = count + 1;
                else
                    count = count - 1;
            }
            if (i < n - 1) {
                if (arr[i + 1] != arr[i])
                    count = count + 1;
                else
                    count = count - 1;
            }
            r = Math.max(r, count);
        }
        return result + r;
    }
}
