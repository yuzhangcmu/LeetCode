package Algorithms.string;

public class IntToRoman {
    public String intToRoman(int num) {
        int nums[] = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    
        StringBuilder sb = new StringBuilder();
        
        int i = 0;
        // 使用贪心法。尽量拆分数字
        while (i < nums.length) {
            if (num >= nums[i]) {
                sb.append(romans[i]);
                num -= nums[i];
            } else {
                i++;
            }
        }
        
        return sb.toString();
    }
}