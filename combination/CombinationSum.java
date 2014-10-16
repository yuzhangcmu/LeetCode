package Algorithms.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (candidates == null || candidates.length == 0) {
            return ret;
        }
        
        List<Integer> path = new ArrayList<Integer>();
        
        // we should sort the candidates than do it. in this case we can get a non-descending order.
        Arrays.sort(candidates);
        
        combinationSum(candidates, target, path, ret, 0);
        return ret;
    }
    
    public void combinationSum(int[] candidates, int target, List<Integer> path, List<List<Integer>> ret, int index) {
        if (target == 0) {
            // add the current set into the result.
            ret.add(new ArrayList<Integer>(path));
            return;
        }
        
        if (target < 0) {
            return;
        }
        
        int len = candidates.length;
        for (int i = index; i < len; i++) {
            int num = candidates[i];
            path.add(num);
            combinationSum(candidates, target - num, path, ret, i);
            path.remove(path.size() - 1);
        }
    }
}
