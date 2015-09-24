package Algorithms.sequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] num) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (num == null) {
            return ret;
        }
        
        Arrays.sort(num);
        
        int len = num.length;
        for (int i = 0; i < len; i++) {
             // 跳过重复的元素，首个数字不需要选择重复的. 
            if (i > 0 && num[i] == num[i - 1]) {
                continue;
            }
            
            // 创建二个指针，分别从2头查找，查目标值，它们2个加起来要等于0-num1
            int l = i + 1;
            int r = len - 1;
            while (l < r) {
                int sum = num[i] + num[l] + num[r];
                if (sum == 0) {
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    list.add(num[i]);
                    list.add(num[l]);
                    list.add(num[r]);
                    ret.add(list);
                    
                    // 跳过重复元素 
                    do {
                        l++;
                    } while (l < r && num[l] == num[l - 1]);
                    
                    do {
                        r--;
                    } while (l < r && num[r] == num[r + 1]);
                } else if (sum < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        
        return ret;
    }
}