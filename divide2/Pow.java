package Algorithms.divide2;

public class Pow {
    public static void main(String[] strs) {
        System.out.println(pow(-3, -2147483648));
        
    }
    
    public static double pow(double x, int n) {
        if (x == 0) {
            return 0;
        }
        
        if (x == 1 || n == 0) {
            return 1;
        }
        
        // Because when we deal with -2147483648, we can't get right -n
        // cause -n == n when it is -2147483648.
        if (n < 0) {
            double ret1 = x * pow(x, -(1 + n));
            return 1/(double)ret1;
        }
        
        int m = n%2;
        
        // count 
        double ret = pow(x, n/2);
        ret *= ret;
        if (m == 1) {
            ret *= x;
        }
        
        return ret;
    }
}