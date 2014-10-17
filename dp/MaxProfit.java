package Algorithms.dp;

public class MaxProfit {
    public int maxProfit(int[] prices) {
        if (prices == null) {
            return 0;
        }
        
        int len = prices.length;
        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }
        
        return maxProfit;
    }
}