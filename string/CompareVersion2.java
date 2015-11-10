package Algorithms.string;
public class CompareVersion2 {
    public static void main(String[] args) {
        
        
        System.out.println(compareVersion("01", "1.0.1"));
    }
    
    public static int compareVersion(String version1, String version2) {
        if (version1 == null || version2 == null || version1.length() == 0
            || version2.length() == 0) {
            return 0;
        }
        
        
        String [] strs1 = version1.split("\\.");
        String [] strs2 = version2.split("\\.");
        
        int size1 = strs1.length;
        int size2 = strs2.length;
        
        int maxSize = Math.max(size1,size2);
        
        for (int i=0; i< maxSize; i++) {
            int v1 = 0;
            int v2 = 0;
            if (size1 > i)
                v1 = Integer.parseInt(strs1[i]);
            if (size2 > i)
                v2 = Integer.parseInt(strs2[i]);
            
            if (v1 > v2) {
                return 1;
            } else if (v1 < v2) {
                return -1;
            }
            
        }
        
        return 0;
    }
}
