package Algorithms.lintcode.math;

class FastPower {
    public static void main(String[] strs) {
        System.out.println(fastPower(2147483647, 2147483645, 214748364));
        
        //System.out.println(Math.pow(2, 2147483647));
        
        System.out.println(9 % (-2));
    }
    
    /*
     * @param a, b, n: 32bit integers
     * @return: An integer
     */
    /*
     * @param a, b, n: 32bit integers
     * @return: An integer
     */
    public static int fastPower(int a, int b, int n) {
        // write your code here
        long ret = pow(a, b, n);
        
        return (int) ret;
    }
    
    // suppose n > 0
    public static long pow1(int a, int b, int n) {
        if (a == 0) {
            return 0;
        }
        
        // The base case.
        if (n == 0) {
            return 1 % b;
        }
        
        if (n == 1) {
            return a % b;
        }
        
        long ret = 0;
        
        // (a * b) % p = (a % p * b % p) % p （3）
        ret = pow(a, b, n / 2);
        ret *= ret;
        
        // 这一步是为了防止溢出
        ret %= b;
        
        if (n % 2 == 1) {
            ret *= pow(a, b, 1);
        }
        
        // 执行取余操作
        ret = ret % b;
        
        return ret;
    }
    
    // SOLUTION 2:
    // suppose n > 0
    public static long pow(int a, int b, int n) {
        if (a == 0) {
            return 0;
        }
        
        // The base case.
        if (n == 0) {
            return 1 % b;
        }
        
        long ret = 0;
        
        // (a * b) % p = (a % p * b % p) % p （3）
        ret = pow(a, b, n / 2);
        ret *= ret;
        
        // 这一步是为了防止溢出
        ret %= b;
        
        if (n % 2 == 1) {
            ret *= (a % b);
        }
        
        // 执行取余操作
        ret = ret % b;
        
        return ret;
    }
};
