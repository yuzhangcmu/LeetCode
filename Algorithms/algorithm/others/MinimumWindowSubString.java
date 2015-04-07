package Algorithms.algorithm.others;
import java.util.HashMap;

public class MinimumWindowSubString {
    public static void main(String[] strs) {
        System.out.println(MinimumWindowSubString.minWindow("a", "a"));
    }
    
    public static String minWindow(String S, String T) {
        if (S == null || T == null || T.length() == 0 || S.length() == 0) {
            return "";
        }
        
        int resultLen = -1;
        int resultleft = 0;
        
        int sum = 0;
        HashMap<Character, Integer> setSub = new HashMap<Character, Integer>();
        for (int i = 0; i < T.length(); i++) {
            if (setSub.containsKey(T.charAt(i))) {
                setSub.put(T.charAt(i), setSub.get(T.charAt(i)) + 1);                    
            } else {
                setSub.put(T.charAt(i), 1);    
            }
        }
        
        // count the number of characters in String T;
        sum = setSub.size();
        
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        
        // record the left board.
        int left = 0;
        
        // record the 
        //int equal = sum;
        
        for (int i = 0; i < S.length(); i++) {
            if (setSub.containsKey(S.charAt(i))) {    
                if (map.containsKey(S.charAt(i))) {
                    map.put(S.charAt(i), map.get(S.charAt(i)) + 1);
                } else {
                    map.put(S.charAt(i), 1);    
                }
                
                if (map.get(S.charAt(i)) == setSub.get(S.charAt(i))) {
                    sum--;
                }
            }
            
            // contains all the charaters.
            if (0 == sum) {
                while (true) {
                    Integer number = map.get(S.charAt(left));
                    // it should contains at least the number in T
                    if (number != null && number <= setSub.get(S.charAt(left))) { 
                        map.put(S.charAt(left), number - 1);
                        sum++;
                        break;
                    }
                    
                    if (number != null) {
                        map.put(S.charAt(left), number - 1);
                    }
                    left++;
                }
                
                if (resultLen == -1 || resultLen > i - left + 1) {
                    resultLen = i - left + 1;
                    resultleft = left;
                    left++;
                }
            }
        }
        
        if (resultLen == -1) {
            return "";
        } else {
            return S.substring(resultleft, resultleft + resultLen);
        }
    }
    
}