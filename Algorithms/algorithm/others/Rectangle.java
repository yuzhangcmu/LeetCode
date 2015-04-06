package Algorithms.algorithm.others;
import java.util.Stack;


public class Rectangle {
    public static void main(String[] args){
        int[] input = {4,2,0,3,2,5};
        
        int result = largestRectangleArea(input);
        System.out.printf("Result: %d\n", result);
    }
    
    public static int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0){
            return 0;
        }
        
        Stack<Integer> stack = new Stack<Integer>();
        
        int rightBoard = 0;
        
        int maxArea = 0;
        
        stack.push(rightBoard);
        
        for (rightBoard = 1; rightBoard <= height.length; rightBoard ++){
            int leftBoard;
            int h;
            int wide;
            
            int currentHeight = 0;
            if (rightBoard < height.length){
                currentHeight = height[rightBoard];
            }
            
            while (!stack.empty() && currentHeight < height[stack.peek()]){
                /* get the left board of the rectangle. */
                leftBoard = stack.pop();
                h = height[leftBoard];
                if (stack.empty()){
                    wide = rightBoard;
                }else{
                    //wide = rightBoard - leftBoard;
                    wide = rightBoard - stack.peek() -1;
                }                                   
                System.out.printf("area: %d, h:%d, w:%d\n", h*wide, h, wide);
                
                maxArea = Math.max(maxArea, h*wide);
            }
            
            stack.push(rightBoard);
        }
            
        return maxArea;
    }
}
