package Algorithms.string;

import java.util.Stack;

public class LongestValidParentheses {
    public static void main(String[] strs) {
        System.out.println(longestValidParentheses("(()()())"));
    }
    
    public static int longestValidParentheses(String s) {
        if (s == null) {
            return 0;
        }    
        
        Stack<Integer> stk = new Stack<Integer>();
        int sum = 0;
        int tmp = 0;
        
        int max = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                stk.push(i);
            } else {
                if (stk.isEmpty()) {
                    // 栈中没有'(',出现')', 则必须重置计算
                    sum = 0;
                    continue;
                }
                
                // count the temporary lenght:
                // like: (()()()
                //        tmp = 2.
                tmp = i - stk.pop() + 1;
                if (stk.isEmpty()) {
                    // 有一套完整的括号集，可以加到前面的一整套括号集上
                    // () (()())
                    //  1    2  第二套括号集可以加过来
                    sum += tmp;
                    max = Math.max(sum, max);
                } else {
                    // 也可能是一个未完成的括号集，比如：
                    // () (()()  在这里 ()() 是一个未完成的括号集，可以独立出来计算，作为
                    // 阶段性的结果
                    tmp = i - stk.peek();
                    max = Math.max(tmp, max);
                }
            }
        }
        
        return max;
    }    
    public int longestValidParentheses2(String s) {
        if (s == null) {
            return 0;
        }
        
        int len = s.length();
        Stack<Integer> stk = new Stack<Integer>();
        
        int sum = 0;
        int max = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stk.push(i);
            } else {
                if (stk.isEmpty()) {
                    // The sequence is cut off.
                    sum = 0;
                } else {
                    int tmp = i - stk.pop() + 1;
                    if (stk.isEmpty()) {
                        sum += tmp;
                        max = Math.max(max, sum);
                    } else {
                        max = Math.max(max, i - stk.peek());
                    }
                }
            }
        }
        
        return max;
    }
}