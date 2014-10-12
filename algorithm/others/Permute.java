package Algorithms.algorithm.others;
import java.util.ArrayList;
import java.util.Arrays;

class Permute {
    public static void main(String[] strs) {
        Permute per = new Permute();
        ArrayList<ArrayList<Integer>> rst = per.permute(new int[]{1,2,3});
        
    }
    
    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> path = new ArrayList<Integer>();
        permuteHelp(num, rst, path);
        return rst;
    }
    
    public void permuteHelp(int[] num, ArrayList<ArrayList<Integer>> rst, ArrayList<Integer> path) {
        if (path.size() == num.length) {
            rst.add(new ArrayList<Integer>(path));
            return;
        }
        
        for (int i = 0; i < num.length; i++) {
            if (path.contains(num[i])) {
                continue;
            }
            
            path.add(num[i]);
            permuteHelp(num, rst, path);
            path.remove(path.size() - 1);
        }
    }
}
