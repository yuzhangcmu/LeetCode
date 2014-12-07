package Algorithms.dp;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        for (int i = 0; i < numRows; i++) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int j = 0; j < i + 1; j++) {
                if (j == 0 || j == i) {
                    // 两边的数字就是1.
                    list.add(1);
                } else {
                    // 中间的数字等于上一行的j + j -1的值
                    int num = ret.get(i - 1).get(j) + ret.get(i - 1).get(j - 1);
                    list.add(num);
                }
            }
            ret.add(list);
        }
        
        return ret;
    }
}
