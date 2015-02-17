package Algorithms.divide2;

public class Sqrt {
    public static void main(String[] strs) {
        //System.out.println(sqrt(2));
        
        int[] A = {1, 2, 3, 4};
        System.out.println(find(A, 2));
    }
    
 // return the index of the target, if not find, return -1;
    public static int find (int[] input, int target) {
        if (input == null) {
           return -1;
        }

        int left = 0; 
        int right = input.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (input[mid] == target) {
                 return mid;
            } else if (input[mid] < target) {
                 left = mid + 1;
            } else {
                 right = mid - 1;
            }
        }

        return -1;
    }
    
    public static int sqrt(int x) {
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
            System.out.println(left + " " + right);
        }
        
        
        return left;
    }
}

//We can use Car to implements the interfaces:
//Gearbox, Engine.
//
//public class Car implements Geabox, Engine {
//    // the 
//}
//
//public interface Geabox {
//}
//
//public interface Engine {
//}【】22；。3