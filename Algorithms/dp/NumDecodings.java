package Algorithms.dp;

public class NumDecodings {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int len = s.length();
        
        // D[i] 表示含有i个字符的子串的DECODE WAYS.
        int[] D = new int[len + 1];
        
        D[0] = 1;
        
        for (int i = 1; i <= len; i++) {
            D[i] = 0;
            
            // 现在正在考察的字符的索引.
            int index = i - 1;
            // 最后一个字符独立解码
            if (isValidSingle(s.charAt(index))) {
                D[i] += D[i - 1];
            }
            
            // 最后一个字符与上一个字符一起解码
            if (i > 1 && isValidTwo(s.substring(index - 1, index + 1))) {
                D[i] += D[i - 2];
            }
        }
        
        return D[len];
    }
    
    public boolean isValidSingle(char c) {
        if (c >= '1' && c <= '9') {
            return true;
        }
        
        return false;
    }
    
    public boolean isValidTwo(String s) {
        int num = Integer.parseInt(s);
        
        return (num >= 10 && num <= 26);
    }
}
