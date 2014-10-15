package Algorithms.dfs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {
    public List<List<Integer>> subsets(int[] S) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        
        if (S == null) {
            return ret;
        }
        
        Arrays.sort(S);
        
        subsets(S, path, ret, 0);
        
        return ret;
    }
    
    public void subsets(int[] S, List<Integer> path, List<List<Integer>> ret, int index) {
        // 把当前的结果可以添加到结果集中. 空集也算是一种集合 
        ret.add(new ArrayList<Integer>(path));
        
        for (int i = index; i < S.length; i++) {
            path.add(S[i]);
            
            // 注意！这里的index要填写i + 1，而不是index，开始老是会犯错。
            subsets(S, path, ret, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
