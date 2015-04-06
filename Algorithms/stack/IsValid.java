package Algorithms.stack;

import java.util.Stack;

public class IsValid {
	public boolean isValid1(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        Stack<Character> stack = new Stack<Character>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (stack.isEmpty()) {
                if (c == ']' || c == ')' || c == '}') {
                    return false;
                }
                stack.push(c);
                continue;
            }
            
            if (c == ')' && stack.peek() == '('
               || c == ']' && stack.peek() == '['
               || c == '}' && stack.peek() == '{'
               ) {
                stack.pop();
            } else if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                return false;
            }
        }
        
        return stack.isEmpty();
    }
    public boolean isValid(String s) {
        if (s == null) {
            return false;
        }
        
        int len = s.length();
        
        // bug 1: don't use s as the name of the stack.
        Stack<Character> stk = new Stack<Character>();
        
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            switch(c) {
                case '(':
                case '[':
                case '{':
                    stk.push(c);
                    break;
                case ')':
                    if (!stk.isEmpty() && stk.peek() == '(') {
                        stk.pop();
                    } else {
                        return false;
                    }
                    break;
                case '}':
                    if (!stk.isEmpty() && stk.peek() == '{') {
                        stk.pop();
                    } else {
                        return false;
                    }
                    break;
                case ']':
                    if (!stk.isEmpty() && stk.peek() == '[') {
                        stk.pop();
                    } else {
                        return false;
                    }
                    break;    
            }
        }
        
        return stk.isEmpty();
    }
}