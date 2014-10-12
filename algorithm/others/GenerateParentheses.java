package Algorithms.algorithm.others;

import java.util.ArrayList;
import java.util.Stack;

public class GenerateParentheses {
    public static void main(String[] args) {
        GenerateParentheses gp = new GenerateParentheses();
        gp.generateParenthesis(2);
    }
    
    public ArrayList<String> generateParenthesis(int n) {
        ArrayList<String> rst = new ArrayList<String>();
        ArrayList<Character> path = new ArrayList<Character>();
        
        Stack<Character> s = new Stack<Character>();
        genParHelp(n, 0, path, s, rst);
        
        System.out.println(rst.toString());
        
        return rst;
    }
    
    public void genParHelp(int n, int index, ArrayList<Character> path, Stack<Character> s, ArrayList<String> rst) {
        if (path.size() == 2*n) {
            // get a string and add it to the string.
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 2*n; i ++) {
                sb.append(path.get(i));  
            }
            rst.add(sb.toString());
            return;
        }
        
        char[] input = {'(', ')'};
        
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                if (s.size() == (2*n - path.size())) {  // full of ((( only can add )
                    continue;
                }
                s.push('(');
            } else {
                if (s.isEmpty()) { // when stack is empty, I can't add a ')';
                    continue;
                }
                s.pop();
            }
            
            path.add(input[i]);
            genParHelp(n, index + 1, path, s, rst);
            path.remove(path.size() - 1);
            
            if (i == 0) {
                s.pop();
            } else {
                s.push('(');
            }
        }
        
        return;
    }

}
