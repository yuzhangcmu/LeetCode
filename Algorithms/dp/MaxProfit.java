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
    
    public int maxProfit2(int[] prices) {
        if (prices == null) {
            return 0;
        }
        
        int maxProfit = 0;
        int minValue = Integer.MAX_VALUE;
        
        for (int i: prices) {
            minValue = Math.min(minValue, i);
            maxProfit = Math.max(maxProfit, i - minValue);
        }
        
        return maxProfit;
    }
}