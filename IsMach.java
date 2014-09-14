package Algorithms;

public class IsMach {
    public static void main(String[] str) {
        //System.out.println(isMatch("aa", "aa"));
        System.out.println(isMatch("aab", "c*a*b"));
    }
    
    public static boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        
        return help(s, p, 0, 0);
    }
    
    public static boolean help(String s, String p, int indexS, int indexP) {
        int pLen = p.length();
        int sLen = s.length();
        
        // 1. P结束了，这时 S也应该要结束 
        if (indexP == pLen) {
            return indexS == sLen;  
        }
        
        // 2. P 只有最后一个没有匹配
        if (indexP == pLen - 1) {
            // 必须相等，或者是p为'.'.  
            // S必须只有一个字符
            return indexS == sLen - 1 && matchChar(s, p, indexS, indexP);
        }
        
        // 以下P 至少还有2个字符.
        
        // 2. 单独匹配的情况, 如 aa, a. 类似这样
        if (p.charAt(indexP + 1) != '*') {
           if (indexS < sLen && matchChar(s, p, indexS, indexP)) {
               return help(s, p, indexS + 1, indexP + 1);  // p可以前进一格
           } else {
               return false;
           }
        }
        
        // 3. 多重匹配的情况, 如 .* or a* ,这时需要进行递归
        
        // 先直接跳过此2个正则，因为我们可以匹配空。
        if (help(s, p, indexS, indexP + 2)) {  
            return true;
        }
        
        // 匹配非空的情况,这里不可以跳过p，必须 匹配1个或是多个
        for (int i = indexS; i < sLen; i++) {
            if (!matchChar(s, p, i, indexP)) {
                return false;
            } else {
                if (help(s, p, i + 1, indexP + 2)) {
                    return true;
                }
            }
        }
        
        // 多重匹配之后，余下的字串仍然不可以匹配，则返回失败。
        return false;
    }
    
    // check if the s match p in the index.
    public static boolean matchChar(String s, String p, int indexS, int indexP) {
        return (s.charAt(indexS) == p.charAt(indexP)) || p.charAt(indexP) == '.';
    }
}
