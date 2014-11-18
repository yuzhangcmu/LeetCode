package Algorithms.stack;

import java.util.Stack;

class MinStack {
    public static void main(String[] strs) {
        MinStack sta = new MinStack();
        
        //push(512),push(-1024),push(-1024),push(512),pop,getminStack,pop,getminStack,pop,getminStack
        sta.push(512);
        sta.push(-1024);
        sta.push(-1024);
        sta.push(512);
        
        sta.pop();
        sta.getminStack();
        sta.pop();
        sta.getminStack();
        sta.pop();
        sta.getminStack();
    }
    
    Stack<Integer> elements = new Stack<Integer>();
    Stack<Integer> minStack = new Stack<Integer>();
    
    public void push(int x) {
        elements.push(x);
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }

    public void pop() {
        if (elements.isEmpty()) {
            return;
        }
        
        if (elements.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        elements.pop();
    }

    public int top() {
        return elements.peek();       
    }

    public int getminStack() {
        return minStack.peek();
    }
}
