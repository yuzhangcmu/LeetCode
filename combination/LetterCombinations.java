package Algorithms.combination;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinations {
    String[] map = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    
    public List<String> letterCombinations(String digits) {
        List<String> ret = new ArrayList<String>();
        if (digits == null) {
            return ret;
        }
        
        dfs(digits, new StringBuilder(), ret, 0);
        return ret;
    }
    
    public void dfs(String digits, StringBuilder sb, List<String> ret, int index) {
        int len = digits.length();
        if (index == len) {
            ret.add(sb.toString());
            return;
        }
        
        // get the possiable selections.
        String s = map[digits.charAt(index) - '0'];
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            dfs(digits, sb, ret, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    
}