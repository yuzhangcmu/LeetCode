package Algorithms.dp;

import java.util.ArrayList;
import java.util.List;

public class GetRow {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> pre = new ArrayList<Integer>();
        List<Integer> cur = new ArrayList<Integer>();
        
        if (rowIndex < 0) {
            return cur;
        }
        
        // 注意这里的rowIndex跟上一题的意义不一样！这个是索引，orz...
        // 所以我们要用<=
        for (int i = 0; i <= rowIndex; i++) {
            // 第i行有i + 1个元素
            cur = new ArrayList<Integer>(); 
            for (int j = 0; j < i + 1; j++) {
                if (j == 0 || j == i) {
                    cur.add(1);
                } else {
                    cur.add(pre.get(j) + pre.get(j - 1));
                }
            }
            pre = cur;
        }
        
        return cur;
    }
}