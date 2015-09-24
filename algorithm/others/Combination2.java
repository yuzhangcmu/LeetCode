package Algorithms.algorithm.others;

import java.util.ArrayList;
import java.util.Arrays;

public class Combination2 {
    public static void main(String[] args) {
        ArrayList<Character> test = new ArrayList<Character>();
        
        test.add('c');
        test.add('b');
        
        System.out.printf("Result: %s", test.toString());
        
        Combination2 cb = new Combination2();
        int[] num = {2,2,2};
        cb.combinationSum2(num, 4);
    }
    
    public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
        
        // first we should sort the array.
        Arrays.sort(num);
        
        ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> path = new ArrayList<Integer>();
        
        cmbHelp(num, 0, path, rst, target);
        
        return rst;
    }
    
    public void cmbHelp(int[] num, int index, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> rst, int target) {
        if (target == 0) {
            // add the current set into the result.
            rst.add(new ArrayList<Integer>(path));
        }
        
        for (int i = index; i < num.length; i++) {
            if (num[i] > target) {
                // don't need to add new element;
                return;
            }
            
            if (i >= 1 && 
                num[i] == num[i - 1] && 
                (path.size() == 0 || path.get(path.size() - 1) != num[i])) {
                continue;     
            }
                
            path.add(num[i]);
            cmbHelp(num, i + 1, path, rst, target - num[i]);
            path.remove(path.size() - 1);
        }
        
        return;
    }

}
