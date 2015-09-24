package Algorithms.algorithm.others;

public class Sqrt {
    public static int sqrt(int x) {
        if (x == 0) {
            return x;
        }
        
        int left = 1;
        int right = x;
        
        int curr = 0;
        
        while(left < right) {
            curr = (left + right)/2;
            int rst = x/curr;
            
            System.out.printf("curr: %d rst:%d left:%d right:%d\n", curr, rst, left, right);
            
            if (rst == curr){
                return rst;
            } else if (rst > curr) {
                left = curr+1;
            } else {
                right = curr-1;
            }
        }
        
        
        System.out.printf("Ret: curr: %d left:%d right:%d x/left:%d \n", curr, left, right, x/left);
        
        if (x/left >= left) {
            return left;
        } else {
            return left - 1;
        }
    }
    
    public static void main(String[] args) {
        int rst = sqrt(8);
        System.out.printf("rst: %d", rst);
    }

}
