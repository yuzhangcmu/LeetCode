package Algorithms.algorithm.others;
import java.util.Arrays;


public class TreeSumCloset {
    public static void main(String[] args) {
        int[] n = {-10,0,-2,3,-8,1,-10,8,-8,6,-7,0,-7,2,2,-5,-8,1,-4,6};
        //int[] n = {0,0,0};
        int r = threeSumClosest(n, 18);
        
        System.out.printf("RETUREN: %d\n", r);
    }
    
    public static int threeSumClosest(int[] num, int target) {
        // first sort the array.
        Arrays.sort(num);
        
        int rst = 0;
        boolean first = true;
        int sum = 0;
        
        for (int i = 0; i <= num.length-3; i++) {
            int n2 = i+1;
            int n3 = num.length-1;
            
            ok:while (n2 < n3) {
                while (n2 - 1 > i && num[n2] == num[n2 - 1]){
                    n2++;                    
                    if (n2 >= n3) {
                        break ok;
                    }
                }
                
                while (n3 + 1 <= num.length - 1 && num[n3] == num[n3 + 1]) {
                    n3--;
                    if (n2 >= n3) {
                        break ok;
                    }
                }
                
                if (n2 >= n3) {
                    //break;
                }
                
                sum = num[i]+num[n2]+num[n3];
                
                if (sum > target) { // move left.
                    n3--;
                } else if (sum < target) { // move right
                    n2++;
                } else { // can return because it is equal;
                    System.out.printf("return: %d 1:%d 2:%d 3:%d \n",rst,num[i],num[n2],num[n3]);
                    return target; 
                    
                }
            }
            
            if (first == true) {
                rst = sum;
                first = false;
                System.out.printf("first: %d \n", rst);
            } else if (Math.abs(sum-target) < Math.abs(rst-target)) {
                rst = sum;
                System.out.printf("later: %d \n", rst);
            }
        }
        
        System.out.printf("LAST: %d \n", rst);
        return rst;
    }

}
