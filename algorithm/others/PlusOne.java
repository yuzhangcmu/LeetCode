package Algorithms.algorithm.others;

public class PlusOne {
    public static void main(String[] args) {
        PlusOne po = new PlusOne();
        int[] digits = {1,2,3};
        po.plusOne(digits);
    }
    
    public int[] plusOne(int[] digits) {
        if (digits.length == 0) {
            return digits;
        }
        
        int[] rst = new int[digits.length];
        boolean next = true;
        
        for (int i = 0; i < digits.length; i++) {
            int tmp = digits[digits.length - i - 1];
            if (next == true) {
                tmp++;
                if (tmp >= 10) {
                    tmp = 0;
                    next = true;
                } else {
                    next = false;
                }
            }
            
            rst[digits.length - i - 1] = tmp;
            
        }
        return rst;
    }

}
