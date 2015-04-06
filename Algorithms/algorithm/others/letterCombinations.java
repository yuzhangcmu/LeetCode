package Algorithms.algorithm.others;
import java.util.ArrayList;


public class letterCombinations {
    public static void main(String[] args){
        JavaAppWithoutMain test = new JavaAppWithoutMain();
        
        String digits = "234";
        ArrayList<String> result = letterCombinations(digits);
        
        for(int i = 0; i < result.size(); i ++){
            System.out.printf("%d: %s\n",i, result.get(i));
        }
        
    }
    
    public static ArrayList<String> letterCombinations(String digits) {
        String[] charas = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        
        ArrayList<String> result = new ArrayList<String>();
        
        StringBuilder exitStr = new StringBuilder();
        
        int pos = 0;
        
        String[] inputStr = new String[digits.length()];
        
        for (int i = 0; i < digits.length(); i ++){
            int index = (int)digits.charAt(i) - 46 - 4;
            System.out.printf("index %d\n",index);
            inputStr[i] = charas[index];
        }
        
        
        
        letterCombinationHelp(result, exitStr, inputStr, pos);
        
        return result;
    }
    
    public static void letterCombinationHelp(ArrayList<String> result,
            StringBuilder exitStr,String[] inputStr, int pos){
        StringBuilder strBuilder = new StringBuilder();
        
        if (inputStr.length == 0){
            return;
        }
        
        if (exitStr.length() == inputStr.length){
            result.add(new String(exitStr));
            return;
        }
        
        for (int j = 0; j < inputStr[pos].length(); j++){
            exitStr.append(inputStr[pos].charAt(j));
            letterCombinationHelp(result, exitStr, inputStr, pos + 1);
                
            // remove the appended char
            exitStr.deleteCharAt(exitStr.length() - 1);
        }
        
    }
}