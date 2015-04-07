package Algorithms.algorithm.interviews.uber;

import java.util.ArrayList;

public class FindWord {
    public static void main(String[] strs) {
        System.out.println(findAllStrings("xxisxhis"));
        
    }
    
    public static boolean isAWord(String s) {
        if (s.equals("is")) {
            return true;
        } else if (s.equals("his")){
            return true;
        }
        return false;
    }
    
    public static ArrayList<String> findAllStrings(String s) {
        ArrayList<String> ret = new ArrayList<String>();
        
        if (s == null || s.length() == 0) {
            return ret;    
        }
        
        int len = s.length();
        
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                String sub = s.substring(i, j + 1);
                if (isAWord(sub)) {
                    ret.add(sub);
                }
            }
        }
        
        return ret;
    }
}
