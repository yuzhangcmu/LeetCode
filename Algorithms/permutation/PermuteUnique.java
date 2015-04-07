package Algorithms.permutation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermuteUnique {
    public static void main(String[] strs) {
        int[] num = {1, 1, 1, 3};
        List<List<Integer>> ret = permuteUnique(num);
        System.out.println(ret.toString());
    }
    
    public static List<List<Integer>> permuteUnique(int[] num) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (num == null || num.length == 0) {
            return ret;
        }
        
        // For deal with the duplicate solution, we should sort it.
        Arrays.sort(num);
        boolean[] visit = new boolean[num.length];
        
        dfs1(num, new ArrayList<Integer>(), ret, visit);
        
        return ret;
    }
    
    public static void dfs1(int[] num, ArrayList<Integer> path, List<List<Integer>> ret, boolean[] visit) {
        int len = num.length;
        if (path.size() == len) {
            ret.add(new ArrayList<Integer>(path));
            return;
        }
        
        for (int i = 0; i < len; i++) {
            // 只能连续地选，这样就可以避免生成重复的solution.
            // 例子：1 2 3 4 4 4 5 6 7 8
            // 444这个的选法只能:4, 44, 444连续这三种选法
            if (visit[i] || (i != 0 && !visit[i - 1] && num[i] == num[i - 1])) {
                continue;
            }
            
            // 递归以及回溯
            visit[i] = true;
            path.add(num[i]);
            dfs1(num, path, ret, visit);
            path.remove(path.size() - 1);
            visit[i] = false;
        }
    }
    
    // SOLUTION 2:
    //  使用一个pre来记录。只取第一个可以取的位置
    public void dfs(int[] num, ArrayList<Integer> path, List<List<Integer>> ret, boolean[] visit) {
        int len = num.length;
        if (path.size() == len) {
            ret.add(new ArrayList<Integer>(path));
            return;
        }

        long pre = Long.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            int n = num[i];
            // 只取第一个可取的位置，因为别的位置取到的也没有区别
            if (visit[i] || pre == n) {
                continue;
            }
            pre = n;
            
            // 递归以及回溯
            visit[i] = true;
            path.add(n);
            dfs(num, path, ret, visit);
            path.remove(path.size() - 1);
            visit[i] = false;
        }
    }
}