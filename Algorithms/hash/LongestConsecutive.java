package Algorithms.hash;

import java.util.HashMap;
import java.util.HashSet;

public class LongestConsecutive {
    // Solution 1: use hashmap.
    public int longestConsecutive1(int[] num) {
        if (num == null) {
            return 0;
        }
        
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        int max = 0;
        
        int len = num.length;
        for (int i = 0; i < len ; i++) {
            // 寻找以num[i] 起头或是结尾的，如果找到，则可以跳过，因为我们
            // 不需要重复的数字
            if (map.get(num[i]) != null) {
                continue;
            }
            
            int left = num[i];
            int right = num[i];
            
            // 寻找左边界
            Integer board = map.get(num[i] - 1);
            if (board != null && board < left) {
                // 更新左边界
                left = board;
                
                // 删除左边2个集合
                map.remove(left);
                map.remove(num[i] - 1);
            }
            
            // 寻找右边界
            board = map.get(num[i] + 1);
            if (board != null && board > right) {
                // 更新右边界
                right = board;
                
                // 删除右边2个集合
                map.remove(right);
                map.remove(num[i] + 1);
            }
            
            // 创建新的合并之后的集合
            map.put(left, right);
            map.put(right, left);
            
            max = Math.max(max, right - left + 1);
        }
        
        return max;
    }
    
    // solution 2: use Hashset.
    public int longestConsecutive(int[] num) {
        if (num == null) {
            return 0;
        }
        
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i: num) {
            set.add(i);
        }
        
        int max = 0;
        for (int i: num) {
            int cnt = 1;
            set.remove(i);
            
            int tmp = i - 1;
            while (set.contains(tmp)) {
                set.remove(tmp);
                cnt++;
                tmp--;
            }
            
            tmp = i + 1;
            while (set.contains(tmp)) {
                set.remove(tmp);
                cnt++;
                tmp++;
            }
            
            max = Math.max(max, cnt);
        }
        
        return max;
    }
}
