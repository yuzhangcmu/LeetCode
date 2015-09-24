package Algorithms.algorithm.others;
import java.util.ArrayList;
import java.util.Arrays;


public class Subset {
    public static void main(String args[]){
        //int[] a = {1, 2, 3};
        int[] a = {0};
        
        //a[0] = 1;
        //a[1] = 2;
        
        ArrayList<ArrayList<Integer>> result= Subset.subsets(a);
        
        for(int i = 0; i < result.size(); i ++){
            for(int j = 0; j < result.get(i).size(); j ++)
            {
                System.out.printf("i:%d, j:%d, data: %d", i, j, result.get(i).get(j));
            }
        }
        return;
    }
    
    public static ArrayList<ArrayList<Integer>> subsets(int[] S) {
        Arrays.sort(S);
        return subsets_help(S, S.length);
    }
    
    // length: the length of the S array.
    private static ArrayList<ArrayList<Integer>> subsets_help(int[] S, int length) {
        
        ArrayList<ArrayList<Integer>> subset_minus1 = 
               new ArrayList<ArrayList<Integer>>();
               
        if (length == 0){
            subset_minus1.add(new ArrayList<Integer>());
            return  subset_minus1;
        }
        
        subset_minus1 = subsets_help(S, length - 1);
        
        System.out.printf("length is :%d subset_minus1.size()%d\n", length, subset_minus1.size());
        
        int max_lastlevel = subset_minus1.size();
        
        for(int i = 0; i < max_lastlevel; i ++){
            // copy a set to a new set.
            ArrayList<Integer> listTmp = (ArrayList<Integer>)subset_minus1.get(i).clone();
            
            // add the new element
            listTmp.add(S[length - 1]);
            
            //System.out.printf("S[length - 1] is :%d\n", S[length - 1]);
            
            subset_minus1.add(listTmp);
            
            //System.out.printf("listTmp is :%s\n", listTmp);
        }
        
        
        
        return subset_minus1;
        
    }
}
