package Algorithms.permutation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermuteUnique {
    public List<List<Integer>> permuteUnique(int[] num) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (num == null || num.length == 0) {
            return ret;
        }
        
        // For deal with the duplicate solution, we should sort it.
        Arrays.sort(num);
        boolean[] visit = new boolean[num.length];
        
        dfs(num, new ArrayList<Integer>(), ret, visit);
        
        return ret;
    }
    
    public void dfs(int[] num, ArrayList<Integer> path, List<List<Integer>> ret, boolean[] visit) {
        int len = num.length;
        if (path.size() == len) {
            ret.add(new ArrayList<Integer>(path));
            return;
        }
        
        for (int i = 0; i < len; i++) {
            // 只能连续地选，这样就可以避免生成重复的solution.
            // 例子：1 2 3 4 4 4 5 6 7 8
            // 444这个的选法只能:4, 44, 444连续这三种选法
            if (visit[i] || (i != 0 && visit[i - 1] && num[i] == num[i - 1])) {
                continue;
            }
            
            // 递归以及回溯
            visit[i] = true;
            path.add(num[i]);
            dfs(num, path, ret, visit);
            path.remove(path.size() - 1);
            visit[i] = false;
        }
    }
}