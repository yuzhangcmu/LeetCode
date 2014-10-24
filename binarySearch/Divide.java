package Algorithms.binarySearch;

public class Divide {
    public int divide(int dividend, int divisor) {
        long a = Math.abs((long)dividend);
        
        // ref : http://blog.csdn.net/kenden23/article/details/16986763
        // Note: 在这里必须先取long再abs，否则int的最小值abs后也是原值
        long b = Math.abs((long)divisor);
        
        int ret = 0;
        // 这里必须是= 因为相等时也可以减
        while (a >= b) {
            // 判断条件是 >=
            for (long deduce = b, cnt = 1; a >= deduce; deduce <<= 1, cnt <<= 1) {
                a -= deduce;
                ret += cnt;
            }
        }
        
        // 获取符号位。根据除数跟被除数的关系来定
        return (dividend > 0) ^ (divisor > 0) ? -ret: ret;
    }
}