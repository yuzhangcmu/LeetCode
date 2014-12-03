package Algorithms.combination;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum2_1203 {
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (num == null || num.length == 0) {
            return ret;
        }
        
        Arrays.sort(num);
        
        dfs(num, target, new ArrayList<Integer>(), ret, 0);
        return ret;
    }
    
    public void dfs1(int[] num, int target, ArrayList<Integer> path, List<List<Integer>> ret, int index) {
        if (target == 0) {
            ret.add(new ArrayList<Integer>(path));
            return;
        }
        
        if (target < 0) {
            return;
        }
        
        // 注意，这里从 i = index开始
        // 每次只取第一个，例如 123334，到了333这里，我们第一次只取第1个3，因为我们选任何一个3是对组合来说是一个解。所以只
        // 第一次取就好了。
        int pre = -1;
        for (int i = index; i < num.length; i++) {
            int n = num[i];
            if (n == pre) {
                continue;
            }
            pre = n;
            path.add(n);
            dfs(num, target - n, path, ret, i + 1);
            path.remove(path.size() - 1);
        }
    }
    
    public void dfs(int[] num, int target, ArrayList<Integer> path, List<List<Integer>> ret, int index) {
        if (target == 0) {
            ret.add(new ArrayList<Integer>(path));
            return;
        }
        
        if (target < 0) {
            return;
        }
        
        // 注意，这里从 i = index开始
        // 每次只取第一个，例如 123334，到了333这里，我们第一次只取第1个3，因为我们选任何一个3是对组合来说是一个解。所以只
        // 第一次取就好了。
        for (int i = index; i < num.length; i++) {
            int n = num[i];
            if (i != index && n == num[i - 1]) {
                continue;
            }
            path.add(n);
            dfs(num, target - n, path, ret, i + 1);
            path.remove(path.size() - 1);
        }
    }
}