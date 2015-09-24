package Algorithms.dp;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;

public class IsInterleave {
    static PrintWriter writer = null;
    
    public static void main(String[] strs) {
        // String s1 = "aabcc";
        // String s2 = "dbbca";
//        String s1 = "feifei";
//        String s2 = "yuyu";
//        String s3 = "feifeiyuyu";
        
        try {
            writer = new PrintWriter("vitural.txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String s1 = "bbbbbabbbbabaababaaaabbababbaaabbabbaaabaaaaababbbababbbbbabbbbababbabaabababbbaabababababbbaaababaa";
        String s2 = "babaaaabbababbbabbbbaabaabbaabbbbaabaaabaababaaaabaaabbaaabaaaabaabaabbbbbbbbbbbabaaabbababbabbabaab";
        String s3 = "babbbabbbaaabbababbbbababaabbabaabaaabbbbabbbaaabbbaaaaabbbbaabbaaabababbaaaaaabababbababaababbababbbababbbbaaaabaabbabbaaaaabbabbaaaabbbaabaaabaababaababbaaabbbbbabbbbaabbabaabbbbabaaabbababbabbabbab";
        
        // String s3 = "aadbbcbcac";
        // String s31 = "aadbbbaccc";
        long tStart = System.nanoTime();
       
        System.out.println(isInterleave4(s1, s2, s3));
        
        /*if (writer != null) {
            writer.close();
            writer = null;
        }*/
        
        long tEnd = System.nanoTime();
        long tDelta = tEnd - tStart;
        double elapsedSeconds = tDelta;
        System.out.printf("Time used for DP (nano second): %f\n", elapsedSeconds);
        
        tStart = System.nanoTime();
        System.out.println(isInterleave2(s1, s2, s3));
        
        tEnd = System.nanoTime();
        tDelta = tEnd - tStart;
        elapsedSeconds = tDelta;
        System.out.printf("Time used for DFS (nano second): %f\n", elapsedSeconds);
        
        tStart = System.nanoTime();
        System.out.println(isInterleave1(s1, s2, s3));
        
        tEnd = System.nanoTime();
        tDelta = tEnd - tStart;
        elapsedSeconds = tDelta;
        System.out.printf("Time used for DFS WITHOUT MEM (nano second): %f\n", elapsedSeconds);
    }

    // Solution1: Recursion with memory
    public static boolean isInterleave1(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }

        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();

        // The length is not equal, just return false.
        if (len1 + len2 != len3) {
            return false;
        }

        int[][][] memory = new int[len1 + 1][len2 + 1][len3 + 1];
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                for (int k = 0; k <= len3; k++) {
                    memory[i][j][k] = -1;
                }
            }
        }

        return recMemory(s1, 0, s2, 0, s3, 0, memory);
    }

    public static boolean recMemory(String s1, int index1, String s2,
            int index2, String s3, int index3, int[][][] memory) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        
        int[] arr = {1,2,4,3};
        Arrays.sort(arr);

        if (index3 == len3 && index1 == len1 && index2 == len2) {
            return true;
        }

        if (memory[index1][index2][index3] != -1) {
            return memory[index1][index2][index3] == 1;
        }

        // 第一个字符，有2种可能：来自s1, 或是来自s2
        boolean ret = false;
        if (index1 < len1 && s1.charAt(index1) == s3.charAt(index3)) {
            ret = recMemory(s1, index1 + 1, s2, index2, s3, index3 + 1, memory);
        }

        // 如果不成功(首字母不来自于s1)，尝试另一种可能
        if (!ret && index2 < len2 && s2.charAt(index2) == s3.charAt(index3)) {
            ret = recMemory(s1, index1, s2, index2 + 1, s3, index3 + 1, memory);
        }

        //memory[index1][index2][index3] = ret ? 1 : 0;
        return ret;
    }

    // Solution2: Recursion with memory
    // 思考了一下看了一下过去的代码，发现其实我们用不到三维数组，因为len1 + len2 = len3,
    // 所以第三维根本可以省略嘛
    public static boolean isInterleave2(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        
        
        
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();

        // The length is not equal, just return false.
        if (len1 + len2 != len3) {
            return false;
        }

        int[][] memory = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                memory[i][j] = -1;
            }
        }
        
        int[][] D2 = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                D2[i][j] = -1;
            }
        }

        return recMemory2(s1, 0, s2, 0, s3, 0, memory, D2);
    }

    public static boolean recMemory2(String s1, int index1, String s2,
            int index2, String s3, int index3, int[][] memory, int[][] print) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        
        //System.out.printf("Come to index: %d, %d\n", index1, index2);
        
        if (memory[index1][index2] == -1) {
            print[index1][index2] = -2;
            printArrayInt(print);
        }

        // index3 走到最后了，其实意思即是这个字符串为空，因为len1+len2+len3相等，说明其它两个也是空，必然返回true
        if (index3 == len3) {
            print[index1][index2] = 1;
            //System.out.printf("Got a value: %d, %d\n", index1, index2);
            printArrayInt(print);
            return true;
        }

        if (memory[index1][index2] != -1) {
            return memory[index1][index2] == 1;
        }

        // 第一个字符，有2种可能：来自s1, 或是来自s2
        boolean ret = false;
        // 注意，一定要判定index1 是否越界，否则会出问题
        if (index1 < len1 && s1.charAt(index1) == s3.charAt(index3)) {
            ret = recMemory2(s1, index1 + 1, s2, index2, s3, index3 + 1, memory, print);
        }

        // 如果不成功(首字母不来自于s1)，尝试另一种可能,即有可能是来自s2的
        // 注意，一定要判定index2 是否越界，否则会出问题
        // 另外，我们判断!ret 的目的是省去重复计算，如果前一个判断已经是TRUE了，这里就不必再算了。
        if (!ret && index2 < len2 && s2.charAt(index2) == s3.charAt(index3)) {
            ret = recMemory2(s1, index1, s2, index2 + 1, s3, index3 + 1, memory, print);
        }

        memory[index1][index2] = ret ? 1 : 0;
        
        //System.out.printf("Got a value: %d, %d\n", index1, index2);
        print[index1][index2] = memory[index1][index2];
        printArrayInt(print);
        return ret;
    }
    
    public static void printArray(boolean[][] d) {
        int rows = d.length;
        int cols = d[0].length;
        
        System.out.println();
        System.out.println("Print the array");
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(d[i][j] + " ");
            }
            
            System.out.println();
        }
        
    }
    
    public static void printArrayInt(int[][] d) {
        return;
//        int rows = d.length;
//        int cols = d[0].length;
//        
//        //System.out.println("Print the array");
//        writer.println("Print the array");
//        
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                if (d[i][j] == -1) {
//                    //System.out.print("* ");
//                    writer.print("* ");
//                    continue;
//                } else if (d[i][j] == -2) {
//                    //System.out.print("- ");
//                    writer.print("- ");
//                    continue;
//                }
//                
//                //System.out.print(d[i][j] + " ");
//                writer.print(d[i][j] + " ");
//            }
//            
//            //System.out.println();
//            writer.println();
//        }
//        
//        //System.out.println();
//        writer.println();
    }
    
    // Solution3: 
    // DP解法
    // D[i][j]: 定义为s1 (前i个字符) s2(前j个字符) s3(i+j) 是不是交叉字符
    //    (s1.i == s3.(i+j) && D[i-1][j]) || (s2.j == s3.(i+j) && D[i][j - 1])
    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        
        if (len1 + len2 != len3) {
            return false;
        }
        
        boolean[][] D = new boolean[len1 + 1][len2 + 1];
        
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                D[i][j] = false;
                if (i == 0 && j == 0) {
                    D[i][j] = true;
                    continue;
                }
                
                if (i != 0) {
                    D[i][j] |= s1.charAt(i - 1) == s3.charAt(i + j - 1) && D[i - 1][j];
                }
                
                if (j != 0) {
                    D[i][j] |= s2.charAt(j - 1) == s3.charAt(i + j - 1) && D[i][j - 1];
                }
            }
        }
        
        return D[len1][len2];
    }
    
    // Solution4: 
    // DP解法 2
    // D[i][j]: 定义为s1 (前i个字符) s2(前j个字符) s3(i+j) 是不是交叉字符
    //    (s1.i == s3.(i+j) && D[i-1][j]) || (s2.j == s3.(i+j) && D[i][j - 1])
    public static boolean isInterleave4(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        
        if (len1 + len2 != len3) {
            return false;
        }
        
        boolean[][] D = new boolean[len1 + 1][len2 + 1];
        
        int[][] D2 = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                D2[i][j] = -1;
            }
        }
        
        //printArrayInt(D2);
        
        for (int i = len1; i >= 0; i--) {
            for (int j = len2; j >= 0; j--) {
                D[i][j] = false;
                
                if (i == len1 && j == len2) {
                    D[i][j] = true;
                    
                    D2[i][j] = 1;
                    //printArray(D);
                    printArrayInt(D2);
                    continue;
                }
                
                if (i != len1) {
                    D[i][j] |= s1.charAt(i) == s3.charAt(i + j) && D[i + 1][j];
                }
                
                if (!D[i][j] && j != len2) {
                    D[i][j] |= s2.charAt(j) == s3.charAt(i + j) && D[i][j + 1];
                }
                
                D2[i][j] = D[i][j] ? 1: 0;
                printArrayInt(D2);
            }
        }
        
        return D[0][0];
    }
}
