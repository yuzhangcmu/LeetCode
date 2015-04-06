package Algorithms.algorithm.others;

public class NumDecodings {
    public static void main(String[] strs) {
        int num = numDecodings("10");
    }
    
    public static int numDecodings(String s) {
        // state: f[i]: from 0 to i , the number of ways to decode.
        // func: f[i] = f[i - 1] + 1 + f[i - 2] * if (s.charAt(i - 1) and s.charAt(i) is a char)
        //       that is : s.charAt(i - 1) < = 2
        // init: f[0] = 1
        // solution: f[len - 1]
        
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int len = s.length();
        
        // in here, 0 means 
        int[] num = new int[len];
        
        if ((int)s.charAt(0) - (int)'0' <= 0) {
            return 0;
        } else {
            num[0] = 1;    
        }
        
        for (int i = 1; i < len; i++) {
            num[i] = 0;
            int curr = (int)s.charAt(i) - (int)'0';
            int pre = (int)s.charAt(i - 1) - (int)'0';
            if (curr >= 1) {
                num[i] += num[i - 1];
            }
            
            int sum = pre * 10 + curr;
            
            if (sum >= 10 && sum <= 26) {
                if (i >= 2) {
                    num[i] += num[i - 2];        
                } else {
                    num[i] += 1;
                }
            }
            
            if (num[i] == 0) {
                return 0;
            }
        }
        
        return num[len - 1];
    }

}
