package Algorithms;

public class CoinCount {
    public static void main(String[] strs) {
        System.out.println("T_T");

        int arr[] = { 1, 2, 3 };
        System.out.println(cnt(arr, 4));
        System.out.println(DP(arr, 4));
        System.out.println(DP_Min(arr, 4));
    }

    public static int DP_Min(int S[], int sum) {
        int[][] m = new int[S.length + 1][sum + 1];

        for (int i = 0; i <= S.length; i++) {
            for (int j = 0; j <= sum; j++) {
                // if SUM = 0, then, only need 0 coin.
                if (j == 0) {
                    m[i][j] = 0;
                    continue;
                }

                // no coins
                if (i == 0 && j > 0) {
                    m[i][j] = Integer.MAX_VALUE - 1;
                    continue;
                }

                // don't use element i.
                m[i][j] = m[i - 1][j];

                // Use element i at leaset once.
                if (j >= S[i - 1]) {
                    m[i][j] = Math.min(m[i][j], m[i][j - S[i - 1]] + 1);
                }

            }
        }
        
        return m[S.length][sum];
    }

    // 1:21
    public static int DP(int S[], int sum) {
        int[][] m = new int[S.length + 1][sum + 1];

        for (int i = 0; i <= S.length; i++) {
            for (int j = 0; j <= sum; j++) {
                // m[i][j] = -1;
                if (j == 0) {
                    m[i][j] = 1;
                    continue;
                }

                // No element to select
                if (j < 0) {
                    m[i][j] = 0;
                    continue;
                }

                // no coins
                if (i <= 0 && j > 0) {
                    m[i][j] = 0;
                    continue;
                }

                // don't use element i.
                m[i][j] = m[i - 1][j];

                if (j >= S[i - 1]) {
                    m[i][j] += m[i][j - S[i - 1]];
                }

            }
        }

        return m[S.length][sum];
    }

    public static int cnt(int S[], int sum) {
        int[][] m = new int[S.length][sum + 1];
        for (int i = 0; i < S.length; i++) {
            for (int j = 0; j <= sum; j++) {
                m[i][j] = -1;
            }
        }

        return dfs(S, S.length, sum, m);
    }

    // 1:04
    public static int dfs(int S[], int i, int sum, int[][] m) {
        if (sum == 0) {
            return 1;
        }

        // No element to select
        if (sum < 0) {
            return 0;
        }

        // no coins
        if (i <= 0 && sum > 0) {
            return 0;
        }

        if (m[i - 1][sum] != -1) {
            return m[i - 1][sum];
        }

        // don't use element i.
        int ret = dfs(S, i - 1, sum, m);

        // use element i.
        ret += dfs(S, i, sum - S[i - 1], m);

        m[i - 1][sum] = ret;
        return ret;
    }

}
