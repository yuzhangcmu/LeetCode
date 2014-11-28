package Algorithms.string;

import java.util.ArrayList;
import java.util.List;

public class FullJustify {
    public static void main(String[] strs) {
        String[] words = {"Listen","to","many,","speak","to","a","few."};
        int L = 6;
        fullJustify(words, L);
    }
    
    // SOLUTION 1: recursion.
    public List<String> fullJustify1(String[] words, int L) {
        List<String> ret = new ArrayList<String>();
        
        if (words == null) {
            return ret;
        }
        
        rec(words, L, 0, ret);
        return ret;
    }
    
    public static void rec(String[] words, int L, int index, List<String> list) {
        int len = words.length;
        if (index >= len) {
            return;
        }
        
        int LenTmp = L;
        
        int end = index;
        for (int i = index; i < len && words[i].length() <= L; i++) {
            L -= words[i].length();
            
            // the space follow the word.
            L--;
            end = i;
        }
        
        // 最后一个空格收回
        L++;
        
        // Count how many words do we have.
        int num = end - index + 1;
        
        int extraSpace = 0;
        int firstExtra = 0;
        
        // 单词数大于1，才需要分配，否则所有的空格加到最后即可
        if (num > 1) {
            extraSpace = L / (num - 1);    
            // 首单词后多跟的空格
            firstExtra = L % (num - 1);
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = index; i <= end; i++) {
            sb.append(words[i]);
            
            // The space following every word.
            if (i != end) {
                sb.append(' ');
            }
            
            // 不是最后一行
            if (end != len - 1) {
                // The first words.
                if (firstExtra > 0) {
                    sb.append(' ');
                    firstExtra--;
                }
                
                // 最后一个单词后面不需要再加空格
                if (i == end) {
                    break;
                }
                
                // 每个单词后的额外空格
                int cnt = extraSpace;
                while (cnt > 0) {
                    sb.append(' ');
                    cnt--;
                }
            }
        }
        
        // 最后一行的尾部的空格
        int tailLen = LenTmp - sb.length();
        while (tailLen > 0) {
            sb.append(' ');
            tailLen--;
        } 
        
        list.add(sb.toString());
        rec(words, LenTmp, end + 1, list);
    }
    
    // SOLUTION 2: iteration.
    public List<String> fullJustify2(String[] words, int L) {
        List<String> ret = new ArrayList<String>();
        if (words == null) {
            return ret;
        }
        
        int len = words.length;
        int index = 0;
        
        while (index < len) {
            int LenTmp = L;
        
            int end = index;
            for (int i = index; i < len && words[i].length() <= LenTmp; i++) {
                LenTmp -= words[i].length();
                
                // the space follow the word.
                LenTmp--;
                end = i;
            }
            
            // 最后一个空格收回
            LenTmp++;
            
            // Count how many words do we have.
            int num = end - index + 1;
            
            int extraSpace = 0;
            int firstExtra = 0;
            
            // 单词数大于1，才需要分配，否则所有的空格加到最后即可
            if (num > 1) {
                extraSpace = LenTmp / (num - 1);    
                // 首单词后多跟的空格
                firstExtra = LenTmp % (num - 1);
            }
            
            StringBuilder sb = new StringBuilder();
            for (int i = index; i <= end; i++) {
                sb.append(words[i]);
                
                // The space following every word.
                if (i != end) {
                    sb.append(' ');
                }
                
                // 不是最后一行
                if (end != len - 1) {
                    // The first words.
                    if (firstExtra > 0) {
                        sb.append(' ');
                        firstExtra--;
                    }
                    
                    // 最后一个单词后面不需要再加空格
                    if (i == end) {
                        break;
                    }
                    
                    // 每个单词后的额外空格
                    int cnt = extraSpace;
                    while (cnt > 0) {
                        sb.append(' ');
                        cnt--;
                    }
                }
            }
            
            // 最后一行的尾部的空格
            int tailLen = L - sb.length();
            while (tailLen > 0) {
                sb.append(' ');
                tailLen--;
            } 
            
            ret.add(sb.toString());
            index = end + 1;
        }
        
        return ret;
    }
    
    // SOLUTION 3: iteration2
    public static List<String> fullJustify(String[] words, int L) {
        List<String> ret = new ArrayList<String>();
        if (words == null) {
            return ret;
        }
        
        int len = words.length;
        int index = 0;
        
        while (index < len) {
            int LenTmp = L;
        
            int end = index;
            for (int i = index; i < len && words[i].length() <= LenTmp; i++) {
                LenTmp -= words[i].length();
                
                // the space follow the word.
                LenTmp--;
                end = i;
            }
            
            // 最后一个空格收回
            LenTmp++;
            
            // Count how many words do we have.
            int num = end - index + 1;
            
            int extraSpace = 0;
            int firstExtra = 0;
            
            // 单词数大于1，才需要分配，否则所有的空格加到最后即可
            if (num > 1) {
                extraSpace = LenTmp / (num - 1);    
                // 首单词后多跟的空格
                firstExtra = LenTmp % (num - 1);
            }
            
            StringBuilder sb = new StringBuilder();
            for (int i = index; i <= end; i++) {
                sb.append(words[i]);
                
                int cnt = 0;
                
                if (i == end) {
                    break;
                }
                
                // 不是最后一行
                if (end != len - 1) {
                    // The first words.
                    if (firstExtra > 0) {
                        cnt++;
                        firstExtra--;
                    }
                    
                    // 最后一个单词后面不需要再加空格
                    // 每个单词后的额外空格
                    cnt += extraSpace;
                }
                
                // 1: 每个单词后本来要加的空格
                appendSpace(sb, cnt + 1);
            }
            
            // 最后一行的尾部的空格，或者是只有一个单词的情况
            appendSpace(sb, L - sb.length());
            
            ret.add(sb.toString());
            index = end + 1;
        }
        
        return ret;
    }
    
    public static void appendSpace(StringBuilder sb, int cnt) {
        while (cnt > 0) {
            sb.append(' ');
            cnt--;
        }
    }
}