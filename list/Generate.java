package Algorithms.list;

import java.util.ArrayList;
import java.util.List;

public class Generate {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        
        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<Integer>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || i == j) {
                    list.add(1);
                } else {
                    int sum = ret.get(i - 1).get(j - 1) + ret.get(i - 1).get(j);
                    list.add(sum);
                }
            }
            
            // BUG 1: forget this statement.
            ret.add(list);
        }
        
        return ret;        
    }
}