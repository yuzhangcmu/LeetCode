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
    
    /*
     * 2015.1.3
     * */
    public int maxProfit2(int[] prices) {
        if (prices == null) {
            return 0;
        }
        
        int maxProfit = 0;
        
        int len = prices.length;
        for (int i = 1; i < len; i++) {
            int dif = prices[i] - prices[i - 1];
            
            if (dif > 0) {
                maxProfit += dif;
            }
        }
        
        return maxProfit;
    }
}