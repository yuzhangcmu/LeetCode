package Algorithms.Divide2;

public class Pow {
    public double pow(double x, int n) {
        if (x == 0) {
            return 0;
        }
        
        if (n == 0) {
            return 1;
        }
        
        // should consider the case when n is below zero.
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