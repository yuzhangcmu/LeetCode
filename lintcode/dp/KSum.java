package Algorithms.lintcode.dp;

public class KSum {
    public static void main(String[] strs) {
        int[] A = {1,2,3,4};
        //kSum(A, 2, 5);
        
        System.out.println(-3%2);
        System.out.println(-14%10);
    }
    /**
     * @param A: an integer array.
     * @param k: a positive integer (k <= length(A))
     * @param target: a integer
     * @return an integer
     */
    public int  kSum1(int A[], int k, int target) {
        // write your code here
        if (target < 0) {
            return 0;
        }
        
        int len = A.length;
        
        int[][][] D = new int[len + 1][k + 1][target + 1];
        
        for (int i = 0; i <= len; i++) {
            for (int j = 0; j <= k; j++) {
                for (int t = 0; t <= target; t++) {
                    if (j == 0 && t == 0) {
                        // select 0 number from i to the target: 0
                        D[i][j][t] = 1;
                    } else if (!(i == 0 || j == 0 || t == 0)) {
                        D[i][j][t] = D[i - 1][j][t];
                        if (t - A[i - 1] >= 0) {
                            D[i][j][t] += D[i - 1][j - 1][t - A[i - 1]];
                        }
                    }
                }
            }
        }
        
        return D[len][k][target];
    }
    
    // 2 dimension
    public int  kSum(int A[], int k, int target) {
        // write your code here
        if (target < 0) {
            return 0;
        }
        
        int len = A.length;
        
        // D[i][j]: k = i, target j, the solution.
        int[][] D = new int[k + 1][target + 1];
        
        // only one solution for the empty set.
        D[0][0] = 1;
        for (int i = 1; i <= len; i++) {
            
                for (int j = 1; j <= k; j++) {
                    for (int t = target; t > 0; t--) {
                    if (t - A[i - 1] >= 0) {
                        D[j][t] += D[j - 1][t - A[i - 1]];
                    }
                }
            }
        }
        
        return D[k][target];
    }
}
