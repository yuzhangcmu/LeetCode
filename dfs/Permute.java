package Algorithms.dfs;

import java.util.ArrayList;
import java.util.List;

public class Permute {
    public List<List<Integer>> permute(int[] num) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (num == null || num.length == 0) {
            return ret;
        }

        dfs(num, new ArrayList<Integer>(), ret);
        return ret;
    }

    public void dfs(int[] num, List<Integer> path, List<List<Integer>> ret) {
        int len = num.length;
        if (path.size() == len) {
            ret.add(new ArrayList<Integer>(path));
            return;
        }

        for (int i = 0; i < len; i++) {
            if (path.contains(num[i])) {
                continue;
            }

            path.add(num[i]);
            dfs(num, path, ret);
            path.remove(path.size() - 1);
        }
    }
}