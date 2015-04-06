package Algorithms.divide2;

public class Pow_1219_2014 {
    public double pow(double x, int n) {
        if (x == 0) {
            return 0;
        }
        
        // base case: when n = 0, the result is 1;
        if (n == 0) {
            return 1;
        }
        
        /*
        递归的主体部分
        */
        
        // X^(-n) = X^(n + 1) * X
        // X^n = 1/(x^(-n))
        if (n < 0) {
            double ret = x * pow(x, -(n + 1));
            return (double)1/ret;
        }
        
        // 将求pow对半分。再将结果相乘
        double ret = pow(x, n / 2);
        ret = ret * ret;
        
        //如果有余数，再乘以x本身。
        if (n % 2 != 0) {
            ret = ret * x;
        }
        
        return ret;
    }
}