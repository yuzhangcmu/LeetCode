package Algorithms.divide2;

public class Sqrt {
    public int sqrt(int x) {
        if (x == 1 || x == 0) {
            return x;
        }
        
        int left = 1;
        int right = x;
        
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            int quo = x / mid;
            
            if (quo == mid) {
                return quo;
            // mid is too big    
            } else if (quo < mid) {
                right = mid;
            } else {
                left = mid;
            }
        }
        
        return left;
    }
}