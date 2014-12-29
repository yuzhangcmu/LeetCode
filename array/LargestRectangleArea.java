package Algorithms.array;

import java.util.Stack;

public class LargestRectangleArea {
	public static void main(String[] strs) {
		int[] height = {0};
		System.out.println(largestRectangleArea(height));
	}
	
	public static int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        
        Stack<Integer> s = new Stack<Integer>();
        
        int max = 0;
        
        int len = height.length;
        int i = 0;
        
        while (i <= len) {
            // BUG 1: should use height[s.peek()] instead of s.peek().
            // BUG 2: should put i < length after the s.isEmpty.
            // The last step: Length is also put into the stack and will break at last.
            if (s.isEmpty() || (i < len && height[i] >= height[s.peek()])) {
                s.push(i);
                i++;
            // Keep a Ascending sequence in the stack.    
            } else {
                // Stack is not empty, and the current node is smaller than the one in the stack.
                // When we come to the end of the array, we will also should count all the solutions.
                // BUG 3: should use height[s.pop] instead of s.pop
                // When the i come to the end, the rectangle will be counted again.
                int h = height[s.pop()];
                int width = s.isEmpty() ? i: i - s.peek() - 1;
                max = Math.max(max, h * width);
            } 
        }
        
        return max;
    }
}