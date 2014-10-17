package Algorithms.dp;

public class MinDistance {
    public static void main(String[] strs) {
        String str1 = "abcdafasdfsaf", str2 = "acadsadsfsfqweiruiijadsfsadfsadfdwqfjljfaffiuweqro";
        
        System.out.println(minDistance(str1, str2));
        
        int[][] record = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i < str1.length() + 1; i++) {
            for (int j = 0; j < str2.length() + 1; j++) {
                record[i][j] = -1;
            }
        }
        
        System.out.println(dfs(str1, str1.length(), str2, str2.length(), record));
        
//        String str3 = "abc";
//        String str4 = "abcd";
        String str3 = "febc";
        String str4 = "afebc";
        System.out.println(disLessThenTwo(str3, str4));
    }
    
    /*
     * 递归解法，效果跟动规是一样的。而且想起来还可能要简单一点儿。
     * */
    public static int dfs(String word1, int len1, String word2, int len2, int[][] record) {
        if (len1 <= 0) {
            return len2;
        }
        
        if (len2 <= 0) {
            return len1;
        }
        
        if (record[len1][len2] != -1) {
            return record[len1][len2];
        }
        
        int ret = 0;
        
        int index1 = word1.length() - len1;
        int index2 = word2.length() - len2;
        if (word1.charAt(index1) == word2.charAt(index2)) {
            // 相等的时候，只需要直接下一步Rec
            ret = dfs(word1, len1 - 1, word2, len2 - 1, record);
        } else {
            // 不相同的时候，有删除，添加，替换三种操作，分别求解并且求一个最小值
            int del = dfs(word1, len1 - 1, word2, len2, record) + 1;
            int add = dfs(word1, len1, word2, len2 - 1, record) + 1;
            int replace = dfs(word1, len1 - 1, word2, len2 - 1, record) + 1;
            
            ret = Math.min(del, add);
            ret = Math.min(ret, replace);
        }
        
        record[len1][len2] = ret;
        return ret;
    }
    
    /*
     * 递归解法，判断edit次数是不是小于2.
        也就是说，edit只能为1，或是为0
     * */
    public static boolean disLessThenTwo(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return false;
        }
        
        int len1 = word1.length();
        int len2 = word2.length();
        
        int dis = 1;
        
        // 解法原理：如果2个字符串只差最多1的话，有2种情况：
        // 1. 两个字符串相同长度，那肯定是存在replace 一个字符。所以如果遇到不同的字符串，
        //    两个指针都向前移动，一旦遇到不同的，就认为错误退出即可。
        // 2. 两个字符串长度不等。那肯定是用了add/delete操作才能使2个字符串相同。所以，
        //    一旦遇到不同的字符，它肯定是插入进去的。
        //    可以将长度长的字符串向后移动，如果后面出现不等的，也是错误退出。
        
        for (int p1 = 0, p2 = 0; p1 < len1 && p2 < len2; p1++, p2++) {
            if (word1.charAt(p1) != word2.charAt(p2)) {
                dis--;
                // 如果不同的字母超过1了，退出
                if (dis < 0) {
                    return false;
                } else {
                    // If str1 < str2, just move forward the point of STR2
                    if (len1 < len2) {
                        p1--;
                    // If str1 > str2, just move forward the point of STR1        
                    } else if (len1 > len2) {
                        p2--;
                    }
                }
            }
        }
        
        return dis >= 0;
    }
    
    public static int minDistance(String word1, String word2) {
        // THE DP FORMULA
        // D[i][j]: The min operations to change from s1 to s2.
        // s1: i characters in word1, s2: j characters in word2.
        // 
        // D[i][j] = 
        
        if (word1 == null || word2 == null) {
            return -1;
        }
        
        int len1 = word1.length();
        int len2 = word2.length();
        
        // create a DP array.        
        // 注意：一定要多分配1个。
        // 取0表示从string中一个都不取
        int[][] D = new int[len1 + 1][len2 + 1];
        
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0 && j == 0) {
                    D[i][j] = 0;
                } else if (i == 0) {
                    // Need to add a new element to do it.
                    D[i][j] = D[i][j - 1] + 1;
                } else if (j == 0) {
                    // Need to delete a element to get the string 2.
                    D[i][j] = D[i - 1][j] + 1;
                } else {
                    // we can come from 3 options:
                    // 1. D[i][j - 1]
                    // 2. D[i - 1][j]
                    // 3. D[i - 1][j - 1]
                    D[i][j] = Math.min(D[i][j - 1] + 1, D[i - 1][j] + 1);
                    
                    // 注意这里的Index是 i - 1 跟 j - 1.
                    // 因为i的意思是从string1取出i个字符，所以最后一个字符的索引是i - 1
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        // 最后一个字符相等，不需要变化
                        D[i][j] = Math.min(D[i][j], D[i - 1][j - 1]);
                    } else {
                        // 最后一个字符不等，需要replace.
                        D[i][j] = Math.min(D[i][j], D[i - 1][j - 1] + 1);
                    }
                }
            }
        }
        
        return D[len1][len2];
    }
}
