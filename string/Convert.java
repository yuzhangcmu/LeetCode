package Algorithms.string;

public class Convert {
    public static void main(String[] strs) {
        System.out.println(convert("A", 1));
    }
    
    public static String convert(String s, int nRows) {
        if (s == null) {
            return null;
        }
        
        // 第一个小部分的大小        
        int size = 2 * nRows - 2;
        
        // 当行数为1的时候，不需要折叠。
        if (nRows <= 1) {
            return s;
        }
        
        StringBuilder ret = new StringBuilder();
        
        int len = s.length();
        for (int i = 0; i < nRows; i++) {
            // j代表第几个BLOCK
            for (int j = i; j < len; j += size) {
                ret.append(s.charAt(j));
                
                // 即不是第一行，也不是最后一行，还需要加上中间的节点
                int mid = j + size - i * 2;
                if (i != 0 && i != nRows - 1 && mid < len) {
                    char c = s.charAt(mid);
                    ret.append(c);
                }
            }
        }
        
        return ret.toString();
    }
}
