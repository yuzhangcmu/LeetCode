package Algorithms.string;

public class Multiply {
    public static void main(String[] strs) {
        multiply("9", "9");
    }
    
    public static String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return null;
        }
        
        int len1 = num1.length();
        int len2 = num2.length();
        
        int[] product = new int[len1 + len2];
        
        // 计算相应位置的product.
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                product[i + j] = (num1.charAt(len1 - 1 - i) - '0') * (num2.charAt(len2 - 1 - j) - '0');
            }
        }
        
        StringBuilder ret = new StringBuilder();
        
        int carry = 0;
        for (int i = 0; i < len1 + len2; i++) {
            product[i] = product[i] + carry;
            int digit = product[i] % 10;
            carry = digit / 10;
            ret.insert(0, digit);
        }
        
        while (ret.length() > 1 && ret.charAt(0) == '0') {
            ret.deleteCharAt(0);
        }
        
        return ret.toString();
    }
}
