package Algorithms.dp;

public class IsMatch {
    public static void main(String[] strs) {
//        System.out.println(isMatch("aa","a"));
//        System.out.println(isMatch("aa","aa**"));
//        System.out.println(isMatch("aaa","aa"));
        System.out.println(isMatch("hi", "*?"));
//        System.out.println(isMatch("aa","a*"));
//        System.out.println(isMatch("ab","?*"));
//        System.out.println(isMatch("aab", "c*a*b"));
    }
    
    public static boolean isMatch1(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        
        int lens = s.length();
        int lenp = p.length();
        
        boolean[][] D = new boolean[lens + 1][lenp + 1];
        
        boolean flag = false;
        
        for (int i = 0; i <= lens; i++) {
            flag = false;
            for (int j = 0; j <= lenp; j++) {
                // both is empty.
                if (i == 0 && j == 0) {
                    D[i][j] = true;
                    flag = true;
                    continue;
                }
                
                // if P is empty, s is not empty, it is false.
                if (j == 0) {
                    D[i][j] = false;
                    continue;
                }
                
                // if S is empty, P is not empty
                if (i == 0) {
                    D[i][j] = D[i][j - 1] && p.charAt(j - 1) == '*';
                } else {
                    D[i][j] = (matchChar(s.charAt(i - 1), p.charAt(j - 1)) && D[i - 1][j - 1])
                      || (p.charAt(j - 1) == '*' && (D[i][j - 1] || D[i - 1][j]));    
                }
                
                if (D[i][j]) {
                    flag = true;
                }
                
                if (D[i][j] && p.charAt(j - 1) == '*' && j == lenp) {
                    return true;
                }
            }
            
            if (!flag) {
                return false;
            }
        }
        
        return D[lens][lenp];
    }
    
    public static boolean matchChar(char c, char p) {
        return (p == '?' || p == c);
    }
    
    public static boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        
        int indexS = 0;
        int indexP = 0;
        
        int lenS = s.length();
        int lenP = p.length();
        
        int preS = 0;
        int preP = 0;
        
        boolean back = false;
        
        while (indexS < lenS) {
            if (indexP < lenP && matchChar(s.charAt(indexS), p.charAt(indexP))) {
                indexS++;
                indexP++;
            } else if (indexP < lenP && p.charAt(indexP) == '*') {
                while (indexP < lenP && p.charAt(indexP) == '*') {
                    indexP++;
                }
                
                if (indexP == lenP) {
                    return true;
                }
                
                back = true;
                preS = indexS;
                preP = indexP;
            } else {
                if (back) {
                    indexS = ++preS;
                    indexP = preP;
                } else {
                    return false;
                }
            }
        }
        
        while (indexP < lenP && p.charAt(indexP) == '*') {
            indexP++;
        }
        
        if (indexS == lenS && indexP == lenP) {
            return true;
        }

        return false;
    }
}
