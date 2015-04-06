package Algorithms.stack;

import java.util.Stack;

public class EvalRPN {
    public int evalRPN(String[] tokens) {
        if (tokens == null) {
            return 0;
        }
        
        int len = tokens.length;
        Stack<Integer> s = new Stack<Integer>();
        
        for (int i = 0; i < len; i++) {
            String str = tokens[i];
            if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {
                // get out the two operation number.
                int n2 = s.pop();
                int n1 = s.pop();
                if (str.equals("+")) {
                    s.push(n1 + n2);
                } else if (str.equals("-")) {
                    s.push(n1 - n2);
                } else if (str.equals("*")) {
                    s.push(n1 * n2);
                } else if (str.equals("/")) {
                    s.push(n1 / n2);
                }
            } else {
                s.push(Integer.parseInt(str));
            }
        }
        
        if (s.isEmpty()) {
            return 0;
        }
        
        return s.pop();
    }
}