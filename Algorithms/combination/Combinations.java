package Algorithms.combination;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        
        if (n == 0 || k == 0) {
            return ret;
        }
        
        List<Integer> path = new ArrayList<Integer>();
        
        combine(n, k, path, ret, 1);
        
        return ret;
    }
    
    // index means the position which I can choose from.
    // for example: when n = 4, 
    // 1, 2, 3, 4, and index = 1, means now I can choose a number from 1 - 4
    // 
    public void combine(int n, int k, List<Integer> path, List<List<Integer>> ret, int index) {
        if (0 == k) {
            ret.add(new ArrayList<Integer>(path));
            return;
        }
        
        // 注意这里的终止条件.
        // For example: N = 4的时候，K = 2的时候,
        // 这里还有2个可以取值，那么 1, 2, 3, 4中index最多可以从3取值，也就是4-2+1.
        // 就是 n - k + 1
        for (int i = index; i <= n - k + 1; i++) {
            path.add(i);
            combine(n, k - 1, path, ret, i + 1);
            path.remove(path.size() - 1);
        }
    }
}