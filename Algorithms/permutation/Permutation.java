package Algorithms.permutation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class Permutation {
    public static void main(String[] strs) {
        int[] num = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.printf("Test size:%d \n", num.length);

        Stopwatch timer1 = new Stopwatch();
        
        permute(num);
        System.out
                .println("Computing time with HASHMAP: "
                        + timer1.elapsedTime() + " millisec.");
        
        System.out.printf("Test size:%d \n", num.length);

        Stopwatch timer2 = new Stopwatch();
        
        permute2(num);
        System.out
                .println("Computing time with list: "
                        + timer2.elapsedTime() + " millisec.");
    }
    
    public static ArrayList<ArrayList<Integer>> permute(int[] num) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        if (num == null) {
            return ret;
        }
        
        permuteHelp(num, ret, new LinkedHashMap<Integer, Integer>());
        return ret;
    }
    
    public static void permuteHelp(int[] num, ArrayList<ArrayList<Integer>> ret, LinkedHashMap<Integer, Integer> set) {
        if (set.size() == num.length) {
            
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (Integer i: set.keySet()){
                list.add(i);
            }
            ret.add(list);
            return;
        }
        
        int len = num.length;
        for (int i = 0; i < len; i++) {
            if (set.containsKey(num[i])) {
                continue;
            }
            
            //path.add(num[i]);
            set.put(num[i], 0);
            permuteHelp(num, ret, set);
            //path.remove(path.size() - 1);
            set.remove(num[i]);
        }
    }
    
    public static ArrayList<ArrayList<Integer>> permute2(int[] num) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        if (num == null) {
            return ret;
        }
        
        ArrayList<Integer> path = new ArrayList<Integer>();
        permuteHelp2(num, path, ret);
        return ret;
    }
    
    public static void permuteHelp2(int[] num, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> ret) {
        if (path.size() == num.length) {
            ret.add(new ArrayList<Integer>(path));
            return;
        }
        
        int len = num.length;
        for (int i = 0; i < len; i++) {
            if (path.contains(num[i])) {
                continue;
            }
            
            path.add(num[i]);
            permuteHelp2(num, path, ret);
            path.remove(path.size() - 1);
        }
    }
}
