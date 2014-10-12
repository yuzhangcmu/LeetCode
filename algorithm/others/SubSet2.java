package Algorithms.algorithm.others;
import java.util.ArrayList;
import java.util.Arrays;

public class SubSet2 {
    public static void main(String[] args){
        int[] a = {1,2,3};
        ArrayList<ArrayList<Integer>> test = subsets(a);
    }
    
    public static ArrayList<ArrayList<Integer>> subsets(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(num == null || num.length == 0) {
            return result;
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        Arrays.sort(num);  
        subsetsHelper(result, list, num, 0);

        return result;
    }


    private static void subsetsHelper(ArrayList<ArrayList<Integer>> result,
        ArrayList<Integer> list, int[] num, int pos) {

        result.add(new ArrayList<Integer>(list));
        
        System.out.printf("generate: %s pos:%d\n", list.toString(), pos);

        for (int i = pos; i < num.length; i++) {

            list.add(num[i]);
            System.out.printf("ADD:%d\n", num[i]);
            
            subsetsHelper(result, list, num, i + 1);
            
            System.out.printf("REMOVE:%d i=%d\n", list.get(list.size() - 1),i);
            list.remove(list.size() - 1);
        }
    }
}