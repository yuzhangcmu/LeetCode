package Algorithms.string;

public class RomanToInt {
    public int romanToInt(String s) {
        if (s == null) {
            return 0;
        }
        
        int len = s.length();
        int sum = 0;
        int pre = 0;
        
        for (int i = len - 1; i >= 0; i--) {
            int cur = romanTable(s.charAt(i));
            
            if (i == len - 1) {
                // 如果是在尾部，直接加上当前值
                sum += cur;
            } else {
                // 判定当前值是不是比前一个值要小，如果小，则需要减去它
                if (cur < pre) {
                    sum -= cur;
                } else {
                    sum += cur;
                }
            }
            pre = cur;
        }
        
        return sum;
    }
    
    public int romanTable(char c) {
        int num = 0;
        switch(c) {
            case 'I':
                num = 1;
                break;
            case 'V':
                num = 5;
                break;
            case 'X':
                num = 10;
                break;
            case 'L':
                num = 50;
                break;
            case 'C':
                num = 100;
                break;
            case 'D':
                num = 500;
                break;
            case 'M':
                num = 1000;
                break;
            default:
                num = 0;
                break;
        }
        
        return num;
    }
}