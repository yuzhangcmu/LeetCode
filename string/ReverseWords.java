package Algorithms.string;

public class ReverseWords {
    public static void main(String[] strs) {
        reverseWords("       I love cmu   ");
    }
    
    /**
     * @param s : A string
     * @return : A string
     */
    public static String reverseWords(String s) {
        // write your code
        if (s == null) {
            return null;
        }
        
        StringBuilder sb = new StringBuilder();
        
        //System.out.println(s);
        
        // remove the leading and the tail space.
        String sTrim = s.trim();
        String[] strs = sTrim.split("\\s+");
        for (int i = strs.length - 1; i >= 0; i--) {
            System.out.println("word:" + strs[i]);
            if (strs[i].equals("")) {
                continue;
            }
            
            sb.append(strs[i]);
            if (i != 0) {
                sb.append(" ");
            }
        }
        
        return sb.toString();
    }
    
    /**
     * @param s : A string
     * @return : A string
     */
    public String reverseWords1(String s) {
        // write your code
        if (s == null) {
            return null;
        }
        
        StringBuilder sb = new StringBuilder();
        
        // remove the leading and the tail space.
        String sTrim = s.trim();
        String strs[] = sTrim.split("\\s+");
        for (int i = strs.length - 1; i >= 0; i--) {
            sb.append(strs[i]);
            if (i != 0) {
                sb.append(" ");
            }
        }
        
        return sb.toString();
    }
}

