package Algorithms.dp;

import java.util.ArrayList;
import java.util.List;

public class GetRow {
    public List<Integer> getRow1(int rowIndex) {
        List<Integer> pre = new ArrayList<Integer>();
        List<Integer> cur = new ArrayList<Integer>();
        
        if (rowIndex < 0) {
            return cur;
        }
        
        // 娉ㄦ剰杩欓噷鐨剅owIndex璺熶笂涓�鐨勬剰涔変笉涓�牱锛佽繖涓槸绱㈠紩锛宱rz...
        // 鎵�互鎴戜滑瑕佺敤<=
        for (int i = 0; i <= rowIndex; i++) {
            // 绗琲琛屾湁i + 1涓厓绱�
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
    
    // SOLUTION 2: DO IT just inplace
    public List<Integer> getRow(int rowIndex) {
        List<Integer> ret = new ArrayList<Integer>();
        
        for (int i = 0; i <= rowIndex; i++) {
            for (int j = i; j >= 0; j--) {
                if (j == i) {
                    ret.add(1);
                } else if (j != 0) {
                    // ERROR: use add instead of set
                    //ret.add(ret.get(j) + ret.get(j - 1));
                    ret.set(j, ret.get(j) + ret.get(j - 1));
                }
            } 
        }
        
        return ret;
    }
}