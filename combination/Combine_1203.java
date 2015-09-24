package Algorithms.combination;
import java.util.ArrayList;
import java.util.List;

public class Combine_1203 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (k == 0) {
            ret.add(new ArrayList<Integer>());
            return ret;
        }
        
        // 注意:start应该从1开始。因为我们的数字是1
        dfs(n, k, new ArrayList<Integer>(), ret, 1);
        return ret;
    }
    
    // SOLUTION 1:
    // 注意，因为求的是组合，所以我们要考虑一下顺序问题，只需要考虑升序。这样的话就不会有重复的
    // 的组合。
    public void dfs1(int n, int k, List<Integer> path, List<List<Integer>> ret, int start) {
        if (path.size() == k) {
            ret.add(new ArrayList<Integer>(path));
            return;
        }
        
        // 注意这里的条件i <= n 取n也是合法的！
        // Example: 
        for (int i = start; i <= n; i++) {
            path.add(i);
            
            // 注意，最后一个参数是i + 1，不是start + 1！！
            dfs(n, k, path, ret, i + 1);
            path.remove(path.size() - 1);
        }
    }
    
    // SOLUTION 2:    
    public void dfs(int n, int k, List<Integer> path, List<List<Integer>> ret, int start) {
        if (0 == k) {
            ret.add(new ArrayList<Integer>(path));
            return;
        }
        
        // 注意这里的条件i <= n 取n也是合法的！
        // Example: 
        for (int i = start; i <= (n - k + 1); i++) {
            path.add(i);
            
            // 注意，最后一个参数是i + 1，不是start + 1！！
            dfs(n, k - 1, path, ret, i + 1);
            path.remove(path.size() - 1);
        }
    }
}