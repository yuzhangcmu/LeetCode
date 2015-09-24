package Algorithms.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsWithDup {
    public List<List<Integer>> subsetsWithDup(int[] num) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        
        if (num == null) {
            return ret;
        }
        
        Arrays.sort(num);
        
        dfs(num, new ArrayList<Integer>(), ret, 0);
        
        return ret;
    }
    
    public void dfs(int[] num, List<Integer> path, List<List<Integer>> ret, int index) {
        ret.add(new ArrayList<Integer>(path));
        
        for (int i = index; i < num.length; i++) {
            // skip the duplicate.
            if (i > index && num[i] == num[i - 1]) {
                continue;
            }
            
            path.add(num[i]);
            // 注意：这里是i + 1不是index + 1!!!
            dfs(num, path, ret, i + 1);
            path.remove(path.size() - 1);
        }
        
    }
}
