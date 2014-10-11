package Algorithms.dp;

public class Candy {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        
        int len = ratings.length;
        int[] candys = new int[len];
        
        // go from the left side to the right side and give them candys.
        candys[0] = 1;
        for (int i = 1; i < len; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candys[i] = candys[i - 1] + 1;
            } else {
                candys[i] = 1;
            }
        }
        
        for (int i = len - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                int num = candys[i + 1] + 1;
                candys[i] = Math.max(candys[i], num);
            }
        }
        
        int sum = 0;
        for (int i: candys) {
            sum += i;
        }
        
        return sum;
    }
}
