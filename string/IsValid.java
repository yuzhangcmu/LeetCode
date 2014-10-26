package Algorithms.string;

import java.util.Stack;

public class IsValid {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        Stack<Character> stack = new Stack<Character>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (stack.isEmpty()) {
                // 栈为空的时候，不能放右括号
                if (c == ']' || c == ')' || c == '}') {
                    return false;
                }
                stack.push(c);
                continue;
            }
            
            // 栈不为空时，必须 要对应弹出相对应的括号
            if (c == ')' && stack.peek() == '('
               || c == ']' && stack.peek() == '['
               || c == '}' && stack.peek() == '{'
               ) {
                stack.pop();
            // 或者继续压入左括号    
            } else if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            // 否则错误退出    
            } else {
                return false;
            }
        }
        
        return stack.isEmpty();
    }
}