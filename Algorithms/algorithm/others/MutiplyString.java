package Algorithms.algorithm.others;
public class MutiplyString {
    public static void main(String[] args) {
        MutiplyString ms = new MutiplyString();
        String rst = ms.multiply("0", "456");
        System.out.println(rst);
        
    }
    
    
    
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return null;
        }
        
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        
        StringBuilder rst = new StringBuilder();
        int carry = 0;
        
        for(int i = 0; i < num1.length(); i++) {
            for (int j = 0; j < num2.length(); j++) {
                int product = Character.getNumericValue(num1.charAt(num1.length() - 1 - i)) * Character.getNumericValue(num2.charAt(num2.length() - 1 - j));
                product += carry;
                //carry = product / 10;
                
                int index = i + j;
                // if it does not exit in the stringBuilder, insert it.
                if (index >= rst.length()) {
                    rst.insert(0, product);
                } else {
                    int sum = product + Character.getNumericValue(rst.charAt(rst.length() - 1 - index));
                    carry = sum / 10;
                    sum = sum % 10;
                    rst.setCharAt(rst.length() - 1 - index, (char)('0' + sum));
                    
                    if (index >= rst.length() - 1 && carry > 0) {
                        rst.insert(0, carry);
                        carry = 0;
                    }
                }
                
            }
        }
        
        return rst.toString();
        
    }
}