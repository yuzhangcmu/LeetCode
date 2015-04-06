package Algorithms.algorithm.others;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class WordBreak2 {
    public static void main(String[] strs) {
        WordBreak2 wb = new WordBreak2();
        
        Set<String> dict = new HashSet<String>();
        /*
        dict.add("cat");
        dict.add("cats");
        dict.add("and");
        dict.add("sand");
        dict.add("dog");
        */
        String[] s = {"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"};
        for(String s1: s) {
            dict.add(s1);
        }
        
        String s3 = "yu";
        String s4 = s3.substring(2);
        
        wb.wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", dict);
        

    }
    
    public ArrayList<String> wordBreak(String s, Set<String> dict) {
        HashMap<String, ArrayList<String>> memo = new HashMap<String, ArrayList<String>>();
        
        return wordBreakHelp(s, dict, memo, getMaxLen(dict));
    }
    
    private ArrayList<String> wordBreakHelp(String s, Set<String> dict, HashMap<String, ArrayList<String>> memo, int max) {
        // this is cutting branch. We don't need to count the duplicate strings.
        ArrayList<String> rst = memo.get(s);
        if (rst != null) {
            return rst;
        }
        
        rst = new ArrayList<String>();
        
        if (s.length() == 0) {
            return rst;
        }
        
        int len = Math.min(max, s.length());
        for (int i = 0; i < len; i++) {
            String pre = s.substring(0, i + 1);
            // if the pre is not a word, just continue;
            if (!dict.contains(pre)) {
                continue;
            }
            
            if (i == s.length() - 1) {
                rst.add(pre);
                break;
            }
            
            ArrayList<String> tmp = wordBreakHelp(s.substring(i + 1), dict, memo, max);
            // the tail can't be divided;
            for(String s1: tmp) {
                String newStr = pre + " " + s1;
                rst.add(newStr);
            }
        }
        
        memo.put(s, rst);
        return rst;
    }
    
    private int getMaxLen(Set<String> dict) {
        int max = 0;
        for(String s: dict) {
            max = Math.max(max, s.length());
        }
        
        return max;
    }
}