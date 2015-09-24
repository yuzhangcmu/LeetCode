package Algorithms.twoPoints;

public class MaxArea {
    public int maxArea(int[] height) {
        if (height == null) {
            return 0;
        }
        
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        
        while (left < right) {
            int h = Math.min(height[left], height[right]);
            int area = h * (right - left);
            maxArea = Math.max(maxArea, area);
            
            if (height[left] < height[right]) {
                // 如果左边界比较低，尝试向右寻找更高的边界
                left++;
            } else {
                // 如果右边界比较低，尝试向左寻找更高的边界
                right--;
            }
        }
        
        return maxArea;
    }
}