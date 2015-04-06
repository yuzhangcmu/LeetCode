package Algorithms.binarySearch;

public class Divide {
	public static void main(String[] strs) {
		System.out.println(divide(-2147483648, -1));
	}
	
	public int divide1(int dividend, int divisor) {
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
    
	public static int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }
        
        // Note: 在这里必须先取long再abs，否则int的最小值abs后也是原值
        long dividendTmp = Math.abs((long)dividend);
        long divisorTmp = Math.abs((long)divisor);
        
        // bug 3: ret should use Long to avoid overflow.
        long ret = 0;
        // bug 2: should use dividentTmp > divisor as the while judge.
        while (dividendTmp >= divisorTmp) {
            // bug 1: should use Long for tmp.
            long tmp = divisorTmp;
            int rst = 1;
            while(tmp <= dividendTmp) {
                // bug 3: the two statement should inside the while LOOP.
                ret += rst;
                dividendTmp -= tmp;
                
                tmp <<= 1;
                rst <<= 1;
            }
        }
        // bug 4: out of range:
        /*
        Input:	-2147483648, -1
        Output:	-2147483648
        Expected:	2147483647
        */
        //ret = ((dividend > 0) ^ (divisor > 0)) ? -ret: ret;
        ret = ((((dividend ^ divisor) >> 31) & 1) == 1) ? -ret: ret;
        
        if (ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        } else {
            return (int)ret;
        }
    }
	
	public int divide3(int dividend, int divisor) {
        long a = Math.abs((long)dividend);
        long b = Math.abs((long)divisor);
        
        long ret = 0;
        
        while (a >= b) {
            for (long tmp = b, cnt = 1; a >= tmp; tmp <<= 1, cnt <<= 1) {
                ret += cnt;
                a -= tmp;
            }
        }
        
        ret = (((dividend ^ divisor) >> 31) & 1) == 1 ? -ret: ret;
        if (ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        
        return (int)ret;
    }
}