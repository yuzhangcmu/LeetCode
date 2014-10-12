package Algorithms.sequence;
public class Reverse {
    public int reverse(int x) {
        long n = x;
        
        boolean neg = false;
        if (n < 0) {
            neg = true;
            n = -n;
        }
        
        long ret = 0;
        while (n > 0) {
            long left = n % 10;
            ret *= 10;
            ret += left;
            n /= 10;
        }
        
        if (neg) {
            ret = -ret;
        }
        
        return (int)ret;
    }
}