package Algorithms.algorithm.others;

public class AddBinary {
    public static void main(String[] args) {
        AddBinary ab = new AddBinary();
        System.out.println(ab.addBinary("100", "110010"));
        
        return;
        
    }
    
    public String addBinary(String a, String b) {
        int sLen = 0;
        int lLen = 0;
        int carries = 0;
        
        String l = null;
        String s = null;
        if (a.length() > b.length()) {
            sLen = b.length();
            lLen = a.length();
            l = a;
            s = b;
        } else {
            sLen = a.length();
            lLen = b.length();
            l = b;
            s = a;
        }
        
        StringBuilder rst = new StringBuilder();
        
        int i = 0;
        for (i = sLen - 1; i >= 0; i--) {
            int sum = (int)(s.charAt(i) - '0') + (int)(l.charAt(lLen - sLen + i) - '0') + carries;
            if (sum >= 2) {
                carries = 1;
            } else {
                carries = 0;
            }
            rst.insert(0, String.valueOf(sum%2));
        }
        
        for (i = lLen - sLen - 1; i >= 0; i--) {
            if (carries == 1) {
                if (l.charAt(i) == '0') {
                    carries = 0;
                    rst.insert(0,'1');
                } else {
                    rst.insert(0,'0');
                }               
            } else {
                rst.insert(0, l, 0, i + 1);
                return rst.toString();
            }
        }
        
        if (carries == 1) {
            rst.insert(0,'1');
        }
        
        return rst.toString();        
    }

}
