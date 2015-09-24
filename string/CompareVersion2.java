package Algorithms.string;
public class CompareVersion2 {
    public static void main(String[] args) {
        
        
        System.out.println(compareVersion("01", "1.0.1"));
    }
    
    public static int compareVersion(String version1, String version2) {
        if (version1 == null || version2 == null) {
            return 0;
        }
        
        int len1 = version1.length();
        int len2 = version2.length();
        
        int p1 = 0;
        int p2 = 0;
        
        int v1 = 0;
        int v2 = 0;
        while (true) {
            v1 = 0;
            v2 = 0;
            
            // Like this: 1.3  VS  1.3.4
            // The length of the two version are not equal.
            if (p1 >= len1 && p2 >= len2) {
                return 0;
            }
            
            while (p1 < len1 && version1.charAt(p1) != '.') {
                v1 = v1 * 10 + ((int)version1.charAt(p1) - '0');
                p1++;
            }    
            
            while (p2 < len2 && version2.charAt(p2) != '.') {
                v2 = v2 * 10 + ((int)version2.charAt(p2) - '0');
                p2++;
            }
            
            if (v1 > v2) {
                return 1;
            } else if (v1 < v2) {
                return -1;
            }
            
            p1++;
            p2++;
        }
    }
}
