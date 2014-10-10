package Algorithms.Divide2;

public class Pow {
    public double pow(double x, int n) {
        if (x == 0) {
            return 0;
        }
        
        if (x == 1 || n == 0) {
            return 1;
        }
        
        if (x == -1) {
            int left = Math.abs(n)%2;
            if (left == 1) {
                return -1;
            } else {
                return 1;
            }
        }
        
        // 注意 这样写是不行的，因为-n在n到最小值会出错，
        // int的最小值（负数）取-n仍然是n 这样就错了。
        
        // if (n < 0) {
        //     double ret1 = pow(x, -n);
        //     return 1/(double)ret1;
        // }
        
        //should consider the case when n is below zero.
        boolean minus = false;
        if (n < 0) {
            minus = true;
            n = -n;
        }
        
        int m = n%2;
        
        // count 
        double ret = pow(x, n/2);
        ret *= ret;
        if (m == 1) {
            ret *= x;
        }
        
        if (minus) {
            return 1/ret;
        }
        return ret;
    }
}