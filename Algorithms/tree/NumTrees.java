package Algorithms.tree;

public class NumTrees {
    public int numTrees1(int n) {
        // cnt[n] = cnt[0]cnt[n - 1] + cnt[1]cnt[n - 2] ... cnt[n - 1]cnt[0]
        // cnt[n-1] = cnt[0]cnt[n - 2] + cnt[1]cnt[n - 3] ... cnt[n - 2]cnt[0]
        // For example:
        // when N = 3, 
        // cnt[3] = cnt[0]cnt[2] + cnt[1]cnt[1] + cnt[2]cnt[1];
        // so the Formula is :
        
        // F[n] = ∑ Cnt[j] * cnt[n-j-1]     0<=j<=n-1
        
        // base case:
        // when n = 0, cnt[0] = 1;
        
        int[] cnt = new int[n + 1];
        cnt[0] = 1;
        
        for (int i = 1 ; i <= n; i++) {
            cnt[i] = 0;
            for (int j = 0; j <= i - 1; j++) {
                cnt[i] += cnt[j] * cnt[i - j - 1];
            }
        }
        
        return cnt[n];
    }
    
    public int numTrees(int n) {
        if (n == 0) {
            return 1;
        }
        
        // Get the results of all the trees which 
        // has the root from 1 to n;
        int num = 0;
        for (int i = 0; i <= n - 1; i++) {
            num += numTrees(i) * numTrees(n - 1 - i);
        }
        
        return num;
    }
}