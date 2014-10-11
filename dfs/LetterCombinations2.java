package Algorithms.dfs;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinations2 {
    public class Solution {
        // We create a map to map the digit to the characters.
        String[] map = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        
        public List<String> letterCombinations(String digits) {
            List<String> ret = new ArrayList<String>();
            ArrayList<Character> path = new ArrayList<Character>();
            
            letterCombinationsHelp(digits, path, ret, 0);
            
            return ret;
        }
        
        /*
         表示从digit的index处开始直到结尾的数字对应的字符的组合数 
        */
        public void letterCombinationsHelp(String digits, ArrayList<Character> path, List<String> ret, int index) {
            if (digits == null) {
                return;
            }
            
            int len = digits.length();
            if (path.size() == len) {
                StringBuilder sb = new StringBuilder();
                for (char c: path) {
                    sb.append(c);
                }
                ret.add(sb.toString());
                return;
            }
            
            // get the possiable characters.
            // for example, get abc from '2'
            String combs = getChar(digits.charAt(index));
            
            for (int i = 0; i < combs.length(); i++) {
                path.add(combs.charAt(i));
                letterCombinationsHelp(digits, path, ret, index + 1);
                path.remove(path.size() - 1);
            }
        }
        
        public String getChar(char c) {
            return map[c - '0'];
        }
    }
}