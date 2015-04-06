package Algorithms.algorithm.others;
import java.util.ArrayList;
import java.util.Arrays;


public class Premute {
    public static void main(String[] args){
        int[] number = {2,1,1,3};
        //ArrayList<ArrayList<Integer>> result = permute(number);
        
        ArrayList<ArrayList<Integer>> result = permuteUnique(number);
        System.out.println(result.toString());
        
    }
    
    public static ArrayList<ArrayList<Integer>> permute(int[] num) {
        
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        premuteHelp(result, list, num, 0);
        
        return result;
        
    }
    
    public static ArrayList<ArrayList<Integer>> premuteHelp(ArrayList<ArrayList<Integer>> result, 
          ArrayList<Integer> list, int[] input, int pos){
        
        result.add(new ArrayList<Integer>(list));
        
        for (int i = pos; i < input.length; i ++){
            list.add(input[i]);
            premuteHelp(result, list, input, i+1);
            list.remove(list.size()-1);
        }
        
        //if (list.size() == input.length){
            
        //}
        
        return result;
    }
    
    public static ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        // record the numbers which has been accessed.
        boolean[] numFlag = new boolean[num.length];
        for(int i = 0; i < numFlag.length; i++) {
            numFlag[i] = false;
        }
        
        // sort the array.
        Arrays.sort(num);
        
        permuteUniqueHelp(result, list, num, numFlag);
        return result;
    }
    
    public static void permuteUniqueHelp(ArrayList<ArrayList<Integer>> result, 
        ArrayList<Integer> list, int[] num, boolean[] numFlag) {
        if (list.size() == num.length) {
            result.add(new ArrayList<Integer>(list));
            return;
        }
        
        /*
        for (int i = 0; i < num.length; i ++){
            while (i < num.length - 1 && num[i] == num[i + 1] ) {
                // skip the duplicate characters.
                i++;
            }
            
            if (numFlag[i] == false) {
                list.add(num[i]);
                numFlag[i] = true;
            }else{
                continue;
            }
            
            permuteUniqueHelp(result, list, num, numFlag);
            list.remove(list.size() - 1);
            numFlag[i] = false;
        }
        */
        for (int i = 0; i < num.length; i ++){
            // if numFlag[i-1] has been accessed, we should open the access of all the duplicates 
            if (i > 0 && num[i] == num[i - 1] && numFlag[i-1] == false) {
                // skip the duplicate characters.
                continue;
            }
            
            if (numFlag[i] == false) {
                list.add(num[i]);
                numFlag[i] = true;
            }else{
                continue;
            }
            
            permuteUniqueHelp(result, list, num, numFlag);
            list.remove(list.size() - 1);
            numFlag[i] = false;
        }

        
        return;
    }

}
