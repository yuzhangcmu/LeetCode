package Algorithms.string;

public class CompareVersion {
    public static void main(String[] args) {
        compareVersion("1", "0");
    }
    
    public static int compareVersion(String version1, String version2) {
        if (version1 == null || version2 == null || version1.length() == 0
            || version2.length() == 0) {
            return 0;
        }
        
        String[] strs1 = version1.split("\\.");        
        String[] strs2 = version2.split("\\.");
        
        int size1 = strs1.length;
        int size2 = strs2.length;
        
        int minSize = Math.min(size1, size2);
        
        for (int i = 0; i < minSize; i++) {
            int v1 = Integer.parseInt(strs1[i]);
            int v2 = Integer.parseInt(strs2[i]);
            
            if (v1 > v2) {
                return 1;
            } else if (v1 < v2) {
                return -1;
            }
        }
        
        return 0;
    }
}
