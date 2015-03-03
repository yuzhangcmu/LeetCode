package Algorithms.algorithm.interviews.ea.epic;

import java.util.ArrayList;

public class Snack {
    public ArrayList<ArrayList<Integer>> snake(int[][] num) {
        ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
        if (num == null || num.length == 0 || num[0].length == 0) {
            return rst;
        }
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < num.length; j++) {
                helper(num,rst, list, i, j);
            }
        }
        
        int maxLength = 0;
        ArrayList<ArrayList<Integer>> output = new ArrayList<ArrayList<Integer>>();
        for (ArrayList<Integer> tmp: rst) {
            maxLength = Math.max(maxLength, tmp.size());
        }
        
        for (ArrayList<Integer> tmp: rst) {
            if (tmp.size() == maxLength) {
                output.add(new ArrayList<Integer>(tmp));
            }
        }
        
        return output;
    }
    
    private void helper(int[][] num, ArrayList<ArrayList<Integer>> rst, ArrayList<Integer> list,
            int i, int j) {
        if (i < 0 || i >= num.length || j < 0 || j > num[0].length || num[i][j] == Integer.MIN_VALUE
            || Math.abs(list.get(list.size() - 1)) - Math.abs(num[i][j]) != 1) {
            rst.add(new ArrayList<Integer>(list));
            return;
        }
        list.add(num[i][j]);
        num[i][j] = Integer.MIN_VALUE;
        helper(num, rst, list, i - 1, j);
        helper(num, rst, list, i + 1, j);
        helper(num, rst, list, i, j - 1);
        helper(num, rst, list, i, j + 1);
        //num[i][j] = list.add(list.size() - 1);
        list.remove(list.size() - 1);
        
    }
}
