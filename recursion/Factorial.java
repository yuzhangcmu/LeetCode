package Algorithms.recursion;

import java.util.Stack;

public class Factorial {
    public static void main(String[] strs) {
        System.out.println(factorial(10));
        System.out.println(factorialIterator(10));
    }
    
    public static int factorialIterator(Integer n) {
        int ret = 1;
        for (int i = n; i >= 1; i--) {
            ret *= i;
        }
        
        return ret;
    }
    
    
    public static int factorial(Integer n) {
        Stack<Integer> s = new Stack<Integer>();
        
        s.push(n);
        
        int ret = 1;
        
        while (!s.isEmpty()) {
            int cur = s.pop();
            
            if (cur == 1) {
                break;
            }
            
            ret *= cur;
            s.push(cur - 1);
        }
        
        return ret;
    }
}
