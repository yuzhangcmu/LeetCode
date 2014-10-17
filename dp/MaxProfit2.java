package Algorithms.dp;

public class MaxProfit2 {
    public int maxProfit(int[] prices) {
        if (prices == null) {
            return 0;
        }
        
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - prices[i - 1] > 0) {
                profit += prices[i] - prices[i - 1];
            }
        }   
        
        return profit;
    }
}