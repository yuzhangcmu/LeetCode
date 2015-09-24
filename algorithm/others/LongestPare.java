package Algorithms.algorithm.others;

import java.util.Stack;

public class LongestPare {
    public static void main(String[] args) {
        LongestPare lp = new LongestPare();
        lp.longestValidParentheses("(()");
    }
    
    public int longestValidParentheses(String s) {
        int cnt = 0;
        int max = 0;
        
        int cum = 0;
        
        // to store the position of the '('
        Stack<Integer> stk = new Stack<Integer>();
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stk.push(i);
            } else {
                if (!stk.isEmpty()) { // when the stack is not empty, means that I can add ')'
                    cnt = i - stk.pop() + 1; // get the avaiable length.
                    System.out.printf("cnt = %d\n", cnt);
                    
                    if (stk.isEmpty()) {
                        // cum refresh.
                        cum += cnt;
                        cnt = 0;
                        System.out.printf("stk.isEmpty() cnt = %d cum=%d\n", cnt, cum);
                    } else {
                        // cnt refresh.
                        cnt = i - stk.peek(); // get the new tempory length.
                        System.out.printf("!stk.isEmpty() cnt = %d cum=%d\n", cnt, cum);
                    }
                    
                    System.out.printf("Bef cnt = %d cum=%d max=%d\n", cnt, cum, max);
                    int tmp = Math.max(cnt, cum);
                    max = Math.max(max, tmp);
                    System.out.printf("Aft cnt = %d cum=%d max=%d\n", cnt, cum, max);
                } else { // error character, record the last number.
                    cum = 0;
                    cnt = 0;
                }
                
                
            }
        }
        System.out.printf("max: %d\n", max);
        return max;   
        
        //return Math.max(cnt,max);
    }

}
