package Algorithms.string;

public class AddBinary {
    public String addBinary(String a, String b) {
        if (a == null || b == null) {
            return null;
        }
        
        if (a.length() == 0) {
            return b;
        }
        
        if (b.length() == 0) {
            return a;
        }
        
        StringBuilder sb = new StringBuilder();
        
        int p1 = a.length() - 1;
        int p2 = b.length() - 1;
        
        int carry = 0;
        while (p1 >= 0 || p2 >= 0) {
            int sum = carry;
            if (p1 >= 0) {
                sum += (a.charAt(p1) - '0');
            }
            
            if (p2 >= 0) {
                sum += (b.charAt(p2) - '0');
            }
            
            char c = sum % 2 == 1 ? '1': '0';
            sb.insert(0, c);
            carry = sum / 2;
            
            p1--;
            p2--;
        }
        
        if (carry == 1) {
            sb.insert(0, '1');    
        }
        
        return sb.toString();
    }
}


    
    