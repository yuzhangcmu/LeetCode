package Algorithms.algorithm.others;
import java.util.Stack;


public class MiniStack {
    // reference: http://stackoverflow.com/questions/685060/design-a-stack-such-that-getminimum-should-be-o1
    Stack<Integer> s = new Stack<Integer>();
    Stack<Integer> minStack = new Stack<Integer>();
    
    public static void main(String[] strs) {
        MiniStack s = new MiniStack();
        s.push(2);
        System.out.println(s.min());
        s.push(6);
        System.out.println(s.min());
        s.push(4);
        System.out.println(s.min());
        s.push(1);
        System.out.println(s.min());
        s.push(5);
        System.out.println(s.min());
        s.push(1);
        System.out.println(s.min());
        
        System.out.println("BEGINE POP");
        
        System.out.println(s.pop());
        System.out.println(s.min());
        
        System.out.println(s.pop());
        System.out.println(s.min());

        System.out.println(s.pop());
        System.out.println(s.min());

        System.out.println(s.pop());
        System.out.println(s.min());

        System.out.println(s.pop());
        System.out.println(s.min());

        System.out.println(s.pop());
        System.out.println(s.min());
    }
    
    public MiniStack() {
        // do initialize if necessary
        super();
    }

    public void push(int number) {
        // write your code here
        if (s.isEmpty() || number <= minStack.peek()) {
            minStack.push(number);
        }
        s.push(number);
        
    }

    public int pop() {
        // write your code here
        if (s.peek() == minStack.peek()) {
            minStack.pop();
        }
        
        return s.pop();
    }

    public int min() {
        // write your code here
        if (minStack.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        return minStack.peek();
    }
}

