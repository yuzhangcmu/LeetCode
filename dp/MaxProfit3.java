package Algorithms.dp;

public class MaxProfit3 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        int len = prices.length;
        int[] left = new int[len];
        int[] right = new int[len];
        
        // 计算i左边的最大利润
        int min = prices[0];
        left[0] = 0;
        for (int i = 1; i < len; i++) {
            min = Math.min(min, prices[i]);
            left[i] = Math.max(left[i - 1], prices[i] - min);
        }
        
        // 计算i右边的最大利润
        int max = prices[len - 1];
        right[len - 1] = 0;
        for (int i = len - 2; i >= 0; i--) {
            max = Math.max(max, prices[i]);
            right[i] = Math.max(right[i + 1], max - prices[i]);
        }
        
        // 把2边的利润相加
        int rst = 0;
        for (int i = 0; i < len; i++) {
            rst = Math.max(rst, left[i] + right[i]);
        }
        
        return rst;
    }
}