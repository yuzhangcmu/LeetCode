package Algorithms.dp;

public class IsInterleave {
    public static void main(String[] strs) {
        // String s1 = "aabcc";
        // String s2 = "dbbca";
        String s1 = "a";
        String s2 = "";
        String s3 = "a";

        // String s3 = "aadbbcbcac";
        // String s31 = "aadbbbaccc";
        //

        System.out.println(isInterleave(s1, s2, s3));
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

        memory[index1][index2][index3] = ret ? 1 : 0;
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

        return recMemory2(s1, 0, s2, 0, s3, 0, memory);
    }

    public static boolean recMemory2(String s1, int index1, String s2,
            int index2, String s3, int index3, int[][] memory) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();

        // index3 走到最后了，其实意思即是这个字符串为空，因为len1+len2+len3相等，说明其它两个也是空，必然返回true
        if (index3 == len3) {
            return true;
        }

        if (memory[index1][index2] != -1) {
            return memory[index1][index2] == 1;
        }

        // 第一个字符，有2种可能：来自s1, 或是来自s2
        boolean ret = false;
        // 注意，一定要判定index1 是否越界，否则会出问题
        if (index1 < len1 && s1.charAt(index1) == s3.charAt(index3)) {
            ret = recMemory2(s1, index1 + 1, s2, index2, s3, index3 + 1, memory);
        }

        // 如果不成功(首字母不来自于s1)，尝试另一种可能,即有可能是来自s2的
        // 注意，一定要判定index2 是否越界，否则会出问题
        // 另外，我们判断!ret 的目的是省去重复计算，如果前一个判断已经是TRUE了，这里就不必再算了。
        if (!ret && index2 < len2 && s2.charAt(index2) == s3.charAt(index3)) {
            ret = recMemory2(s1, index1, s2, index2 + 1, s3, index3 + 1, memory);
        }

        memory[index1][index2] = ret ? 1 : 0;
        return ret;
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

        // The length is not equal, just return false.
        if (len1 + len2 != len3) {
            return false;
        }

        // 注意，这里要用len1 + 1，因为我们要从0算到len1 (0表示s1取空)
        // D[0][0] = true
        boolean[][] D = new boolean[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0 && j == 0) {
                    D[i][j] = true;
                } else {
                    boolean b1 = false;
                    // the index in s3.
                    int index = i + j - 2;
                    if (i > 0 && s1.charAt(i - 1) == s2.charAt(index)
                        && D[i - 1][j]) {
                        b1 = true;
                    }

                    boolean b2 = false;
                    if (j > 0 && s2.charAt(j - 1) == s2.charAt(index)
                        && D[i][j - 1]) {
                        b2 = true;
                    }

                    D[i][j] = b1 | b2;
                }
            }
        }

        return D[len1][len2];
    }
}
