package Algorithms.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Subsets {
    public static void main(String[] strs) {
        int size = 3;
        int[] S = new int[size];
        for (int i = 0; i < size; i++) {
            S[i] = i;
        }
        
        Algorithms.permutation.Stopwatch timer1 = new Algorithms.permutation.Stopwatch();
        //System.out.println(subsets(S));
        System.out.println(subsets(S));
        System.out
                .println("Subset with memory record: "
                        + timer1.elapsedTime() + " millisec.");
        
        timer1 = new Algorithms.permutation.Stopwatch();
        //System.out.println(subsets(S));
        subsets1(S);
        System.out
                .println("Subset recursion: "
                        + timer1.elapsedTime() + " millisec.");
        
        timer1 = new Algorithms.permutation.Stopwatch();
        //System.out.println(subsets(S));
        subsets2(S);
        System.out
                .println("Subset Iterator: "
                        + timer1.elapsedTime() + " millisec.");
    }
    
    public static List<List<Integer>> subsets1(int[] S) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        
        if (S == null) {
            return ret;
        }
        
        Arrays.sort(S);
        
        subsets(S, path, ret, 0);
        
        return ret;
    }
    
    public static void subsets(int[] S, List<Integer> path, List<List<Integer>> ret, int index) {
        // 把当前的结果可以添加到结果集中. 空集也算是一种集合 
        ret.add(new ArrayList<Integer>(path));
        
        for (int i = index; i < S.length; i++) {
            path.add(S[i]);
            
            // 注意！这里的index要填写i + 1，而不是index，开始老是会犯错。
            subsets(S, path, ret, i + 1);
            path.remove(path.size() - 1);
        }
    }
    
    public static List<List<Integer>> subsets2(int[] S) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (S == null || S.length == 0) {
            return ret;
        }
        
        int len = S.length;
        Arrays.sort(S);
        
        // forget to add (long).
        long numOfSet = (long)Math.pow(2, len);
        
        for (int i = 0; i < numOfSet; i++) {
            long tmp = i;
            
            ArrayList<Integer> list = new ArrayList<Integer>();
            while (tmp != 0) {
                int indexOfLast1 = Long.numberOfTrailingZeros(tmp);
                list.add(S[indexOfLast1]);
                
                tmp ^= (1 << indexOfLast1);
            }
            
            ret.add(list);
        }
//        for (int i = 0; i < numOfSet; i++) {
//            long tmp = i;
//            
//            ArrayList<Integer> list = new ArrayList<Integer>();
//            int index = 0;
//            while (tmp != 0) {
//                if ((tmp & 1) == 1) {
//                    list.add(S[index]);
//                }
//                index++;
//                tmp = tmp >> 1;
//            }
//            
//            ret.add(list);
//        }
        
        return ret;
    }
    
    // Solution 3: The memory and recursion.
    public static List<List<Integer>> subsets3(int[] S) {
        // 2135
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (S == null) {
            return ret;
        }
        
        Arrays.sort(S);
        return dfs3(S, 0, new HashMap<Integer, List<List<Integer>>>());
    }
    
    public static List<List<Integer>> dfs3(int[] S, int index, HashMap<Integer, List<List<Integer>>> map) {
        int len = S.length;
        
        if (map.containsKey(index)) {
            return map.get(index);
        }
        
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        List<Integer> pathTmp = new ArrayList<Integer>();
        ret.add(pathTmp);
        
        for (int i = index; i < len; i++) {
            List<List<Integer>> left = dfs3(S, i + 1, map);
            for (List<Integer> list: left) {
                pathTmp = new ArrayList<Integer>();
                pathTmp.add(S[i]);
                pathTmp.addAll(list);
                ret.add(pathTmp);
            }
        }
        
        map.put(index, ret);
        return ret;
    }
    
    public static List<List<Integer>> subsets(int[] S) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (S == null) {
            return ret;
        }
        
        Arrays.sort(S);
        
        ArrayList<Integer> path = new ArrayList<Integer>();
        
        dfs2(S, 0, path, ret);    
        
        return ret;
    }
    
    public static void dfs2(int[] S, int index, List<Integer> path, List<List<Integer>> ret) {
        if (index >= S.length) { 
            ret.add(new ArrayList<Integer>(path));    
            return;
        }
        
        path.add(S[index]);
        dfs2(S, index + 1, path, ret);
        path.remove(path.size() - 1);
        
        dfs2(S, index + 1, path, ret);
    }
}

