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
        if (!ret && index2 < len2 && s2.charAt(index2) == s3.charAt(index3)) {
            ret = recMemory2(s1, index1, s2, index2 + 1, s3, index3 + 1, memory);
        }

        memory[index1][index2] = ret ? 1 : 0;
        return ret;
    }
}
