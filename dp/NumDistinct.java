package Algorithms.dp;

public class NumDistinct {
    public int numDistinct1(String S, String T) {
        if (S == null || T == null) {
            return 0;
        }
        
        int lenS = S.length();
        int lenT = T.length();
        
        if (lenS < lenT) {
            return 0;
        }
        
        int[][] D = new int[lenS + 1][lenT + 1];
        
        // BUG 1: forget to use <= instead of <....
        for (int i = 0; i <= lenS; i++) {
            for (int j = 0; j <= lenT; j++) {
                // both are empty.
                if (i == 0 && j == 0) {
                    D[i][j] = 1;
                } else if (i == 0) {
                    // S is empty, can't form a non-empty string.
                    D[i][j] = 0;
                } else if (j == 0) {
                    // T is empty. S is not empty.
                    D[i][j] = 1;
                } else {
                    D[i][j] = 0;
                    // keep the last character of S.
                    if (S.charAt(i - 1) == T.charAt(j - 1)) {
                        D[i][j] += D[i - 1][j - 1];
                    }
                    
                    // discard the last character of S.
                    D[i][j] += D[i - 1][j];
                }
            }
        }
        
        return D[lenS][lenT];
    }
    
    // SOLUTION 2: recursion version. TLE
    public int numDistinct2(String S, String T) {
        if (S == null || T == null) {
            return 0;
        }
        
        return rec(S, T, 0, 0);
    }
    
    public int rec(String S, String T, int indexS, int indexT) {
        int lenS = S.length();
        int lenT = T.length();
        
        // base case:
        if (indexT >= lenT) {
            // T is empty.
            return 1;
        }
        
        if (indexS >= lenS) {
            // S is empty but T is not empty.
            return 0;
        }
        
        int sum = 0;
        // use the first character in S.
        if (S.charAt(indexS) == T.charAt(indexT)) {
            sum += rec(S, T, indexS + 1, indexT + 1);
        }
        
        // Don't use the first character in S.
        sum += rec(S, T, indexS + 1, indexT);
        
        return sum;
    }
    
    // SOLUTION 3: recursion version with memory.
    public int numDistinct3(String S, String T) {
        if (S == null || T == null) {
            return 0;
        }
        
        int lenS = S.length();
        int lenT = T.length();
        
        int[][] memory = new int[lenS + 1][lenT + 1];
        for (int i = 0; i <= lenS; i++) {
            for (int j = 0; j <= lenT; j++) {
                memory[i][j] = -1;
            }
        }
        
        return rec(S, T, 0, 0, memory);
    }
    
    public int rec(String S, String T, int indexS, int indexT, int[][] memory) {
        int lenS = S.length();
        int lenT = T.length();
        
        // base case:
        if (indexT >= lenT) {
            // T is empty.
            return 1;
        }
        
        if (indexS >= lenS) {
            // S is empty but T is not empty.
            return 0;
        }
        
        if (memory[indexS][indexT] != -1) {
            return memory[indexS][indexT];
        }
        
        int sum = 0;
        // use the first character in S.
        if (S.charAt(indexS) == T.charAt(indexT)) {
            sum += rec(S, T, indexS + 1, indexT + 1, memory);
        }
        
        // Don't use the first character in S.
        sum += rec(S, T, indexS + 1, indexT, memory);
        
        // record the solution.
        memory[indexS][indexT] = sum;
        return sum;
    }
    
    // SOLUTION 4: improved recursion version
    public int numDistinct4(String S, String T) {
        if (S == null || T == null) {
            return 0;
        }
        
        int lenS = S.length();
        int lenT = T.length();
        
        int[][] memory = new int[lenS + 1][lenT + 1];
        for (int i = 0; i <= lenS; i++) {
            for (int j = 0; j <= lenT; j++) {
                memory[i][j] = -1;
            }
        }
        
        return rec4(S, T, 0, 0, memory);
    }
    
    public int rec4(String S, String T, int indexS, int indexT, int[][] memory) {
        int lenS = S.length();
        int lenT = T.length();
        
        // base case:
        if (indexT >= lenT) {
            // T is empty.
            return 1;
        }
        
        if (indexS >= lenS) {
            // S is empty but T is not empty.
            return 0;
        }
        
        if (memory[indexS][indexT] != -1) {
            return memory[indexS][indexT];
        }
        
        int sum = 0;
        for (int i = indexS; i < lenS; i++) {
            // choose which character in S to choose as the first character of T.
            if (S.charAt(i) == T.charAt(indexT)) {
                sum += rec4(S, T, i + 1, indexT + 1, memory);
            }
        }
        
        // record the solution.
        memory[indexS][indexT] = sum;
        return sum;
    }
    
    // SOLUTION 5: improved recursion version without memory.
    public int numDistinct(String S, String T) {
        if (S == null || T == null) {
            return 0;
        }

        return rec5(S, T, 0, 0);
    }
    
    public int rec5(String S, String T, int indexS, int indexT) {
        int lenS = S.length();
        int lenT = T.length();
        
        // base case:
        if (indexT >= lenT) {
            // T is empty.
            return 1;
        }
        
        if (indexS >= lenS) {
            // S is empty but T is not empty.
            return 0;
        }
        
        int sum = 0;
        for (int i = indexS; i < lenS; i++) {
            // choose which character in S to choose as the first character of T.
            if (S.charAt(i) == T.charAt(indexT)) {
                sum += rec5(S, T, i + 1, indexT + 1);
            }
        }
        
        return sum;
    }
    
}