package Algorithms.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        
        if (num == null || num.length == 0) {
            return ret;
        }
        
        // before solve it, we just need to sort it.
        Arrays.sort(num);
        
        // boolean[] isSelect = new boolean[num.length];
        // for (int i = 0; i < num.length; i++) {
        //     isSelect[i] = false;
        // }
        
        //combinationSum2Help(num, isSelect, target, path, ret, 0);
        
        combinationSum2HelpMethod2(num, target, path, ret, 0);
        
        return ret;
    }
    
    // Solution 1;
    public void combinationSum2Help(int[] num, boolean[] isSelect, int target, 
               List<Integer> path, List<List<Integer>> ret, int index) {
        if (target < 0) {
            return;
        }
        
        if (target == 0) {
            // add a new solution.
            ret.add(new ArrayList<Integer>(path));   
        }
        
        for (int i = index; i < num.length; i++) {
            if (i > 0 && num[i] == num[i - 1] && !isSelect[i - 1]) {
                continue;
            }
            path.add(num[i]);
            isSelect[i] = true;
            combinationSum2Help(num, isSelect, target - num[i], path, ret, i + 1);
            path.remove(path.size() - 1);
            isSelect[i] = false;
        }
    }
    
    // Solution 2;
    public void combinationSum2HelpMethod2(int[] num, int target, 
               List<Integer> path, List<List<Integer>> ret, int index) {
        if (target < 0) {
            return;
        }
        
        if (target == 0) {
            // add a new solution.
            ret.add(new ArrayList<Integer>(path));   
        }
        
        int pre = -1;
        for (int i = index; i < num.length; i++) {
            // 我们每次都只取第一个，因为我们不能取重复的元素。在所有元素里任何挑一个就行了。
            if (num[i] == pre) {
                continue;
            }
            
            pre = num[i];
            
            path.add(num[i]);
            combinationSum2HelpMethod2(num, target - num[i], path, ret, i + 1);
            // 回溯
            path.remove(path.size() - 1);
        }
    }
}
