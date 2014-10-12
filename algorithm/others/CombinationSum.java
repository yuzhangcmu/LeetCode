package Algorithms.algorithm.others;

import java.util.ArrayList;
import java.util.Arrays;

public class CombinationSum{
    public ArrayList<ArrayList<Integer>> combine(int n, int k) {
        ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
        
        help(n, 0, new ArrayList<Integer>(), rst, k);
        
        return rst;
    }
    
    public void help(int n, int index, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> rst, int k) {
        if (path.size() == k) {
            rst.add(new ArrayList<Integer>(path));
            return;
        }
        
        for (int i = index; i < n; i++) {
            path.add(i + 1);
            help(n, i + 1, path, rst, k);
            path.remove(path.size() - 1);
        }
        
        return;
    }
    
    
    public static void main(String[] args) {
        CombinationSum cs = new CombinationSum();
        
        int[] num = {1,2};
        cs.combine(1, 1);
        
        
        
        
        
    }
    
    public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
        ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>> ();
        Arrays.sort(candidates);

        help(candidates, 0, new ArrayList<Integer>(), rst, target);
        
        System.out.printf(rst.toString());
        
        return rst;
    }
    
    public void help(int[] cand, int index, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> rst, int target) {
        if (target == 0) {
            // add the current set into the result.
            rst.add(new ArrayList<Integer>(path));
            return;
        }
        
        int pre = -1;
        for (int i = index; i < cand.length; i++) {
            if (cand[i] > target) {
                // because the sequence is ascending, so we don't need to go on.
                break;
            }
            
            if (cand[i] == pre)
            
            path.add(cand[i]);
            help(cand, index, path, rst, target - cand[i]);
            path.remove(path.size() - 1);
        }
        
        return;
    }

}
