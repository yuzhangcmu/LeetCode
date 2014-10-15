package Algorithms.array;

public class PlusOne {
    public int[] plusOne(int[] digits) {
        if (digits == null) {
            return null;
        }
        
        int overFlow = 0;
        
        int len = digits.length;
        int[] ret = new int[len];
        
        for (int i = len - 1; i >= 0; i--) {
            int sum = digits[i] + overFlow;
            if (i == len - 1) {
                // 只有最后一位需要加1
                sum++;
            }
            
            // 溢出的话，置溢出位。
            if (sum > 9) {
                overFlow = 1;
            } else {
                overFlow = 0;
            }
            
            // 把高位去掉，因为我们要0-9
            ret[i] = sum % 10;
        }
        
        if (overFlow == 1) {
            int[] retOver = new int[len + 1];
            System.arraycopy(retOver, 1, ret, 0, len);
            retOver[0] = 1;
            return retOver;
        }
        
        return ret;
    }
}
