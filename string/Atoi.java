package Algorithms.string;

public class Atoi {
    public int atoi(String str) {
        if (str == null) {
            return 0;
        }
        
        // remove the spaces in the beginning and the end.
        String s = str.trim();
        
        boolean minus = false;
        long num = 0;
        for (int i = 0; i < s.length(); i++) {
            /*
             takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them              as a numerical value.
            */
            if (i == 0 && s.charAt(i) == '+') {
                continue;
            } else if (i == 0 && s.charAt(i) == '-'){
                // get the 
                minus = true;
                continue;
            }
            
            int c = s.charAt(i) - '0';
            if (c > 9 || c < 0) {
                // invalid character.
                break;
            }
            
            num = num * 10 + c;
        }
        
        // If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is           // returned.
        if (minus) {
            num = -num;
            num = Math.max(num, Integer.MIN_VALUE);
        } else {
            num = Math.min(num, Integer.MAX_VALUE);
        }
        
        return (int)num;
    }
}