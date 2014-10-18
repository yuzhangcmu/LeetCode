package Algorithms.string;

public class LengthOfLastWord {
    public static void main(String[] strs) {
        String s = " the  book ";
        System.out.println(lengthOfLastWord1(s));
    }
    
 // solution 1
    public static int lengthOfLastWord1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        /*
        这里有个规则，它乍看之下很古怪，但很少造成问题：Split会保留开头处的空字段，却舍去结尾处的空字段。例如：

        my @fields = split /:/, “:::a:b:c:::”;        #得到（“”，“”，“”，“a”，“b”，“c”）
        */
        String[] strs = s.split("\\s+");
        
        int size = strs.length;
        if (size == 0) {
            return 0;
        }
        int len = strs[size - 1].length();
        return len;
    }
    
    // solution 2
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        // remove the spaces at the end.
        String strs = s.trim();
        
        int len = strs.length();
        
        int ret = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (strs.charAt(i) == ' ') {
                return ret;
            }
            ret++;
        }
        
        return len;
    }
}
