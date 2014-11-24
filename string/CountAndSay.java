package Algorithms.string;

public class CountAndSay {
    public String countAndSay(int n) {
        if (n == 0) {
            return null;
        }
        
        if (n == 1) {
            return "1";
        }
        
        String s = countAndSay(n - 1);
        StringBuilder sb = new StringBuilder();
        
        int len = s.length();
        int cnt = 0;
        for (int i = 0; i < len; i++) {
            cnt++;
            
            if (i == len - 1 || (i < len - 1 && s.charAt(i) != s.charAt(i + 1))) {
                sb.append(cnt);
                sb.append(s.charAt(i));
                cnt = 0;
            }
        }
        
        return sb.toString();
    }
}