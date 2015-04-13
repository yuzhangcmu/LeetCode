package Algorithms.algorithm.interviews.ea.epic;

import java.util.ArrayList;

public class PhoneCombine {
    public static void main(String[] strs) {
        getPhone();
    }
    
    public static void getPhone() {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        dfs(new ArrayList<Integer>(), ret, 0);
        //System.out.println(ret);
    }
    
    public static void dfs(ArrayList<Integer> path, ArrayList<ArrayList<Integer>> ret, int index) {
        //System.out.println(path);
        if (index >= 9) {
            ret.add(new ArrayList<Integer>(path));
            return;
        }
        
        for (int i = 0; i <= 9; i++) {
            if (index != 0 && (path.get(index - 1) == i || i == 4)) {
                continue;
            }
            
            path.add(i);
            dfs(path, ret, index + 1);
            path.remove(path.size() - 1);
        }
    }
}
