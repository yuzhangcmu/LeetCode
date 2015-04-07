package Algorithms.algorithm.others;

public class DivideTwoIntegers {
    public static void main(String[] args) {
        System.out.println(divide(-2147483648, 1));
    }
    
    public static int divide(int dividend, int divisor) {
        long a = Math.abs((long)dividend);
        long b = Math.abs((long)divisor);
        int ret = 0;
        
        while (a >= b) {
            long bTmp = b;
            int cnt = 1;
            for (int i = 1; a >= bTmp; cnt <<= 1, bTmp <<= 1) {
                a -= bTmp;
                ret += cnt;
            }
        }
        
        return ((dividend > 0) ^ (divisor > 0)) ? -ret: ret;
    }
}
